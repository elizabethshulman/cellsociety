package cellsociety_team10;

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
 * @author benhubsch
 * @author Elizabeth Shulman
 * 
 * This class handles all of the logic on an iteration to iteration basis for a given simulation.
 * It has a reasonably heavy dependency on FileProcessor myFileProcessor, which Graph uses in the
 * constructor and in updating the grid on dynamic changes in DIY mode from the user.
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
	 * @param file_processor
	 * @param rules_factory
	 * @param cell_factory
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
	 * This function adjusts the number of rows on the visualization when the user sets
	 * them dynamically in DIY mode.
	 *
	 * @param new_rows The number of new rows.
	 */
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
		updateGraph(cells);
	}
	
	/**
	 * This function adjusts the number of columns on the visualization when the user sets
	 * them dynamically in DIY mode.
	 *
	 * @param new_cols The number of new columns.
	 */
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
		updateGraph(cells);
	}

	/**
	 * This function updates the Graph with the latest settings that the user
	 * enables dynamically in DIY mode. 
	 */
	public void updateGraph(Set<Cell> cells) {
		Cell[][] cellGrid = new GridConverter().createStateGrid(cells, numRows, numCols);
		currentGrid = myFileProcessor.getMapConverter().generateMapFromGrid(cellGrid);
	}
	
	/**
	 * Builds the next iteration of the grid given the appropriate rules set.
	 */
	public void buildNextGrid() {
		currentGrid = myRules.applyGraphRules(currentGrid);
	}
	
	/**
	 * Gets the Set<Cell> object.
	 *
	 * @return Set<Cell>
	 */
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
	 * Gets the number of rows for this simulation.
	 *
	 * @return int
	 */
	public int getRows() {
		return numRows;
	}
	
	/**
	 * Gets the number of columns for this simulation.
	 *
	 * @return int
	 */
	public int getCols() {
		return numCols;
	}

	/**
	 * Checks if the simulation has reached its conclusion, which is used
	 * to stop the animation and disable buttons.
	 *
	 * @return true, if is concluded
	 */
	public boolean isDead() {
		return myRules.simulationIsDead();
	}
	
	/**
	 * Gets the String object representing the color for a given state.
	 *
	 * @param state
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
	 * Reset the rules so that a simulation can be re-run after concluding.
	 */
	public void resetIsDead() {
		myRules.resetDead();
	}
}
