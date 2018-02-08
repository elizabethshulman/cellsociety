package rulesVariants.PredatorPreyHelper;

import java.util.ArrayList;
import java.util.Map;

import cellVariants.PredatorPreyCell;

public abstract class Manager {
	protected Map<PredatorPreyCell, ArrayList<PredatorPreyCell>> ocean;
	
	public Manager(Map<PredatorPreyCell, ArrayList<PredatorPreyCell>> currentgraph) {
		ocean = currentgraph;
	}
	
	protected void handleReproduction(PredatorPreyCell c) {
		if(c.getReproduce()==true) {
			c.setReproduce(false);
			c.setSharkEnergy(0);
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
	
	protected abstract PredatorPreyCell whereToMove(PredatorPreyCell c); 
}
