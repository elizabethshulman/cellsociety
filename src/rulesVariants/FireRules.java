package rulesVariants;

import java.util.List;
import java.util.Map;
import java.util.Random;

import cellVariants.Cell;

/**
 * 
 * @author elizabethshulman
 *
 * This class manages the logic of the Fire Burning simulation. Its primary purpose
 * is to take in a graph, spread and extinguish fire, check if the simulation has 
 * run its course, and return the updated graph. It assumes states 0 for dead; 1
 * for live tree; 2 for burning.
 */
public class FireRules extends Rules {
	
	private final double probCatchFire;
	private Random randomGenerator = new Random();
	
	public FireRules(Map<String, Double> map) {
		probCatchFire = map.get("probCatchFire")*100.0;	
	}
	
	

	@Override
	/**
	 * This method indicates whether or not a cell needs to change its state
	 */
	protected Boolean dissatisfied(int state, List<Cell> neighbors) {
		return(state==2 || 
				state==1 && neighborIsBurning(neighbors) && (randomGenerator.nextInt(100)+1<probCatchFire));
	}

	/**
	 * This method checks if a cell has caught fire.
	 * 
	 * @param neighbors		ArrayList of cells neighboring current cell
	 * @return true if at least one neighbor is burning
	 */
	private boolean neighborIsBurning(List<Cell> neighbors) {
		for(Cell c:neighbors) {
			if(c.getState()==2) {
				return true;
			}
		}
		return false;
	}
	

	@Override
	/**
	 * This method adjusts a cell's state; a cell that acts must have 
	 * state 1 or 2 (tree or burning, respectively). If cell is burning,
	 * kill it, otherwise set tree on fire.
	 * 
	 * @param c		This is the cell changing state.
	 */
	protected void act(Cell c) {
		if(c.getState()==2) { 
			c.setState(0);
		} else {
			c.setState(2);
		}
	}
	

	@Override
	/**
	 * This method indicates the fire has ceased burning.
	 * 
	 * @param g	This is the simulation field.
	 */
	protected void updateDeath(Map<Cell, List<Cell>> g) {
		dead=true;
		for(Cell c:g.keySet()) {
			if(c.getState()==2) {
				dead=false;
				return;
			}
		}
	}
}