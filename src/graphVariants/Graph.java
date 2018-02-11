package graphVariants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;
import cellVariants.CellFactory;
import cellsociety_team10.FileProcessor;
import neighborCalculatorVariants.NeighborCalculator;
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
		NeighborCalculator neighbor_calc = myFileProcessor.getNeighborCalc();
		myFileProcessor.setRowsAndCols(new_rows, numCols);
		if (new_rows > numRows) {
			for (int r=numRows; r < new_rows; r++) {
				for (int c=0; c < numCols; c++) {
					Cell cell = myCellFactory.createCell(myFileProcessor.getType());
					cell.setRow(r);
					cell.setCol(c);
					findAndAddNeighbors(cell, neighbor_calc);
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
				currentGrid.remove(cell);
			}
		}
		numRows = new_rows;
	}
	
	public void adjustCols(int new_cols) {
		NeighborCalculator neighbor_calc = myFileProcessor.getNeighborCalc();
		myFileProcessor.setRowsAndCols(numRows, new_cols);
		if (new_cols > numCols) {
			for (int r=0; r < numRows; r++) {
				for (int c=numCols; c < new_cols; c++) {
					Cell cell = myCellFactory.createCell(myFileProcessor.getType());
					cell.setRow(r);
					cell.setCol(c);
					findAndAddNeighbors(cell, neighbor_calc);
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
				currentGrid.remove(cell);
			}
		}
		numCols = new_cols;
	}
	
	private void findAndAddNeighbors(Cell cell, NeighborCalculator neighbor_calc) {
		List<int[]> l = neighbor_calc.calcNeighborLocations(cell.getRow(), cell.getCol());
		List<Cell> neighbors = new ArrayList<>();
		for (int[] pair : l) {
			for (Cell possible : getCells()) {
				if (possible.getRow() == pair[0] && possible.getCol() == pair[1]) {
					neighbors.add(possible);
				}
			}
		}
		currentGrid.put(cell, neighbors);
		for (Cell possible : neighbors) {
			currentGrid.get(possible).add(cell);
		}
	}
	public void updateGraph() {
		currentGrid = myFileProcessor.getCellGrid();
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
	
	public String getCellShape() {
		return myFileProcessor.getCellShape();
	}
}
