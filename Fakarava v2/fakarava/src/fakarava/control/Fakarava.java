package fakarava.control;

import fakarava.ecosystem.*;

import java.util.ArrayList;


public class Fakarava{
    /**
     * @attribute
     */
    public Boolean end;

    /**
     * @attribute
     */
    private Boolean isDay;
    
    

    public static void clockForward() {
        // attendre la classe Clock.java qui utilisera le Boolean isday pour savoir si 
        //c'est le jour ou nn
    }

    public static int createFishWay(Integer x, Integer y) {
        proie.get
        predateur.get
        // Donnera la position du poisson mais il faut la fonction
    }
    public static int createPredator(String name, double weight, int x, int y){
            return leLagon.newPredateur(name, weight, x, y);
        }

    public static int createPrey(String name, Real weight, Integer x, Integer y, Integer vivacity) {
        
        return return leLagon.newProie(name, weight, x, y, vivacity);
    }

    public static void init (Integer maxDensity, Integer n,Integer predatorCloneTime, Integer preyCloneTime, Integer seed) 
    {
        /* NE pas oublier dans les paramètre Integer biteFactor,Integer maxCurrentStrength */
        Proie.setPreyCloneTime(preyCloneTime);
        Predateur.setPredatorCloneTime(predatorCloneTime);
        leLagon = new Lagon(n);
        leLagon.setc_MAX_DENSITY(maxDensity);
    }
}
