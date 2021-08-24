package fakarava.sim;




import fakarava.control.Fakarava;

import java.util.Arrays;

public class Simulation {
    public static void sim(String args[]){

        Boolean verbose = false;
        if (args.length !=0){
            if (args[0] == "-v"){
                verbose = true;
            }
        }


        //On initialise les variables
        int maxDensity = 3, n = 4, predatorCloneTime = 10, preyCloneTime = 5;

        //On crée la simulation
        Fakarava.init(maxDensity, n, predatorCloneTime, preyCloneTime, null);

        //On crée les objets
        int jojo = Fakarava.createPrey("Mérou", 3.0, 1, 2, 99);
        int lola = Fakarava.createPrey("Poisson Lune", 1.0, 0, 1, 90);
        int alfred = Fakarava.createPrey("Poisson Perroquet", 2.0, 0, 3, 51);
        int bertha = Fakarava.createPredator("Requin Marteau", 100.0, 1, 2);
        int adolphe = Fakarava.createPredator("Requin Gris", 75.0, 0, 2);

        Fakarava.clockForward();
        long time = 1;

        while(! Fakarava.end){
            Fakarava.clockForward();
            time++;
            if (verbose){
                for (String chaine : Fakarava.spyReport()){
                    System.out.println(chaine);
                }
                System.out.println("\n **jour suivant** \n");
            }
        }
        System.out.println(time);
    }
}
