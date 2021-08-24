package fakarava.ecosystem;

public class Predateur extends Poisson{
    /**
     * @attribute
     */
    private static int c_PREDATOR_CLONE_TIME = 10;
    private static int c_BITE_FACTOR = 10;
    
    /**
     * @Constructeur
     */
    public Predateur(int age, String nom, int numero, double poid, Cellule cellule){
        super(age, nom, numero, poid, cellule, "Predateur");
    }
    public Predateur clone(int numero){
        return new Predateur(0, this.getNom(), numero,this.getPoid(),this.getCellule());
    }
    @Override
    public String toString(){
        return "Predateur : " + this.getNom() + " " +this.getAge() + " (" + this.getCellule().getPosition()[0] + ", "+this.getCellule().getPosition()[1] + ") "+ this.getPoid();
    }
   
    public static void setPredatorCloneTime(int Valeur){
        c_PREDATOR_CLONE_TIME = Valeur;
    }
    public static int getPredatorCloneTime(){
        return c_PREDATOR_CLONE_TIME;
    }
    @Override public void ticktock(){
        this.setAge(this.getAge()+1);
        this.setPoid(this.getPoid()-1);
    }
    /*public static void setBiteFactor(int Valeur){
        c_BITE_FACTOR = Valeur;
    }
    public static int getBiteFactor(){
        return c_BITE_FACTOR;
    }*/
    public void attaque() {
    }
}
