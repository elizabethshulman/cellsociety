package cellsociety_team10;

import java.util.Set;

import cellVariants.Cell;

public class GridConverter {
	/**
	 * Creates a 2D array from a Set of Cells
	 * @param cells a set of all the cells in the grid
	 * @return 2D array corresponding to state of simulation
	 */
	public Cell[][] createStateGrid(Set<Cell> cells, int numRows, int numCols) {
		Cell[][] arrangement = new Cell[numRows][numCols];
		for(Cell c: cells) {
			arrangement[c.getRow()][c.getCol()] = c;
		}
		return arrangement;
	}
}
