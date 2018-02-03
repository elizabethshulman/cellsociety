package graphVariants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import cellVariants.PredatorPreyCell;
import rulesVariants.Rules;

public class PredatorPreyGraph extends Graph {

	HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> currentGrid;
	
	public PredatorPreyGraph(Rules rules) {
		super(rules);
		instantiate();
	}

	private void instantiate() {
		//ask Andrew about what incoming cell data looks like
		//update (build initial map) when input is clear (maybe a rules.getCellCount()?)
		currentGrid = new HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>>();
	}
}
