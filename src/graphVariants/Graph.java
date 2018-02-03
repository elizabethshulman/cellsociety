package graphVariants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import cellVariants.Cell;
import rulesVariants.Rules;


/**
 * 
 *	Constructor takes in initial grid & ruleset
 *	Stores cell/neighbor map
 *
 */
public class Graph {

	HashMap<Cell, ArrayList<Cell>> currentGrid;
	Rules myRules;
	
	public Graph(Rules rules) {
		myRules = rules;
		instantiate();
	}
	
	private void instantiate() {
		//ask Andrew about what incoming cell data looks like
		//update (build initial map) when input is clear (maybe a rules.getCellCount()?)
		currentGrid = new HashMap<Cell, ArrayList<Cell>>();
	}
	
	
	
	
	//GETTERS
	public Set<Cell> getCells(){
		return currentGrid.keySet();
	}
	
	public ArrayList<Cell> getNeighbors(Cell c){
		return currentGrid.get(c);
	}
}
