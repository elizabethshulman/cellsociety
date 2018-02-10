package rulesVariants.RulesVariantsManagers;

import java.util.List;
import java.util.Map;

import cellVariants.PredatorPreyCell;

public abstract class VariantsManager {
	protected Map<PredatorPreyCell, List<PredatorPreyCell>> ocean;
	
	public VariantsManager(Map<PredatorPreyCell, List<PredatorPreyCell>> currentgraph) {
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
