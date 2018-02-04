package rulesVariants;

import java.util.ArrayList;
import java.util.HashMap;

import cellVariants.Cell;

public abstract class Rules {
	/**
	 * When given a grid, return a grid with updated cells per values stored in corresponding FileProcessor
	 */
	
	protected boolean dead=false;
	
	//return updated graph
	public HashMap<Cell, ArrayList<Cell>> applyGraphRules(HashMap<Cell,ArrayList<Cell>> g) {
		ArrayList<Cell> needChange = new ArrayList<Cell>();	
		for(Cell c : g.keySet()) {
			if(dissatisfied(c.getState(), g.get(c))) {
				needChange.add(c);
			}
		} for(Cell toAct : needChange) {
			act(toAct);
		}
		updateDeath(g);
		return g;
	}
	
	//check for simulation death & update boolean
	protected void updateDeath(HashMap<Cell, ArrayList<Cell>> g) {		
	}

	//true if cell needs to change state, false if otherwise
	protected Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		return false;
	}
	
	//change cell state accordingly
	protected void act(Cell c) {
	}

	//true if simulation has run its course
	public boolean simulationIsDead() {
		return dead;
	}

}