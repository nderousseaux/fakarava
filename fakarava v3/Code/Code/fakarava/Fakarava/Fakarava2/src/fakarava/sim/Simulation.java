package fakarava.sim;




import fakarava.control.Fakarava;
import java.util.Arrays;

public class Simulation {
    public static void sim(String args[]){

        //On active le mode verbose si il y a un "-v" dans les param�tres
        Boolean verbose = false;
        if (args.length !=0){
            if (args[0] == "-v"){
                verbose = true;
            }
        }


        //On initialise les variables
        int biteFactor = 1,
                    maxCurrentStrength = 20,
                    maxDensity = 30,
                    n=1,
                    predatorCloneTime = 1000,
                    preyCloneTime = 5;
        
        
        //On initialise la simulation
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime, preyCloneTime, 100l);

        //On cr�e les poissons
        int jojo = Fakarava.createPrey("M�rou", 3.0, 0, 0, 20);
        int lola = Fakarava.createPrey("Poisson Lune", 1.0, 0, 0, 70);
        int alfred = Fakarava.createPrey("Poisson Perroquet", 2.0, 0, 0, 45);
        int bertha = Fakarava.createPredator("Requin Marteau", 100.0, 0, 0);
        int adolphe = Fakarava.createPredator("Requin Gris", 75.0, 0, 0);
        int bertho = Fakarava.createPredator("Requin Marteau", 10.0, 0, 0);
        int adolphie = Fakarava.createPredator("Requin Gris", 45.0, 0, 0);
        //On cr�e les passes
        int p1 = Fakarava.createFishWay(0,0);
        
        //On ajoute des plongeurs
        
        //On ajoute des cam�ras
        
        //On ajoute des �metteurs

        //On avance le temps
        Fakarava.clockForward();
        long time = 1;

        //On lance la simulation tant qu'elle n'est pas finie
        while(! Fakarava.end){
            //Le temps passe
            Fakarava.clockForward();
            time++;
            
            //Mode verbose
            if (verbose){
                System.out.println("\n\n ---------------Demi-Jour suivant--------------- \n");
                for (String chaine : Fakarava.spyReport()){
                    System.out.println(chaine);
                }
            }
        }
        
        //On affiche le score final de la simulation
        System.out.println("\n\n\n Temps �coul� :" + time);
    }
}
