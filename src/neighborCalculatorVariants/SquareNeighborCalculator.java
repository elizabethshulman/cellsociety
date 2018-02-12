/**
 * Handles neighbor calculation for square grids
 * @author Andrew
 */
package neighborCalculatorVariants;

import java.util.ArrayList;
import java.util.List;

public class SquareNeighborCalculator extends NeighborCalculator {

	public SquareNeighborCalculator(int numRows, int numCols, boolean diag, boolean torus) {
		super(numRows, numCols, diag, torus);
	}
	/**
	 * Returns the neighboring coordinates for the square. Ignores the isDiagonal parameter
	 * Up to 8 neighbors exist for a square cell; up to 4 if only orthogonal neighbors are considered 
	 */
	@Override
	public List<int[]> calcNeighborLocations(int row, int col) {
		List<int[]> neighborCoordinates = new ArrayList<>();
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
