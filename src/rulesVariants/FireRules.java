package rulesVariants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cellVariants.Cell;

/*
 * Empty: 0
 * Tree: 1
 * Burning: 2
 */

public class FireRules extends Rules {
	
	private final double probCatchFire;
	private Random randomGenerator = new Random();
	
	public FireRules(Map<String, Double> map) {
		probCatchFire = map.get("probCatchFire")*100.0;	
	}
	
	@Override
	protected Boolean dissatisfied(int state, List<Cell> neighbors) {
		return(state==2 || 
				state==1 && neighborIsBurning(neighbors) && (randomGenerator.nextInt(100)+1<probCatchFire));
	}

	/**
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
	
	//A cell that acts must have state 1 or 2 (tree or burning, respectively). If cell is burning, kill it, otherwise set tree on fire
	@Override
	protected void act(Cell c) {
		if(c.getState()==2) { 
			c.setState(0);
		} else {
			c.setState(2);
		}
	}
	
	@Override
	protected void updateDeath(Map<Cell, ArrayList<Cell>> g) {
		dead=true;
		for(Cell c:g.keySet()) {
			if(c.getState()==2) {
				dead=false;
				return;
			}
		}
	}
}