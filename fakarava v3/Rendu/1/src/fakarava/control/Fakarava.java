package fakarava.control;

import fakarava.ecosystem.*;

import java.util.ArrayList;

public class Fakarava {

    public static boolean isDay = true;
    public static boolean end = false;


    private static int maxDensity;
    private static Lagon leLagon;
    private static Long seed;


    //Initialisation
    public static void init(int maxDensity, int n, int predatorCloneTime, int preyCloneTime, Long seed){
        //Générateur d'aléatoire
        Random.mySeed = seed;

        leLagon = new Lagon(n);
        Lagon.setc_MAX_DENSITY(maxDensity);
        Predateur.setPredatorCloneTime(predatorCloneTime);
        Proie.setPreyCloneTime(preyCloneTime);

    }

    public static int createPredator(String name, double weight, int x, int y){
        return leLagon.newPredateur(name, weight, x, y);
    }

    public static int createPrey(String name, double weight, int x, int y, int dayVivacity){
        return leLagon.newProie(name, weight, x, y, dayVivacity);
    }

    public static void clockForward(){
        //Les poissons gagnent un d'age, puis se reproduise le cas échant
        leLagon.tempsPasse();

        //Le jour devient la nuit ou l'inverse
        isDay = !isDay;


        if(leLagon.getNbPoisson() > Lagon.getc_MAX_DENSITY()*leLagon.getNbCellule()){
            end = true;
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
}