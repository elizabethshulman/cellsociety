package graphVariants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;
import cellsociety_team10.FileProcessor;
import rulesVariants.Rules;
import rulesVariants.RulesFactory;
import visualComponents.Container;
import visualComponents.ContainerFactory;


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
	protected ContainerFactory myContainerFactory = new ContainerFactory();
	
	
	public Graph(FileProcessor file_processor, RulesFactory rules_factory) {
		myFileProcessor = file_processor;
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
	public Set<Cell> getCells() {
		return currentGrid.keySet();
	}
	
	public List<Cell> getNeighbors(Cell c) {
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
	
	public String getCorrectColor(int state) {
		Cell cell = currentGrid.keySet().iterator().next();
		return cell.getCorrespondingColor(state);
	}
	
	private String hex(double color) {
		int col = (int) (color *255);
        String col_string = Integer.toHexString(col);
        return col_string;
	}
	
	public abstract Container createContainer();
}
