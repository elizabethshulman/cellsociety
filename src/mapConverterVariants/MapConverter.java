/**
 * This class is responsible for converting a 2D array of cells into a Map of Cells to their neighbors.
 * Each subclass is required to cover finite/toroidal borders and orthogonal/adjacent neighbors if applicable.
 * @author Andrew Yeung
 */
package mapConverterVariants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellVariants.Cell;

public abstract class MapConverter {
	private Cell[][] myArray;
	private int gridRowLength;
	private int gridColLength;
	protected boolean includesDiagonals;
	protected boolean isToroidal;
	/**
	 * 
	 * @param diag true if the neighbors should include diagonal neighbors; false otherwise
	 * @param torus true if the borders are toroidal
	 */
	public MapConverter(boolean torus, boolean diag) {
		includesDiagonals = diag;
		isToroidal = torus;
	}
	/**
	 * Converts a 2D array of Cells into a Map of Cells to their neighbors
	 * @param cellArray the 2D grid of cells
	 */
	public Map<Cell,List<Cell>> generateMapFromGrid(Cell[][] cellArray) {
		myArray = cellArray;
		gridRowLength = myArray.length;
		gridColLength = myArray[0].length;
		Map<Cell,List<Cell>> cellsAndNeighbors = new HashMap<>();
		for(int a = 0; a < gridRowLength; a++) {
			for(int b = 0; b < gridColLength; b++) {
				List<Cell> neighbors = calcNeighbors(a,b);
				cellsAndNeighbors.put(myArray[a][b],neighbors);
			}
		}
		return cellsAndNeighbors;
	}
	/**
	 * Calculates and returns the list of all cell neighbors for a cell at the specified row and column
	 * @param row the row where the current cell is located
	 * @param col the column where the current cell is located
	 * @return A list of neighbors of a cell
	 */
	public abstract List<Cell> calcNeighbors(int row, int col);
	/**
	 * Checks whether the specified coordinates are valid in the simulation; 
	 * @param row the row coordinate
	 * @param col the column coordinate
	 * @return true if the coordinates correspond to a valid spot in the grid
	 */
	public boolean isValid(int row, int col) {
		return row >= 0 && row < gridRowLength && col >= 0 && col < gridColLength;
	}
	/**
	 * Adds the corresponding Cell to the list if the toroidal borders are enabled or the coordinates are valid
	 * @param row the row coordinate to add
	 * @param col the column coordinate to add
	 * @param neighborList the list of current neighbors
	 */
	protected void addLocation(int row, int col, List<Cell> neighborList){
		if(isToroidal || isValid(row, col))
			neighborList.add(myArray[(row + gridRowLength) % gridRowLength]
									[(col + gridColLength) % gridColLength]);
	}
	

}
