package cellsociety_team10;

import java.util.ArrayList;

public class Rules {

	/**
	 * When given a grid, return a grid with updated cells per values stored in corresponding FileProcessor
	 * Stores no data
	 * 
	 * should rules be an interface??? how much to keep in Rules, how much to apply to extensions
	 * what of this comes from fileProcessor
	 * 
	 * Methods:
	 * 		applyGridRules() --> build and return an update array
	 */
	
	private ArrayList<Cell> needChange;
	private FileProcessor specifications;
	
	
	public Rules(FileProcessor fp) {
		
	}
	
	
	
	
	
	/**
	 * takes in grid, returns grid with updated cells per rules set
	 */
	public Graph applyGridRules(Graph g) {
		
		for(Cell c : g.getCells()) {
			if(dissatisfied(c.getState(), g.getNeighbors(c))) {
				needChange.add(c);
			}
		} for(Cell toAct : needChange) {
			act(toAct);
		}
		return g;
	}
	
	
	/**
	 * @param state
	 * @param neighbors
	 * @return true if cell needs to change, false if no
	 */
	private Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
//		int ct = 0;
//		for(Cell c:neighbors) if (c.getState()==state) ct+=1;
//		if(ct<specifications.getSatisfactionThreshold()) return true;
		
		return false;
	}
	
	
	
	private void act(Cell c) {
	}
}
