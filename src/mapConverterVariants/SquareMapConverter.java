/**
 * Handles conversion of array to map for square grids
 * @author Andrew
 */
package mapConverterVariants;

import java.util.ArrayList;
import java.util.List;

import cellVariants.Cell;

public class SquareMapConverter extends MapConverter {

	public SquareMapConverter(boolean diag, boolean torus) {
		super(diag, torus);
	}
	/**
	 * Returns the neighboring coordinates for the square. Ignores the isDiagonal parameter
	 * Up to 8 neighbors exist for a square cell; up to 4 if only orthogonal neighbors are considered 
	 */
	@Override
	public List<Cell> calcNeighbors(int row, int col) {
		List<Cell> neighborCoordinates = new ArrayList<>();
		for(int a = row - 1; a <= row + 1; a++) {
			for(int b = col - 1; b <= col + 1; b++) {
				if(a == row && b == col)
					continue;
				if(a == row || b == col || includesDiagonals) {
					addLocation(a, b, neighborCoordinates);
				}
			}
		}
		return neighborCoordinates;
	}

}
