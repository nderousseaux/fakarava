package fakarava.ecosystem;

public class Predateur extends Poisson{
    /**
     * @attribute
     */
    private static Integer c_PREDATOR_CLONE_TIME = 10;
    private static Integer c_BITE_FACTOR = 10;
    
    /**
     * @Constructeur
     */
    public Predateur(int age, String nom, int numero, double poid, Cellule cellule){
        super(age, nom, numero, poid, cellule, "Predateur");
    }
    public Predateur clone(int numero){
        return new Predateur(0, this.getNom(), numero,this.getPoid(),this.getCellule());
    }
    public void ticktock(){
        this.setAge(this.getAge()+1);
    }
   
    public static void setPredatorCloneTime(int Valeur){
        c_PREDATOR_CLONE_TIME = Valeur;
    }
    public static int getPredatorCloneTime(){
        return c_PREDATOR_CLONE_TIME;
    }
    public static void setPreyCloneTime(int Valeur){
        c_BITE_FACTOR = Valeur;
    }
    public static int getPreyCloneTime(){
        return c_BITE_FACTOR;
    }
}
