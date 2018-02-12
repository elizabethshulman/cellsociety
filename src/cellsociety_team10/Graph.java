package cellsociety_team10;

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
 * @author benhubsch
 * @author Elizabeth Shulman
 * 
 * This class handles all of the logic on an iteration to iteration basis for a given simulation.
 * It depends heavily on the 
 */
public class Graph {

	private Map<Cell, List<Cell>> currentGrid;
	private Rules myRules;
	private int numRows;
	private int numCols;
	private FileProcessor myFileProcessor;
	protected ContainerFactory myContainerFactory = new ContainerFactory();
	private CellFactory myCellFactory;
	
	
	/**
	 * Instantiates a new Graph object.
	 *
	 * @param file_processor the file processor
	 * @param rules_factory the rules factory
	 * @param cell_factory the cell factory
	 */
	public Graph(FileProcessor file_processor, RulesFactory rules_factory, CellFactory cell_factory) {
		myCellFactory = cell_factory;
		myFileProcessor = file_processor;
		Rules curr_rules = rules_factory.createRules(myFileProcessor.getType(), myFileProcessor.getGlobalVars());
		
		myRules = curr_rules;
		currentGrid = myFileProcessor.getCellGrid();
		numRows = myFileProcessor.getRowCount();
		numCols = myFileProcessor.getColCount();
	}
	
	/**
	 * Adjust rows.
	 *
	 * @param new_rows the new rows
	 */
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
	
	/**
	 * Adjust cols.
	 *
	 * @param new_cols the new cols
	 */
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
	
	/**
	 * Find and add neighbors.
	 *
	 * @param cell the cell
	 * @param neighbor_calc the neighbor calc
	 */
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
	
	/**
	 * Update graph.
	 */
	public void updateGraph() {
		currentGrid = myFileProcessor.getCellGrid();
	}
	
	/**
	 * Builds the next grid.
	 */
	public void buildNextGrid() {
		currentGrid = myRules.applyGraphRules(currentGrid);
	}
	
	/**
	 * Gets the Set<Cell> object.
	 *
	 * @return Set<Cell>
	 */
	//GETTERS
	public Set<Cell> getCells() {
		return currentGrid.keySet();
	}
	
	/**
	 * Gets the List<Cell> object.
	 *
	 * @param c the c
	 * @return List<Cell>
	 */
	public List<Cell> getNeighbors(Cell c) {
		return currentGrid.get(c);
	}
	
	/**
	 * Gets the int object.
	 *
	 * @return int
	 */
	public int getRows() {
		return numRows;
	}
	
	/**
	 * Gets the int object.
	 *
	 * @return int
	 */
	public int getCols() {
		return numCols;
	}

	/**
	 * Checks if is dead.
	 *
	 * @return true, if is dead
	 */
	public boolean isDead() {
		return myRules.simulationIsDead();
	}
	
	/**
	 * Gets the String object.
	 *
	 * @param state the state
	 * @return String
	 */
	public String getCorrectColor(int state) {
		Cell cell = currentGrid.keySet().iterator().next();
		return cell.getCorrespondingColor(state);
	}
	
	/**
	 * Gets the String object.
	 *
	 * @return String
	 */
	public String getCellShape() {
		return myFileProcessor.getCellShape();
	}
	
	/**
	 * Reset is dead.
	 */
	public void resetIsDead() {
		myRules.resetDead();
	}
}
