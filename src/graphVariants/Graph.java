package graphVariants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;
import cellsociety_team10.FileProcessor;
import rulesVariants.Rules;


/**
 * 
 *	Constructor takes in initial grid & ruleset
 *	Stores cell/neighbor map
 *
 */
public class Graph {

	private Map<Cell, List<Cell>> currentGrid;
	private Rules myRules;
	private int numRows;
	private int numCols;
	private boolean dead=false;
	
	
	public Graph(Rules rules, FileProcessor fp) {
		myRules = rules;
		currentGrid = fp.getCellGrid();
		numRows = fp.getRowCount();
		numCols = fp.getColCount();
	}
	
	
	public void buildNextGrid() {
		currentGrid = myRules.applyGraphRules(currentGrid);
		if(myRules.simulationIsDead()) {
			dead=true;
		}
	}
	
	
	
	//GETTERS
	public Set<Cell> getCells(){
		return currentGrid.keySet();
	}
	
	public List<Cell> getNeighbors(Cell c){
		return currentGrid.get(c);
	}
	
	public int getRows() {
		return numRows;
	}
	public int getCols() {
		return numCols;
	}

	public boolean isDead() {
		return dead;
	}
}
