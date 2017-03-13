package fr.univ_lyon1.ter.utilitaire;

import java.util.Comparator;

import fr.univ_lyon1.ter.modele.Site;

public class CompareSite  implements Comparator<Site> {
	@Override
    public int compare(Site o1, Site o2) {
        // write comparison logic here like below , it's just a sample
        return Integer.compare(o2.getPertinence(),o1.getPertinence());    
    }
}
