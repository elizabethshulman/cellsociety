package cellsociety_team10;

import java.util.ArrayList;

public abstract class Rules {
	/**
	 * When given a grid, return a grid with updated cells per values stored in corresponding FileProcessor
	 * Stores no data
	 * 
	 * Methods:
	 * 		applyGridRules() --> build and return an update array
	 */

	
	//return updated graph
	Graph applyGraphRules(Graph g) {
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
	Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		return null;
	}
	
	//change cell state accordingly
	void act(Cell c) {
	}
}