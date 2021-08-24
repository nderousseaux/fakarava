package fakarava.ecosystem;

import java.util.ArrayList;

public class Cellule {

    //Attributs
    private int[] position = new int[2];
    private ArrayList<Poisson> poissons = new ArrayList<Poisson>();

    //Constructeur
    public Cellule(int x, int y){
        this.position[0] = x;
        this.position[1] = y;
    }

    //Methodes
    public int[] getPosition(){
        return this.position;
    }

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
    }
}
