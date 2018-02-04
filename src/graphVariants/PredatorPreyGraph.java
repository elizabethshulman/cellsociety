package graphVariants;

import java.util.ArrayList;
import java.util.HashMap;

import cellVariants.PredatorPreyCell;
import cellsociety_team10.FileProcessor;
import rulesVariants.Rules;

public class PredatorPreyGraph extends Graph {

	HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> currentGrid;
	
	public PredatorPreyGraph(Rules rules, FileProcessor fp) {
		super(rules, fp);
		instantiate();
	}

	private void instantiate() {
		//ask Andrew about what incoming cell data looks like
		//update (build initial map) when input is clear (maybe a rules.getCellCount()?)
		currentGrid = new HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>>();
	}
}
