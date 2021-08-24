package fakarava.ecosystem;

public class Proie extends Poisson{
    
    //Attributs
    private int vivacite;

    private static int c_PREY_CLONE_TIME = 10;
    

    //Constructeurs
    public Proie(int age, String nom, int numero, double poid, Cellule cellule, int vivacite){
        super(age, nom, numero, poid, cellule);
        this.vivacite = vivacite;
    }
    

    //Assesseurs
    public int getVivacite(){
        return this.vivacite;
    }
    

    //Méthodes d'instances
    public Proie clone(int numero){
        return new Proie(0, this.getNom(), numero,this.getPoid(),this.getCellule(),this.getVivacite());
    }


    //Méthodes statique
    public static void setPreyCloneTime(int Valeur){
        c_PREY_CLONE_TIME = Valeur;
    }
    public static int getPreyCloneTime(){
        return c_PREY_CLONE_TIME;
    }
    
    @Override
    public String toString(){
        return "Proie ---> " + super.toString() + " - Vivacité:" + this.getVivacite();
    }
   
    
    
}
