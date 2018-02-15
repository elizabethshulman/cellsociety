/**
 * Handles map conversion for hexagonal grids
 * @author Andrew Yeung
 */
//The shift constant represents whether the column is odd or even.
// Column odd/even parity affects the relation of the rows/cols corresponding to neighbors to the given cell.
package mapConverterVariants;

import java.util.ArrayList;
import java.util.List;

import cellVariants.Cell;

public class HexagonMapConverter extends MapConverter {

	public HexagonMapConverter(boolean isDiagonal, boolean isTorus) {
		super(isDiagonal, isTorus);
	}
	/**
	 * Returns the neighboring coordinates for the hexagonal cell. Ignores the isDiagonal parameter
	 * Up to 6 neighbors exist for a hexagonal cell 
	 */
	@Override
	public List<Cell> calcNeighbors(int row, int col) {
		ArrayList<Cell> neighborLocs = new ArrayList<>();
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
