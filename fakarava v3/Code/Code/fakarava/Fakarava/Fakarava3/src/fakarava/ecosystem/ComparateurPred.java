package fakarava.ecosystem;

import java.util.Comparator;

public class ComparateurPred implements Comparator<Predateur> {

	//Constructeurs
    public ComparateurPred() {
        super();
    }

    //Méthode d'instance
    @Override
    public int compare(Predateur pred1, Predateur pred2) {
        return (int)(pred2.getPoid()-pred1.getPoid());
    }
}
