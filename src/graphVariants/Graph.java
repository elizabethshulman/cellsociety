package graphVariants;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;
import cellsociety_team10.FileProcessor;
import rulesVariants.Rules;
import rulesVariants.RulesFactory;


/**
 * 
 *	Constructor takes in initial grid & ruleset
 *	Stores cell/neighbor map
 *
 */
public abstract class Graph {

	private Map<Cell, List<Cell>> currentGrid;
	private Rules myRules;
	private int numRows;
	private int numCols;
	private FileProcessor myFileProcessor;
	
	
	public Graph(File file, RulesFactory rules_factory) {
		try {
			myFileProcessor = new FileProcessor(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid filepath.");
		}

		Rules curr_rules = rules_factory.createRules(myFileProcessor.getType(), myFileProcessor.getGlobalVars());
		
		myRules = curr_rules;
		currentGrid = myFileProcessor.getCellGrid();
		numRows = myFileProcessor.getRowCount();
		numCols = myFileProcessor.getColCount();
	}
	
	public abstract void adjustRows(int new_rows);
	
	public abstract void adjustCols(int new_cols);
	
	public void buildNextGrid() {
		currentGrid = myRules.applyGraphRules(currentGrid);
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
		return myRules.simulationIsDead();
	}
	
	public String getTitle() {
		return myFileProcessor.getTitle();
	}
	public String getAuthor() {
		return myFileProcessor.getAuthor();
	}
}
