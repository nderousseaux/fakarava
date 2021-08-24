package fakarava.ecosystem;

import fakarava.Clock;


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

    //Méthodes
    public int getNumero(){
        return this.numero;
    }
    public String getType(){
        return this.type;
    }

    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getNom(){
        return this.nom;
    }

    public double getPoid(){
        return this.poid;
    }
    public void setPoid(double poid){
        this.poid = poid;
    }

    public Cellule getCellule(){
        return this.cellule;
    }


    public void seDeplacer(Lagon lagon){

        int x = this.cellule.getPosition()[0];
        int y = this.cellule.getPosition()[1];

        Random alea = Random.getARandom();
        int[] nouvellePosition = alea.move(x,y,lagon.getN());

        //On enlève le poisson de la cellule
        this.getCellule().delPoisson(this.numero);

        //On le place dans sa nouvelle cellule
        lagon.getCellule(nouvellePosition[0], nouvellePosition[1]).addPoisson(this);
    }
    @Override
    public String toString(){
        return "Poisson : " + this.getNom() + " " +this.getAge() + " " + this.getCellule().getPosition()[0] + ", "+this.getCellule().getPosition()[1] + " "+ this.getPoid() +" "+ this.getType();
    }
    
    public void ticktock(){
        this.age = this.age +1;
    }
}
