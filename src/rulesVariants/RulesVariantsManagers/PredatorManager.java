package rulesVariants.RulesVariantsManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cellVariants.PredatorPreyCell;

/**
 * 
 * @author elizabethshulman
 *
 * This class contains predator/shark-specific logic necessary for the PredatorPrey simulation.
 * It is primarily used in PredatorPreyRules, and it is an extension of the 
 * PredatorPrey helper class VariantsManager.
 */
public class PredatorManager extends VariantsManager {

	private double sharkStarveTime;
	
	/**
	 * This constructor overrides the VariantsManager constructor to indicate
	 * sharkStarveTime
	 * 
	 * @param currentgraph	This is the current simulation graph.
	 * @param starveTime		This is the number of rounds without food at which a shark dies of starvation.
	 */
	public PredatorManager(Map<PredatorPreyCell, List<PredatorPreyCell>> currentgraph,
			double starveTime) {
		super(currentgraph);
		sharkStarveTime = starveTime;
	}
	
	/**
	 * This method manages changes to the states of the sharks.
	 */
	public void manageSharks() {
		updateSharkEnergy();
	}
	
	/**
	 * This method increases the energy value of a shark.
	 * As shark energy increases, a shark becomes closer to death.
	 * If a cell's shark energy surpasses sharkStarveTime, this method kills the shark.
	 */
	private void updateSharkEnergy() {
		for(PredatorPreyCell c:ocean.keySet()) {
			if (c.getState() == 2) {
				c.increaseSharkEnergy();
			} if(c.getSharkEnergy() >= sharkStarveTime) {
				c.setState(0);
			}
		}
	}
	
	/**
	 * This method moves sharks across the simulation board.
	 * 
	 * @param c			This is the shark in need of movement.
	 * @param neighbors	These are the cells surrounding the shark.
	 */
	public void moveSharks(PredatorPreyCell c, List<PredatorPreyCell> neighbors) {
		PredatorPreyCell cellToMoveTo = whereToMove(c);
		if(cellToMoveTo.equals(c)) { //indicates no possible movement options
			c.setReproductiveTime(c.getReproductiveTime() + 1);
			return; 
		}
		if(cellToMoveTo.getState() == 1) {
			cellToMoveTo.setSharkEnergy(c.getSharkEnergy() - 2);
		} else {
			cellToMoveTo.setSharkEnergy(c.getSharkEnergy());
		}
		cellToMoveTo.setState(2);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime() + 1);
		handleReproduction(c);
	}
	
	/**
	 * This method determines the next location of the shark.
	 * 
	 * @param c		shark moving
	 * @return 		next cell location for the shark
	 */
	protected PredatorPreyCell whereToMove(PredatorPreyCell c){
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<>();
		ArrayList<PredatorPreyCell> fishOptions = new ArrayList<>();
		for(PredatorPreyCell n : ocean.get(c)) {
			if(n.getState() == 0 && (!n.hasMovedThisTurn())) {
				emptyOptions.add(n);
			} else if (n.getState() == 1 && (!n.hasMovedThisTurn())) {
				fishOptions.add(n);
			}
		}
		PredatorPreyCell cellToMoveTo = c;
		if(fishOptions.isEmpty()) {
			if(emptyOptions.isEmpty()) {
				return c;
			} else {
				Collections.shuffle(emptyOptions); //randomize fish movement
				cellToMoveTo = emptyOptions.remove(emptyOptions.size() - 1);
			}
		} else {
			Collections.shuffle(fishOptions);
			cellToMoveTo = fishOptions.remove(fishOptions.size() - 1);
		}
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
}