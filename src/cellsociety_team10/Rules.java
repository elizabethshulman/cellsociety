package cellsociety_team10;

import java.util.ArrayList;

public interface Rules {
	/**
	 * When given a grid, return a grid with updated cells per values stored in corresponding FileProcessor
	 * Stores no data
	 * 
	 * Methods:
	 * 		applyGridRules() --> build and return an update array
	 */



	
	
	Graph applyGridRules(Graph g);
	
	Boolean dissatisfied(int state, ArrayList<Cell> neighbors); //true if cell needs to change, false if no
	
	void act(Cell c);
}