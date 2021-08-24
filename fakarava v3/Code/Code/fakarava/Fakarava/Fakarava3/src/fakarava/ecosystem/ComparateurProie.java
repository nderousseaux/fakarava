package fakarava.ecosystem;

import java.util.Comparator;
    // Constructeur
public class ComparateurProie implements Comparator<Proie> {
    public ComparateurProie() {
        super();
    }
    
/*********************************************************************************************************************/

    // M�thode d'instance
    @Override
    public int compare(Proie proie1, Proie proie2) {
        return proie1.getVivacite()-proie2.getVivacite();
    }
}
