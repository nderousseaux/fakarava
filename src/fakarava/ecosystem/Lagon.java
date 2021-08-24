package fakarava.ecosystem;

import java.util.ArrayList;
import java.util.Collections;

public class Lagon {

    //Attributs
    private int N;
    private ArrayList<Cellule> LesCellules = new ArrayList<Cellule>();
    private ArrayList<Poisson> poissons = new ArrayList<Poisson>();
    private ArrayList<Cellule> passes;
    private int nbPasse = 0;

    private static int nextPasse = -1;
    private static int nextNumero = -1;

    private static int c_MAX_DENSITY=50;
    private static int c_MAX_CURRENT_STRENGHT;
    
    //Constructeurs
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


    //Assesseurs
    public int getN(){
        return this.N;
    }


    //Méthodes du role de Gestionnaire Poisson
    //Partie Poisson
    public Poisson getPoisson(Integer numero) {

        //Le poisson n'est pas trouvé, jusqu'à preuve du contraire
        Poisson res = null;
        
        //On cherche le poisson dans la liste des poissons
        for(Poisson poisson : this.poissons){
            if(poisson.getNumero() == numero){
                res = poisson;
            }
        }

        //Si le poisson n'a pas été trouvé, on renvoie une exception
        if (res == null){
             throw new IllegalArgumentException(); 
        }
        return res;
    }
    public ArrayList<Poisson> getPoissonbyCellule(Cellule cellule){
        ArrayList<Poisson> poissonc = new ArrayList<Poisson>();
        //On parcourt la liste des poissons
        for(Poisson poisson : this.poissons){
            if (poisson.getCellule()==cellule){
                poissonc.add(poisson);
            }
        }
        return poissonc;
    }
    public ArrayList<Poisson> getPoissons() {
        return this.poissons;
    }
    public int getNbPoisson(){
        return this.poissons.size();
    }
    public void deletePoisson(Integer numero) {
        //On copie la liste
        ArrayList<Poisson> poissonS = new ArrayList<Poisson>(this.poissons);
        //On parcourt la liste des poissons
        for (Poisson unPoisson:poissonS) {
            if(unPoisson.getNumero() == numero){
                poissons.remove(unPoisson);
            }
        }
    }
    //Partie Proie
    public int newProie(String nom, double poid, int x, int y,int vivacite) {
        nextNumero++;
        poissons.add(new Proie(0, nom, nextNumero, poid,this.getCellule(x, y),vivacite));
        return nextNumero;
    }
    public ArrayList<Proie> getProiebyCellule(Cellule cellule){
        ArrayList<Proie> proies = new ArrayList<Proie>();
        //On parcourt tout les poissons et on récupère les proies
        for (Poisson poisson : this.getPoissonbyCellule(cellule)){
            if (poisson.getType() == "Proie"){
                proies.add((Proie)poisson);
            }
        }
        //On trie la liste
        Collections.sort(proies,new ComparateurProie());
        return proies;
    }
    public int cloneProie(Proie proie){
        nextNumero++;
        this.poissons.add(proie.clone(nextNumero));
        return nextNumero; 
    }
    public Proie getMinVivacite(Cellule cellule){
        if (getProiebyCellule(cellule).size()==0)
            throw new IllegalArgumentException("Pas de proie dans cette cellule");
        return getProiebyCellule(cellule).get(0);
    }
    //Partie Prédateur
    public int newPredateur(String nom, double poid, int x, int y) {
        nextNumero++;
        poissons.add(new Predateur(0, nom, nextNumero, poid,this.getCellule(x, y)));
        return nextNumero;
    }
    public ArrayList<Predateur> getPredbyCellule(Cellule cellule){
        ArrayList<Predateur> predateurs = new ArrayList<Predateur>();
        //On parcourt la liste des poissons et on sélectionne que les prédateurs
        for (Poisson poisson : this.getPoissonbyCellule(cellule)){
            if (poisson.getType() == "Predateur"){
                predateurs.add((Predateur)poisson);
            }
        }
        Collections.sort(predateurs,new ComparateurPred());
        return predateurs;
    }
    public int clonePredateur(Predateur predateur){
        nextNumero++;
        poissons.add(predateur.clone(nextNumero));
        return nextNumero;
    }
    

    //Méthodes du role de Gestionnaire cellule
    //Cellules
    public Cellule getCellule(int x, int y){
        return this.LesCellules.get((x*this.N)+y);
    }
    public int getNbCellule(){
        return this.N*this.N;
    }
    //Passes
    public int newPasse(Cellule cellule){
        //On enregistre la positiion de la cellule
        int x = cellule.getPosition()[0];
        int y = cellule.getPosition()[1];
        int n = this.getN();

        boolean voisin = false;
        //Si la cellule ne respecte pas toutes les conditions, on renvoie une exception
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
            cellule.setId(nbPasse); 
            this.nbPasse++;
        }
        else{
            throw new IllegalArgumentException("Cette cellule ne peut pas devenir une passe");
        }
        return nbPasse-1;
    }
    public Cellule getPasseById(int id){
        //Par défaut la passe n'existe pas, jusqu'à preuve du contraire
        Cellule cel=null;

        //On parcourt la liste des cellules
        for (Cellule cellule : this.passes){
            
            if (cellule.getId()==id){
                cel=cellule;
            }
        }
        if (cel == null){
            throw new IllegalArgumentException("cellule non trouvée");
        }
        return cel;
    }
    public int getNbPasses(){
        return this.nbPasse;
    }


    //Méthodes de temps
    public void chasse(boolean isDay){
        ArrayList<String> resultc = new ArrayList<String>();
        //Pour tout les prédateurs qui sont dans une passe et qui peuvent attaquer, on appelle la méthode attaque
        for (Cellule passe : passes){ 
            ArrayList<Predateur> preds = this.getPredbyCellule(passe);
            for (Predateur pred : preds){
                if (this.getProiebyCellule(passe).size()>0){
                    if ((pred.isIn(passe))&&(pred.getPoid()>0)){
                        resultc.add(pred.attaque(isDay));  
                    }
                }
            }
            passe.setChasse(resultc);
        }
    }
    public void tempsPasse(boolean isDay){
        // On copie la liste de poissons
        ArrayList<Poisson> poissonS = new ArrayList<Poisson>(this.poissons);


        for (Poisson poisson : poissonS){
            //Les poissons viellissent et perdent du pois
            poisson.ticktock();

            //On supprime tout les poissons qui sont mort de faim
            if (poisson.getPoid() <= 0){
                
                if(poisson.getType() == "Predateur") {
                    if(((Predateur)poisson).getProprietaireEmetteur() != null){
                        ((Predateur)poisson).getProprietaireEmetteur().deleteEmetteur((Predateur)poisson);
                        
                    }
                }
                deletePoisson(poisson.getNumero());
                
            }

            //On fait reproduire les proies et les prédateurs
            if ((poisson.getType() == "Proie") && (poisson.getAge()%Proie.getPreyCloneTime()==0)){
                this.cloneProie((Proie)poisson);
            }
            else if ((poisson.getType() == "Predateur") && (poisson.getAge()%Predateur.getPredatorCloneTime()==0)){
                this.clonePredateur((Predateur)poisson);
            }
        }

        //Les poissons se déplacent
        poissonS = new ArrayList<Poisson>(this.poissons);
        for (Poisson poisson:poissonS){
            poisson.seDeplacer(this);
        } 

        //Les requins chassent
        this.chasse(isDay);
    }
    

    //Méthodes statiques
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
