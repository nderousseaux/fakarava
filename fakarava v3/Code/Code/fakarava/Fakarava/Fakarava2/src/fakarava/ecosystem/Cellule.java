package fakarava.ecosystem;

import java.util.ArrayList;

public class Cellule {

    //Attributs
    private int[] position = new int[2];
    //private ArrayList<Poisson> poissons = new ArrayList<Poisson>();
    private Boolean estPasse = false;
    private Lagon leLagon;

    //Constructeur
    public Cellule(int x, int y, Lagon lagon){
        this.position[0] = x;
        this.position[1] = y;
        this.leLagon = lagon;
    }

    //Methodes
    public int[] getPosition(){
        return this.position;
    }
    public Lagon getLagon(){
        return this.leLagon;
    }
    public boolean getEstPasse(){
        return this.estPasse;
    }
    public void setEstPasse(boolean etat){
        this.estPasse = etat;
    }
/*
    public ArrayList<Poisson> getPoissons(){
        return this.poissons;
    }

    public void delPoisson(int id){
        for (Poisson unPoisson:this.poissons) {
            if(unPoisson.getNumero() == id){
                this.poissons.remove(unPoisson);
            }
        }
    }

    public void addPoisson(Poisson poisson){
        this.poissons.add(poisson);
    }
    
    public int getNbPoisson(){
        return this.poissons.size();
    }
    @Override
    public String toString(){
        return "cellule position " + this.position[0] + "," + this.position[0] + " avec " + this.getNbPoisson() + "poissons";
    }*/
}
