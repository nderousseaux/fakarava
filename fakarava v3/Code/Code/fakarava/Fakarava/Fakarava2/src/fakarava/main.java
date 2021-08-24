package fakarava;


import fakarava.control.Fakarava;

import fakarava.sim.Simulation;

import java.util.Arrays;

public class main {
    public static void main(String[] args){
        
        //On simule le mode verbose
        String[] tab = new String[1];
        tab[0] = "-v";
        
        //On apelle notre simulation
        Simulation.sim(tab);
    }
}
