package rulesVariants;

import java.util.ArrayList;
import java.util.Random;

import cellVariants.Cell;
import cellsociety_team10.FileProcessor;


//Is it better to override the protected methods in Rules when applyGraphRules
//must be overridden, or to create new private methods?

/**
 * Empty: 0
 * Tree: 1
 * Burning: 2
 */
public class FireRules extends Rules {
	
	private final double probCatch;
	private Random randomGenerator = new Random();
	
	public FireRules(FileProcessor fp) {
		probCatch = fp.getGlobalVars().get("probCatch"); //confirm with Andrew	
	}
	
	@Override
	protected Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		return(state==2 || 
				(state==1 && neighborIsBurning(neighbors) 
				&& (randomGenerator.nextInt(100)+1)>probCatch));
	}

	/**
	 * @param neighbors		ArrayList of cells neighboring current cell
	 * @return true if at least one neighbor is burning
	 * Currently considering all cell neighbors, regardless of 2D N/S/E/W specification
	 */
	private boolean neighborIsBurning(ArrayList<Cell> neighbors) {
		for(Cell c:neighbors) {
			if(c.getState()==2) return true;
		}
		return false;
	}
	
	/**
	 * A cell that acts must have state 1 or 2 (tree or burning, respectively)
	 * If cell is burning, kill it
	 * Otherwise, set tree on fire
	 */
	@Override
	protected void act(Cell c) {
		if(c.getState()==2) { 
			c.setState(0);
		} else {
			c.setState(2);
		}
	}
}