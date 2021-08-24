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

    //Méthodes 

    //La métode isIn est un boolean vérifie si le predateur est dans la cellule
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
    //La méthode attaque gère l'attaque des prédatueur
    public String attaque(boolean isDay) {
        String result="Attaque de "+this.toString()+" result ";
    	//On rÃ©cupÃ©re le lagon (objet que l'on va souvent utiliser)
    	Lagon leLagon = this.getCellule().getLagon();
    	//On determine la force du courrant
    	double forceDuCourrant = Lagon.getc_Max_Current_Strenght()/leLagon.getNbPasses();
    	//On dÃ©termine quel proie on va attaquer !
        Proie victime = leLagon.getMinVivacite(this.getCellule());
        //On determine la probabilitÃ©e de survie
        int vivacite = victime.getVivacite();
        if(!isDay){
            vivacite/=2;
        }
        double p = Math.max(0, vivacite/this.getPoid() - forceDuCourrant/100.0);
        //On teste si oui ou non le prÃ©dateur rÃ©ussi son attaque
        Random alea = Random.getARandom();
        //Si oui, on supprime le poisson
        if(alea.selection(p)){
            leLagon.deletePoisson(victime.getNumero());
            //ON ajoute le poid du poisson à l'attaquant
            this.setPoid(this.getPoid()+victime.getPoid());
            result += " réussie --> victime : " + victime.toString();
        }
        //Sinon on cherche un predateur à attaquer 
        else if (leLagon.getPredbyCellule(this.getCellule()).size()>1){
            ArrayList<Predateur> predateurs = leLagon.getPredbyCellule(this.getCellule());

                    //On supprime le prÃ©dateur attaquant Ã  la liste (il ne peut pas s'attaquer lui même)
            predateurs.remove(this);
            int predaVicNum = alea.who(predateurs.size());
            Predateur predaVic = predateurs.get(predaVicNum);
                    //On enlÃ¨ve le poid du prÃ©dateur
            predaVic.setPoid(predaVic.getPoid()-(this.getPoid()/getBiteFactor()));
            result += " échoué --> mordue : " + predaVic.toString();
            //System.out.println(result);
            if (predaVic.getPoid()<=0){
                if((predaVic).getProprietaireEmetteur() != null){
                    (predaVic).getProprietaireEmetteur().deleteEmetteur(predaVic);
                }
                leLagon.deletePoisson(predaVic.getNumero());
            }
        }
        else{
            result += " échoué --> aucun poisson présent";
        }
        return result;
    }
    //La méthode getProprietaireEmetteur renvoie proprietaireEmeteur
    public Plongeur getProprietaireEmetteur(){
        return this.proprietaireEmeteur;
    }
    //La méthode setProprietaireEmetteur modifie proprietaireEmeteur 
    public void setProprietaireEmetteur(Plongeur plongeur){
        this.proprietaireEmeteur = plongeur;
    }
    
/*********************************************************************************************************************/ 

    // Méthode d'instance

    @Override
    public void ticktock(){
        this.setAge(this.getAge()+1);
        this.setPoid(this.getPoid()-1);
    }
    @Override
    public String toString(){
        return "Prédateur ---> " + super.toString();
    }
/*********************************************************************************************************************/  
    
    // Méthode static

    //La méthode setPredatorCloneTime modifie c_PREDATOR_CLONE_TIME
    public static void setPredatorCloneTime(int Valeur){
        c_PREDATOR_CLONE_TIME = Valeur;
    }
    //La méthode getPredatorCloneTime renvoie c_PREDATOR_CLONE_TIME
    public static int getPredatorCloneTime(){
        return c_PREDATOR_CLONE_TIME;
    }
    //La méthode setBiteFactor modifie c_BITE_FACTOR
    public static void setBiteFactor(int Valeur){
        c_BITE_FACTOR = Valeur;
    }
    //La méthode getBiteFactor renvoie c_BITE_FACTOR
    public static int getBiteFactor(){
        return c_BITE_FACTOR;
    }

    
}
