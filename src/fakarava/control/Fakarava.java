package fakarava.control;

import fakarava.ecosystem.*;

import java.util.ArrayList;

public class Fakarava {

    //Initialise des variables statique
    
    public static boolean isDay = true;
    public static boolean end = false;
    
/*********************************************************************************************************************/

    //Déclaration des paramètre de la simulation

    private static int maxDensity;
    private static Lagon leLagon;
    private static Long seed;
    private static int temps = 0;


/*********************************************************************************************************************/

    //Methode

    public static void init(int biteFactor, int maxCurrentStrength, int maxDensity,
                            int n, int predatorCloneTime, int preyCloneTime, Long seed){
        Predateur.setBiteFactor(biteFactor);
        Lagon.setc_Max_Current_Strenght(maxCurrentStrength);
        Lagon.setc_MAX_DENSITY(maxDensity);
        leLagon = new Lagon(n);
        Predateur.setPredatorCloneTime(predatorCloneTime);
        Proie.setPreyCloneTime(preyCloneTime);
        //GÃ©nÃ©rateur d'alÃ©atoire
        Random.mySeed = seed;
    }
    //Création d'un Prédateur
    public static int createPredator(String name, double weight, int x, int y){
        return leLagon.newPredateur(name, weight, x, y);
    }
    //Création d'une Proie
    public static int createPrey(String name, double weight, int x, int y, int dayVivacity){
        return leLagon.newProie(name, weight, x, y, dayVivacity);
    }
    //Création d'un FishWay
    public static int createFishWay(int x, int y){
        return leLagon.newPasse(leLagon.getCellule(x, y));
    }
    //Création d'un plongeur
    public static int createDiver(String nom, String labo){
        return GestionnairePlongeurs.addPlongeur(nom,labo,leLagon);
    }
    //Suppression d'un plongeur
    public static void deleteDiver(int plongeur){
        GestionnairePlongeurs.deletePlongeur(plongeur);
        
    //Création d'une caméra
    }
    public static void putCamera(int plongeur, int passe){//"c intPasse"
        GestionnairePlongeurs.getPlongeur(plongeur).addToDoList("c "+passe);
    }
    
    //Création d'un transmetteur
    public static void putTransmitters(int plongeur, int predateur){ //"t intPred"
        GestionnairePlongeurs.getPlongeur(plongeur).addToDoList("t "+predateur);
    }
    public static void clockForward(){
        //Le jour devient la nuit ou l'inverse
        isDay = !isDay;
        
        //Les poissons gagnent un d'age, puis se reproduise le cas Ã©chant
        leLagon.tempsPasse(isDay);
        //Les plongeurs effectue leur todoList
        GestionnairePlongeurs.tempsPasse(isDay, leLagon, temps);

        if(leLagon.getNbPoisson() > Lagon.getc_MAX_DENSITY()*leLagon.getNbCellule()){
            end = true;
        }
        
        if(leLagon.getNbPoisson()<= 0){
            end=true;
        }
        if(GestionnairePlongeurs.getNbPlongeurs() <=0){
            end=true;
            }
        
        temps++;
    }

    public static String[] spyReport(){
        String[] retour = new String[leLagon.getPoissons().size()];
        int i =0;
        
        for (Poisson poisson: leLagon.getPoissons()) {
            retour[i] = poisson.toString();
            i++;
        }
        return retour;
    }

}