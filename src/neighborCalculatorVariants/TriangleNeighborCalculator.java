/**
 * Handles neighbor calculation for triangular grids
 * @author Andrew
 */
package neighborCalculatorVariants;

import java.util.ArrayList;
import java.util.List;

public class TriangleNeighborCalculator extends NeighborCalculator {

	public TriangleNeighborCalculator(int numRows, int numCols, boolean isDiagonal, boolean isTorus) {
		super(numRows, numCols, isDiagonal, isTorus);
	}
	/**
	 * Returns the neighboring coordinates for the square. Ignores the isDiagonal parameter
	 * Up to 12 neighbors exist for a triangular cell; up to 3 if only orthogonal neighbors are considered 
	 */
	@Override
	public List<int[]> calcNeighborLocations(int row, int col) {
		List<int[]> neighborLocs = new ArrayList<>();
		int shift_constant = 1 - 2*((row+col) % 2);
		if(!includesDiagonals)
		{
			for(int a = col - 1; a <= col + 1; a++)
			{
				if(a == col)
					addLocation(row + shift_constant,a,neighborLocs);
				else 
					addLocation(row,a,neighborLocs);
			}
			return neighborLocs;
		}
		for(int b = col - 2; b <= col + 2; b++)
		{
			for(int a = row; a != row + 2*shift_constant; a += shift_constant)
			{
				if(a == row && b == col)
					continue;
				addLocation(a,b,neighborLocs);
			}
		}
		for(int b = col -1; b <= col + 1; b++)
		{
			addLocation(row - shift_constant,b,neighborLocs);
		}
		return neighborLocs;
	}

}
