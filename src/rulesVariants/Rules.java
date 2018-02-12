package rulesVariants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cellVariants.Cell;

public abstract class Rules {
	
	/**
	 * When given a grid, return a grid with updated cells per values stored in corresponding FileProcessor
	 */
	
	protected boolean dead=false;
	
	//return updated graph
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
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
	protected void updateDeath(Map<Cell, List<Cell>> g) {		
	}

	//true if cell needs to change state, false if otherwise
	protected Boolean dissatisfied(int state, List<Cell> neighbors) {
		return false;
	}
	
	//change cell state accordingly
	protected void act(Cell c) {
	}

	//true if simulation has run its course
	public boolean simulationIsDead() {
		return dead;
	}
	
	public void resetDead() {
		dead = false;
	}
}