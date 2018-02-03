package rulesVariants;

import java.util.ArrayList;

import cellVariants.Cell;
import cellsociety_team10.Graph;

public abstract class Rules {
	/**
	 * When given a grid, return a grid with updated cells per values stored in corresponding FileProcessor
	 * Stores no data
	 * 
	 * Methods:
	 * 		applyGridRules() --> build and return an update array
	 */

	
	//return updated graph
	protected Graph applyGraphRules(Graph g) {
		ArrayList<Cell> needChange = new ArrayList<Cell>();	
		for(Cell c : g.getCells()) {
			if(dissatisfied(c.getState(), g.getNeighbors(c))) {
				needChange.add(c);
			}
		} for(Cell toAct : needChange) {
			act(toAct);
		}
		return g;
	}
	
	//true if cell needs to change state, false if otherwise
	protected Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		return false;
	}
	
	//change cell state accordingly
	protected void act(Cell c) {
	}
}