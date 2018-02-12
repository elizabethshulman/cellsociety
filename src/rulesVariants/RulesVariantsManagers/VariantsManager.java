package rulesVariants.RulesVariantsManagers;

import java.util.List;
import java.util.Map;

import cellVariants.PredatorPreyCell;

/**
 * 
 * @author elizabethshulman
 *
 * This is an abstract helper class for the PredatorPrey simulation. It handles
 * PredatorPrey-specific logic relevant to both sharks and fish. It is extended by
 * PredatorManager and PreyManager classes.
 */
public abstract class VariantsManager {
	protected Map<PredatorPreyCell, List<PredatorPreyCell>> ocean;
	
	public VariantsManager(Map<PredatorPreyCell, List<PredatorPreyCell>> currentgraph) {
		ocean = currentgraph;
	}
	
	/**
	 * Responds to reproductive values stored in cell by reproducing if necessary.
	 * @param c		cell with potential to reproduce
	 */
	protected void handleReproduction(PredatorPreyCell c) {
		if(c.getReproduce()==true) {
			c.setReproduce(false);
			c.setSharkEnergy(0);
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
	
	/**
	 * Determines where cell's next location should be
	 * @param c		cell seeking movement
	 * @return reference to cell c's next location in the graph
	 */
	protected abstract PredatorPreyCell whereToMove(PredatorPreyCell c); 
}
