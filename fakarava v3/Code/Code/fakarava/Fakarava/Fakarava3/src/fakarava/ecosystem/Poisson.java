package fakarava.ecosystem;

import fakarava.ecosystem.Clock;
import fakarava.ecosystem.Random;

public class Poisson implements Clock{

    //Attributs
    private int age;
    private String nom;
    private int numero;
    private double poid;
    private Cellule cellule;
    private String type = "Proie";


    //Constructeur
    public Poisson(int age, String nom, int numero, double poid, Cellule cellule){
        this.age = age;
        this.nom = nom;
        this.numero = numero;
        this.poid = poid;
        this.cellule = cellule;
    }
    public Poisson(int age, String nom, int numero, double poid, Cellule cellule,String type){
        this.age = age;
        this.nom = nom;
        this.numero = numero;
        this.poid = poid;
        this.cellule = cellule;
        this.type = type;
    }

    //Assesseur
    //Get
    public int getNumero(){
        return this.numero;
    }
    public String getType(){
        return this.type;
    }
    public int getAge(){
        return this.age;
    }
    public String getNom(){
        return this.nom;
    }
    public double getPoid(){
        return this.poid;
    }
    public Cellule getCellule(){
        return this.cellule;
    }
    //Set
    public void setAge(int age){
        this.age = age;
    }
    public void setPoid(double poid){
        this.poid = poid;
    }

    

    //Méthode de temps
    public void ticktock(){
        //Les poissons viellissent
        this.age = this.age +1;
    }
    public void seDeplacer(Lagon lagon){

        int x = this.cellule.getPosition()[0];
        int y = this.cellule.getPosition()[1];

         //Les poissons se déplacent avec la classe Random
        Random alea = Random.getARandom();
        int[] nouvellePosition = alea.move(x,y,lagon.getN());


        this.cellule = lagon.getCellule(nouvellePosition[0], nouvellePosition[1]);
    }
    
            
    @Override
    public String toString(){
        return " Nom:"+ this.getNom() + " - Age:" +this.getAge() + " - Cellule:(" + this.getCellule().getPosition()[0]
               + ","+this.getCellule().getPosition()[1] + ") - Poid:"+ String.format("%.2f", this.getPoid());
    }
}
