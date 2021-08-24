package fakarava.ecosystem;

import java.util.Comparator;

public class ComparateurProie implements Comparator<Proie> {
    public ComparateurProie() {
        super();
    }

    @Override
    public int compare(Proie proie1, Proie proie2) {
        return proie1.getVivacite()-proie2.getVivacite();
    }
}
