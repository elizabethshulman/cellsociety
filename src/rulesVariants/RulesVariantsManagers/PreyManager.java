package rulesVariants.RulesVariantsManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cellVariants.PredatorPreyCell;

/**
 * 
 * @author elizabethshulman
 *
 * This class contains fish/prey-specific logic necessary for the PredatorPrey simulation.
 * It is primarily used in PredatorPreyRules, and it is an extension of the 
 * PredatorPrey helper class VariantsManager.
 */
public class PreyManager extends VariantsManager {
	
	public PreyManager(HashMap<PredatorPreyCell, List<PredatorPreyCell>> currentgraph) {
		super(currentgraph);
	}
	
	/**
	 * This method handles fish movement across the current simulation graph.
	 * @param c		fish moving
	 */
	public void moveFish(PredatorPreyCell c) {
		PredatorPreyCell cellToMoveTo = whereToMove(c);
		if(c.equals(cellToMoveTo)) {
			return;
		}
		cellToMoveTo.setState(1);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime() + 1);
		handleReproduction(c);
	}
	
	/**
	 * This method determines where the given cell will move to next, per the fish-specific rules.
	 * 
	 * @param c		fish moving
	 * @return 		next cell location for the fish
	 */
	protected PredatorPreyCell whereToMove(PredatorPreyCell c){
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<>();
		for(PredatorPreyCell n : ocean.get(c)) {
			if(n.getState() == 0 && !n.hasMovedThisTurn()) {
				emptyOptions.add(n);
			}
		}
		if(emptyOptions.isEmpty()) {
			return c;
		}
		Collections.shuffle(emptyOptions); //randomize fish movement
		PredatorPreyCell cellToMoveTo = emptyOptions.remove(emptyOptions.size() - 1);
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
}