package fr.univ_lyon1.ter.override;

import java.util.Comparator;

import fr.univ_lyon1.ter.modele.Site;

public class CompareSite  implements Comparator<Site> {
	@Override
    public int compare(Site o1, Site o2) {
        // On retourne le site avec la plus haute pertinence
        return Integer.compare(o2.getPertinence(),o1.getPertinence());    
    }
}
