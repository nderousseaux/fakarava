package fakarava.control;

import fakarava.ecosystem.*;

import java.util.ArrayList;

public class Fakarava {

    //Initialise les variables statique
    public static boolean isDay = true;
    public static boolean end = false;

    //D�clare les param�tre de la simulation
    private static int maxDensity;
    private static Lagon leLagon;
    private static Long seed;


    //Initialisation
    public static void init(int biteFactor, int maxCurrentStrength, int maxDensity,
                            int n, int predatorCloneTime, int preyCloneTime, Long seed){
        Predateur.setBiteFactor(biteFactor);
        Lagon.setc_Max_Current_Strenght(maxCurrentStrength);
        Lagon.setc_MAX_DENSITY(maxDensity);
        leLagon = new Lagon(n);
        Predateur.setPredatorCloneTime(predatorCloneTime);
        Proie.setPreyCloneTime(preyCloneTime);
        //Générateur d'aléatoire
        Random.mySeed = seed;
    }

    public static int createPredator(String name, double weight, int x, int y){
        return leLagon.newPredateur(name, weight, x, y);
    }

    public static int createPrey(String name, double weight, int x, int y, int dayVivacity){
        return leLagon.newProie(name, weight, x, y, dayVivacity);
    }
    
    public static int createFishWay(int x, int y){
        return leLagon.newPasse(leLagon.getCellule(x, y));
    }

    public static void clockForward(){
        //Le jour devient la nuit ou l'inverse
        isDay = !isDay;
        
        //Les poissons gagnent un d'age, puis se reproduise le cas échant
        leLagon.tempsPasse(isDay);


        if(leLagon.getNbPoisson() > Lagon.getc_MAX_DENSITY()*leLagon.getNbCellule()){
            end = true;
        }
        
        if(leLagon.getNbPoisson()<= 0){
            end=true;
        }
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
    public static String[] spyReportCel(int x,int y){
    String[] retour = new String[leLagon.getPoissonbyCellule(leLagon.getCellule(x, y)).size()];
    int i =0;
    for (Poisson poisson: leLagon.getPoissonbyCellule(leLagon.getCellule(x, y))) {
        retour[i] = poisson.toString();
        i++;
    }
    return retour;
    }
}