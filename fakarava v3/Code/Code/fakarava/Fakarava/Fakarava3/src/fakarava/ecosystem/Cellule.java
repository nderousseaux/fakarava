package fakarava.ecosystem;

import java.util.ArrayList;

public class Cellule {

    //Attributs de Cellule
    
    private int[] position = new int[2];
    //private ArrayList<Poisson> poissons = new ArrayList<Poisson>();
    private Boolean estPasse = false;
    private Lagon leLagon;
    private Plongeur proprietaireCamera = null;
    private int idPasse;
    private ArrayList<String> chasse = new ArrayList<String>();
    
/*********************************************************************************************************************/

    //Constructeur de Celulle

    public Cellule(int x, int y, Lagon lagon){
        this.position[0] = x;
        this.position[1] = y;
        this.leLagon = lagon;
    }

/*********************************************************************************************************************/

    //Methodes
    
    // La m�thode getPosition return la position 
    public int[] getPosition(){
        return this.position;
    }
    // La m�thode getLagon return le lagon
    public Lagon getLagon(){
        return this.leLagon;
    }
    // La m�thode getEstPasse est un boolean qui renvoie estPasse
    public boolean getEstPasse(){
        return this.estPasse;
    }
    // La m�thode setEstPasse modifie la valeur du estPasse
    public void setEstPasse(boolean etat){
        this.estPasse = etat;
    }
    // La m�thode getProprietaireCamera renvoie le proprietaire de la camera
    public Plongeur getProprietaireCamera(){
        return this.proprietaireCamera;
    }
    //La m�thode setProprietaireCamera modifie la valeur du proprietaireCamera
    public void setProprietaireCamera(Plongeur pl){
        this.proprietaireCamera = pl;
    }
    //La m�thode getId renvoie idPasse
    public int getId(){
        return this.idPasse;
    }
    //La m�thode setId modifie idPasse
    public void setId(int id){
        this.idPasse = id;
    }
    //La m�thode getChasse renvoie chasse
    public ArrayList<String> getChasse(){
        return this.chasse;
    }
    //Lam�thode setChasse modifie chasse
    public void setChasse(ArrayList<String> chasses){
        this.chasse = chasses;
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
    }*/
    
/*********************************************************************************************************************/

    // M�thode d'instance
    @Override 
    public String toString(){
        return "cellule position " + this.position[0] + "," + this.position[0];
    }
}
