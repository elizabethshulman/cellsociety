/**
 * Handles neighbor calculation for hexagonal grids
 * @author Andrew
 */
package neighborCalculatorVariants;

import java.util.ArrayList;
import java.util.List;

public class HexagonNeighborCalculator extends NeighborCalculator {

	public HexagonNeighborCalculator(int numRows, int numCols, boolean isDiagonal, boolean isTorus) {
		super(numRows, numCols, isDiagonal, isTorus);
	}
	/**
	 * Returns the neighboring coordinates for the hexagonal cell. Ignores the isDiagonal parameter
	 * Up to 6 neighbors exist for a hexagonal cell 
	 */
	@Override
	public List<int[]> calcNeighborLocations(int row, int col) {
		ArrayList<int[]> neighborLocs = new ArrayList<>();
		int shift_constant = 2* (col % 2) - 1;
		for(int x = row - 1; x <= row + 1; x++)
		{
			for(int y = col - 1; y <= col + 1; y++) {
				if((row == x ^ col == y) || x == row + shift_constant)
					addLocation(x,y,neighborLocs);
			}
				
		}
		return neighborLocs;
	}

}
