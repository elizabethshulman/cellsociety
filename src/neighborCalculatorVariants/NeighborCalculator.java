/**
 * This class is responsible for calculating the coordinates of neighbors associated with each cell for each simulation.
 * Each subclass is required to cover finite/toroidal borders and orthogonal/adjacent neighbors if applicable.
 * @author Andrew
 */
package neighborCalculatorVariants;

import java.util.List;

public abstract class NeighborCalculator {
	protected int gridRowLength;
	protected int gridColLength;
	protected boolean includesDiagonals;
	protected boolean isToroidal;
	/**
	 * 
	 * @param numRows number of rows in the simulation
	 * @param numCols number of columns in the simulation
	 * @param diag true if the neighbors should include diagonal neighbors; false otherwise
	 * @param torus true if the borders are toroidal
	 */
	public NeighborCalculator(int numRows, int numCols, boolean diag, boolean torus)
	{
		gridRowLength = numRows;
		gridColLength = numCols;
		includesDiagonals = diag;
		isToroidal = torus;
	}
	/**
	 * Sets the number of rows in the simulation to the user input
	 * @param row the updated number of rows in the simulation 
	 */
	public void setRowLength(int row) {
		gridRowLength = row;
	}
	/**
	 * Sets the number of columns in the simulation to the user input
	 * @param col the updated number of rows in the simulation 
	 */
	public void setColLength(int col) {
		gridColLength = col;
	}
	/**
	 * Calculates and returns the coordinates of all neighbors for a cell at the specified row and column
	 * @param row the row where the current cell is located
	 * @param col the column where the current cell is located
	 * @return A list of surrounding coordinates corresponding to the neighbors of a cell
	 */
	public abstract List<int[]> calcNeighborLocations(int row, int col);
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
	 * Adds the corresponding location to the list if the toroidal borders are enabled or the coordinates are valid
	 * @param row the row coordinate to add
	 * @param col the column coordinate to add
	 * @param neighborList the list of current neighbors
	 */
	public void addLocation(int row, int col, List<int[]> neighborList){
		if(isToroidal || isValid(row, col))
			neighborList.add(new int[] {(row + gridRowLength) % gridRowLength,(col + gridColLength)%gridColLength});
	}
}
