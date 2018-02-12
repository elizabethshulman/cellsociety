package graphVariants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;
import cellVariants.CellFactory;
import cellsociety_team10.FileProcessor;
import rulesVariants.Rules;
import rulesVariants.RulesFactory;
import visualComponents.ContainerFactory;


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
	private FileProcessor myFileProcessor;
	protected ContainerFactory myContainerFactory = new ContainerFactory();
	private CellFactory myCellFactory;
	
	
	public Graph(FileProcessor file_processor, RulesFactory rules_factory, CellFactory cell_factory) {
		myCellFactory = cell_factory;
		myFileProcessor = file_processor;
		Rules curr_rules = rules_factory.createRules(myFileProcessor.getType(), myFileProcessor.getGlobalVars());
		
		myRules = curr_rules;
		currentGrid = myFileProcessor.getCellGrid();
		numRows = myFileProcessor.getRowCount();
		numCols = myFileProcessor.getColCount();
	}
	
	public void adjustRows(int new_rows) {
		Set<Cell> cells = new HashSet<Cell>(currentGrid.keySet());
		myFileProcessor.setRowsAndCols(new_rows, numCols);
		if (new_rows > numRows) {
			for (int r=numRows; r < new_rows; r++) {
				for (int c=0; c < numCols; c++) {
					Cell cell = myCellFactory.createCell(myFileProcessor.getType());
					cell.setRow(r);
					cell.setCol(c);
					cells.add(cell);
				}
			}
		} else {
			List<Cell> toBeRemoved = new ArrayList<>();
			for (Cell cell : getCells()) {
				if (cell.getRow() >= new_rows) {
					toBeRemoved.add(cell);
				}
			}
			for (Cell cell : toBeRemoved) {
				cells.remove(cell);
			}
		}
		numRows = new_rows;
		Cell[][] newGrid = myFileProcessor.createStateGrid(cells);
		myFileProcessor.createCellMap(newGrid);
	}
	
	public void adjustCols(int new_cols) {
		Set<Cell> cells = new HashSet<Cell>(currentGrid.keySet());
		myFileProcessor.setRowsAndCols(numRows, new_cols);
		if (new_cols > numCols) {
			for (int r=0; r < numRows; r++) {
				for (int c=numCols; c < new_cols; c++) {
					Cell cell = myCellFactory.createCell(myFileProcessor.getType());
					cell.setRow(r);
					cell.setCol(c);
					cells.add(cell);
				}
			}
		} else {
			List<Cell> toBeRemoved = new ArrayList<>();
			for (Cell cell : getCells()) {
				if (cell.getCol() >= new_cols) {
					toBeRemoved.add(cell);
				}
			}
			for (Cell cell : toBeRemoved) {
				cells.remove(cell);
			}
		}
		numCols = new_cols;
		Cell[][] newGrid = myFileProcessor.createStateGrid(cells);
		myFileProcessor.createCellMap(newGrid);
	}

	public void updateGraph() {
		currentGrid = myFileProcessor.getCellGrid();
		numRows = myFileProcessor.getRowCount();
		numCols = myFileProcessor.getColCount();
	}
	
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
	
	public String getCorrectColor(int state) {
		Cell cell = currentGrid.keySet().iterator().next();
		return cell.getCorrespondingColor(state);
	}
	
	public String getCellShape() {
		return myFileProcessor.getCellShape();
	}
	
	public void resetIsDead() {
		myRules.resetDead();
	}
}
