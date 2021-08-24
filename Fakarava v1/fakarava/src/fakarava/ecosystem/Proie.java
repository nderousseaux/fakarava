package fakarava.ecosystem;

public class Proie extends Poisson{
    /**
     * @attribute
     */
    private int vivacite;

    /**
     * @attribute
     */
    private static Integer c_PREY_CLONE_TIME = 10;
    
    /**
     * @contructeur
     */
    public Proie(int age, String nom, int numero, double poid, Cellule cellule, int vivacite){
        super(age, nom, numero, poid, cellule);
        this.vivacite = vivacite;
    }
    
    /**
     * @method
     */
    public int getVivacite(){
        return this.vivacite;
    }
    
    public Proie clone(int numero){
        return new Proie(0, this.getNom(), numero,this.getPoid(),this.getCellule(),this.getVivacite());
    }
   
    /**
     * @param Valeur
     */
    public static void setPreyCloneTime(int Valeur){
        c_PREY_CLONE_TIME = Valeur;
    }
    public static int getPreyCloneTime(){
        return c_PREY_CLONE_TIME;
    }
}
