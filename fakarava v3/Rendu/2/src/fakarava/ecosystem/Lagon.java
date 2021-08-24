package fakarava.ecosystem;

import java.util.ArrayList;
import java.util.Collections;

public class Lagon {

    /**
     * @Attributs
     */
    private int N;
    private ArrayList<Cellule> LesCellules = new ArrayList<Cellule>();
    private static Integer nextNumero = -1;
    private static int c_MAX_DENSITY=50;
    private ArrayList<Poisson> poissons = new ArrayList<Poisson>();
    private ArrayList<Cellule> passes;
    private int nbPasse = 0;
    private static int c_MAX_CURRENT_STRENGHT;

    /**
     * @Constructeur
     **/
    public Lagon(int N){
        this.N = N;
        this.passes = new ArrayList<Cellule>();
        //On crée autant de cellules que necessaire
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                this.LesCellules.add(new Cellule(i, j,this));
            }
        }
    }

    /**
     * @method_instanciation
     */
    
    public int newPredateur(String nom, double poid, int x, int y) {
        nextNumero++;
        poissons.add(new Predateur(0, nom, nextNumero, poid,this.getCellule(x, y)));
        return nextNumero;
    }
    public int clonePredateur(Predateur predateur){
        nextNumero++;
        poissons.add(predateur.clone(nextNumero));
        return nextNumero;
    }
    public int newProie(String nom, double poid, int x, int y,int vivacite) {
        nextNumero++;
        poissons.add(new Proie(0, nom, nextNumero, poid,this.getCellule(x, y),vivacite));
        //System.out.println("new Proie " + nextNumero);
        return nextNumero;
    }
    public int cloneProie(Proie proie){
        nextNumero++;
        this.poissons.add(proie.clone(nextNumero));
        return nextNumero; 
    }
    public int newPasse(Cellule cellule){
        int x = cellule.getPosition()[0];
        int y = cellule.getPosition()[1];
        int n = this.getN();
        boolean voisin = false;
        if ((x==0 || x==this.getN()-1)||(y==0 || y==this.getN()-1)) {
            for(Cellule cellulen:this.LesCellules){
                if(cellulen.getEstPasse()){
                    if((x<=cellulen.getPosition()[0]+1 && x>=cellulen.getPosition()[0]-1 )
                       || (y<=cellulen.getPosition()[1]+1 && y>=cellulen.getPosition()[1]-1)){
                           throw new IllegalArgumentException("Cette cellule est à coté d'une passe ou est une passe");
                       }
                }
            }
            this.passes.add(cellule);
            cellule.setEstPasse(true);
            this.nbPasse++;
        }
        else{
            throw new IllegalArgumentException("Cette cellule ne peut pas devenir une passe");
        }
        return nbPasse;
    }
    
    public void deletePoisson(Integer numero) {
        ArrayList<Poisson> poissonS = new ArrayList<Poisson>(this.poissons);
        for (Poisson unPoisson:poissonS) {
            if(unPoisson.getNumero() == numero){
                poissons.remove(unPoisson);
            }
        }
    }
    
    /**
     * @assesseur
     **/
    public Cellule getCellule(int x, int y){
        return this.LesCellules.get((x*this.N)+y);
    }

    public int getN(){
        return this.N;
    }
    public int getNbPoisson(){
        return this.poissons.size();
    }
    public int getNbCellule(){
        return this.N*this.N;
    }
    public int getNbPasses(){
        return this.nbPasse;
    }
    public ArrayList<Poisson> getPoissons() {
        return this.poissons;
    }
    public Proie getMinVivacite(Cellule cellule){
        if (getProiebyCellule(cellule).size()==0)
            throw new IllegalArgumentException("Pas de proie dans cette cellule");
        return getProiebyCellule(cellule).get(0);
    }
    public Poisson getPoisson(Integer numero) {
        //System.out.println("getPoisson");
        Poisson res = null;
        Boolean trouve = false;
        System.out.println(this.poissons.get(0).getNumero());
        for(Poisson poisson : this.poissons){
            if(poisson.getNumero() == numero){
                res = poisson;
                trouve = true;
            }
        }
        if (!trouve){
             throw new IllegalArgumentException(); 
        }
        return res;
    }
    public ArrayList<Poisson> getPoissonbyCellule(Cellule cellule){
        ArrayList<Poisson> poissonc = new ArrayList<Poisson>();
        for(Poisson poisson : this.poissons){
            if (poisson.getCellule()==cellule){
                poissonc.add(poisson);
            }
        }
        return poissonc;
    }
    public ArrayList<Predateur> getPredbyCellule(Cellule cellule){
        ArrayList<Predateur> predateurs = new ArrayList<Predateur>();
        for (Poisson poisson : this.getPoissonbyCellule(cellule)){
            if (poisson.getType() == "Predateur"){
                predateurs.add((Predateur)poisson);
            }
        }
        Collections.sort(predateurs,new ComparateurPred());
        return predateurs;
    }
    public ArrayList<Proie> getProiebyCellule(Cellule cellule){
        ArrayList<Proie> proies = new ArrayList<Proie>();
        for (Poisson poisson : this.getPoissonbyCellule(cellule)){
            if (poisson.getType() == "Proie"){
                proies.add((Proie)poisson);
            }
        }
        Collections.sort(proies,new ComparateurProie());
        return proies;
    }
   
    
    /**
     * @method_temps
     */
    public void chasse(boolean isDay){
        for (Cellule passe : passes){ 
            ArrayList<Predateur> preds = this.getPredbyCellule(passe);
            for (Predateur pred : preds){
                if (this.getProiebyCellule(passe).size()>0){
                    if ((pred.isIn(passe))&&(pred.getPoid()>0)){
                        pred.attaque(isDay);  
                    }
                }
            }
        }
    }
    public void tempsPasse(boolean isDay){
        ArrayList<Poisson> poissonS = new ArrayList<Poisson>(this.poissons);
        for (Poisson poisson : poissonS){
            poisson.ticktock();
            if (poisson.getPoid() <= 0){
                deletePoisson(poisson.getNumero());
            }
            if ((poisson.getType() == "Proie") && (poisson.getAge()%Proie.getPreyCloneTime()==0)){
                this.cloneProie((Proie)poisson);
            }
            else if ((poisson.getType() == "Predateur") && (poisson.getAge()%Predateur.getPredatorCloneTime()==0)){
                this.clonePredateur((Predateur)poisson);
            }
        }
        poissonS = new ArrayList<Poisson>(this.poissons);
        for (Poisson poisson:poissonS){
            poisson.seDeplacer(this);
        } 
        this.chasse(isDay);
    }
    
    /**
     * @static
     */
    public static int getc_MAX_DENSITY(){
        return c_MAX_DENSITY;
    }
    public static void setc_MAX_DENSITY(int density){
        c_MAX_DENSITY = density;
    }
    public static int getc_Max_Current_Strenght(){
        return c_MAX_CURRENT_STRENGHT;
    }
    public static void setc_Max_Current_Strenght(int strenght){
        c_MAX_CURRENT_STRENGHT = strenght;
    }
}
