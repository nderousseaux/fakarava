package fakarava.ecosystem;

import java.util.ArrayList;

public class Lagon {

    /**
     * @Attributs
     */
    private int N;
    private ArrayList<Cellule> LesCellules = new ArrayList<Cellule>();
    private static Integer nextNumero = -1;
    private ArrayList<Poisson> poissons = new ArrayList<Poisson>();
    private ArrayList<Cellule> passes = new ArrayList<Cellule>();

    //Constructeur
    public Lagon(int N){
        this.N = N;
        //On crée autant de cellules que necessaire
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                this.LesCellules.add(new Cellule(i, j));
            }
        }
    }

    //Les mÃ©thodes
    public Cellule getCellule(int x, int y){
        return this.LesCellules.get((y*this.N)+x);
    }

    public int getN(){
        return this.N;
    }
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
        System.out.println("new Proie " + nextNumero);
        return nextNumero;
    }
    public int cloneProie(Proie proie){
        nextNumero++;
        this.poissons.add(proie.clone(nextNumero));
        return nextNumero; 
    }
   /* public int newPasse(Cellule cellule){
        int x = cellule.getPosition()[0];
        int y = cellule.getPosition()[1];
        int n = this.getN();
        if (x == 0 || x == this.getN()){ // TODO verifier qu'on peut faire de la cellule une passe
            if(){
                this.passes.add(cellule);
            }
        }
        else if 
        else if(y == 0 || y == this.getN()){
            if(){
                this.passes.add(cellule);
            }
        }
        
    }*/
    public void deletePoisson(Integer numero) {
        for (Poisson unPoisson:poissons) {
            if(unPoisson.getNumero() == numero){
                poissons.remove(unPoisson);
            }
        }
    }
    public ArrayList<Poisson> getPoissons() {
        return this.poissons;
    }

    public Poisson getPoisson(Integer numero) {
        System.out.println("getPoisson");
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
    public int getNbPoisson(){
        return this.poissons.size();
    }
    public void tempsPasse(){
        for (Poisson poisson : this.poissons){
            poisson.ticktock();
            poisson.seDeplacer(this);
            if (poisson.getPoid() <= 0){
                deletePoisson(poisson.getNumero());
            }
            if ((poisson.getType() == "Proie") && (poisson.getAge()==Proie.getPreyCloneTime())){
                this.cloneProie((Proie)poisson);
            }
            else if (poisson.getAge()==Predateur.getPredatorCloneTime()){
                this.clonePredateur((Predateur)poisson);
            }
        }
    }
}
