package fr.univ_lyon1.ter.override;


import es.usc.citius.hipster.model.Node;
import es.usc.citius.hipster.model.impl.WeightedNode;

/*
 * On redefinit la méthode toString.
 * Méchanisme de composition utilisé
 */
@SuppressWarnings("rawtypes")
public class ResultatRecherche{
	
	private WeightedNode wn;
	
	public ResultatRecherche(Node node){
		this.wn = (WeightedNode)node;
	}
	
	@Override
	public String toString(){
		return wn.getCost().toString();
	}
}
