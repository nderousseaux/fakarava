package fakarava.ecosystem;

import java.util.ArrayList;
import java.lang.Math;

public class Predateur extends Poisson{

    //Attributs de Predateur
    
    private static int c_PREDATOR_CLONE_TIME = 10;
    private static int c_BITE_FACTOR = 10;
    private Plongeur proprietaireEmeteur = null;
    
/*********************************************************************************************************************/
    
    //Constructeur de Predateur
    
    public Predateur(int age, String nom, int numero, double poid, Cellule cellule){
        super(age, nom, numero, poid, cellule, "Predateur");
    }
    public Predateur clone(int numero){
        return new Predateur(0, this.getNom(), numero,this.getPoid(),this.getCellule());
    }
    
/*********************************************************************************************************************/   

    //M�thodes 

    //La m�tode isIn est un boolean v�rifie si le predateur est dans la cellule
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
    //La m�thode attaque g�re l'attaque des pr�datueur
    public String attaque(boolean isDay) {
        String result="Attaque de "+this.toString()+" result ";
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
            result += " r�ussie --> victime : " + victime.toString();
        }
        //Sinon on cherche un predateur � attaquer 
        else if (leLagon.getPredbyCellule(this.getCellule()).size()>1){
            ArrayList<Predateur> predateurs = leLagon.getPredbyCellule(this.getCellule());

                    //On supprime le prédateur attaquant à la liste (il ne peut pas s'attaquer lui m�me)
            predateurs.remove(this);
            int predaVicNum = alea.who(predateurs.size());
            Predateur predaVic = predateurs.get(predaVicNum);
                    //On enlève le poid du prédateur
            predaVic.setPoid(predaVic.getPoid()-(this.getPoid()/getBiteFactor()));
            result += " �chou� --> mordue : " + predaVic.toString();
            //System.out.println(result);
            if (predaVic.getPoid()<=0){
                if((predaVic).getProprietaireEmetteur() != null){
                    (predaVic).getProprietaireEmetteur().deleteEmetteur(predaVic);
                }
                leLagon.deletePoisson(predaVic.getNumero());
            }
        }
        else{
            result += " �chou� --> aucun poisson pr�sent";
        }
        return result;
    }
    //La m�thode getProprietaireEmetteur renvoie proprietaireEmeteur
    public Plongeur getProprietaireEmetteur(){
        return this.proprietaireEmeteur;
    }
    //La m�thode setProprietaireEmetteur modifie proprietaireEmeteur 
    public void setProprietaireEmetteur(Plongeur plongeur){
        this.proprietaireEmeteur = plongeur;
    }
    
/*********************************************************************************************************************/ 

    // M�thode d'instance

    @Override
    public void ticktock(){
        this.setAge(this.getAge()+1);
        this.setPoid(this.getPoid()-1);
    }
    @Override
    public String toString(){
        return "Pr�dateur ---> " + super.toString();
    }
/*********************************************************************************************************************/  
    
    // M�thode static

    //La m�thode setPredatorCloneTime modifie c_PREDATOR_CLONE_TIME
    public static void setPredatorCloneTime(int Valeur){
        c_PREDATOR_CLONE_TIME = Valeur;
    }
    //La m�thode getPredatorCloneTime renvoie c_PREDATOR_CLONE_TIME
    public static int getPredatorCloneTime(){
        return c_PREDATOR_CLONE_TIME;
    }
    //La m�thode setBiteFactor modifie c_BITE_FACTOR
    public static void setBiteFactor(int Valeur){
        c_BITE_FACTOR = Valeur;
    }
    //La m�thode getBiteFactor renvoie c_BITE_FACTOR
    public static int getBiteFactor(){
        return c_BITE_FACTOR;
    }

    
}
