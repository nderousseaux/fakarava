package fakarava.ecosystem;

import java.util.ArrayList;
import java.lang.Math;

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
    
    /**
     * @method
     **/
    public boolean isIn(Cellule cellule){
        boolean trouve = false;
        int i=0;
        ArrayList<Predateur> preds = cellule.getLagon().getPredbyCellule(cellule);
        while (!trouve && i<preds.size()){
            if (preds.get(i).getNumero()==this.getNumero()){
                trouve = true;
            }
            i++;
        }
        return trouve;
    }
    
    public void attaque(boolean isDay) {
        
    	//On récupére le lagon (objet que l'on va souvent utiliser)
    	Lagon leLagon = this.getCellule().getLagon();
    	//On determine la force du courrant
    	double forceDuCourrant = Lagon.getc_Max_Current_Strenght()/leLagon.getNbPasses();
    	//On détermine quel proie on va attaquer !
        
        Proie victime = leLagon.getMinVivacite(this.getCellule());
        //On determine la probabilitée de survie
        int vivacite = victime.getVivacite();
        if(!isDay){
            vivacite/=2;
        }
        
        double p = Math.max(0, vivacite/this.getPoid() - forceDuCourrant/100.0);
        //On teste si oui ou non le prédateur réussi son attaque
        Random alea = Random.getARandom();
        //Si oui, on supprime le poisson
        if(alea.selection(p)){
            leLagon.deletePoisson(victime.getNumero());
            //ON ajoute le poid du poisson � l'attaquant
            this.setPoid(this.getPoid()+victime.getPoid());
            System.out.println("victime : " + victime.toString());
        }
        
            //Sinon on cherche un predateur à attaquer 
        else if (leLagon.getPredbyCellule(this.getCellule()).size()<1){
            ArrayList<Predateur> predateurs = leLagon.getPredbyCellule(this.getCellule());

                    //On supprime le prédateur attaquant à la liste (il ne peut pas s'attaquer lui m�me)
            predateurs.remove(this);
            int predaVicNum = alea.who(predateurs.size());
            Predateur predaVic = predateurs.get(predaVicNum);
                    //On enlève le poid du prédateur
            predaVic.setPoid(predaVic.getPoid()-(this.getPoid()/getBiteFactor()));
            System.out.println("victime : " + predaVic.toString());
            if (predaVic.getPoid()<=0){
                leLagon.deletePoisson(predaVic.getNumero());
            }
        }
    }
    
    /**
     * @Override
     **/
    @Override
    public void ticktock(){
        this.setAge(this.getAge()+1);
        this.setPoid(this.getPoid()-1);
    }
    @Override
    public String toString(){
        return "Pr�dateur ---> " + super.toString();
    }
    
    
    /**
     * @method_static
     **/
    public static void setPredatorCloneTime(int Valeur){
        c_PREDATOR_CLONE_TIME = Valeur;
    }
    public static int getPredatorCloneTime(){
        return c_PREDATOR_CLONE_TIME;
    }
    public static void setBiteFactor(int Valeur){
        c_BITE_FACTOR = Valeur;
    }
    public static int getBiteFactor(){
        return c_BITE_FACTOR;
    }
}
