package fakarava;


import fakarava.control.Fakarava;

import fakarava.sim.Simulation;

import java.util.Arrays;

public class main {
    public static void main(String[] args){
        /*int biteFactor = 10,
            maxCurrentStrength = 20,
            maxDensity = 3,
            n=4,
            predatorCloneTime = 10,
            preyCloneTime = 5;
        Fakarava.init(maxDensity, n, predatorCloneTime,preyCloneTime,null);
        int jojo = Fakarava.createPrey("Mérou", 3.0, 1, 2, 79);
        int lola = Fakarava.createPrey("Poisson lune", 1.0, 0, 1, 90);
        int alfred = Fakarava.createPrey("Poisson Peroquet", 2.0, 0, 3, 51);
        int bertha = Fakarava.createPredator("requin Marteau", 100.0, 1, 2);
        int adolphe = Fakarava.createPredator("requin Gris", 75.0, 0, 2);
        System.out.println(Arrays.toString(Fakarava.spyReport()));
        Fakarava.clockForward();
        System.out.println(Arrays.toString(Fakarava.spyReport()));
        Fakarava.clockForward();
        System.out.println(Arrays.toString(Fakarava.spyReport()));
        Fakarava.clockForward();
        System.out.println(Arrays.toString(Fakarava.spyReport()));
        Fakarava.clockForward();
        System.out.println(Arrays.toString(Fakarava.spyReport()));
        Fakarava.clockForward();
        */
       // args[0]="-v";
        String[] tab = new String[1];
        tab[0] = "-v";
        Simulation.sim(tab);
    }
}
