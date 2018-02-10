package cellsociety_team10;

import java.util.ArrayList;
import java.util.List;

public class HexagonNeighborCalculator extends NeighborCalculator {

	public HexagonNeighborCalculator(int numRows, int numCols, boolean isDiagonal, boolean isTorus) {
		super(numRows, numCols, isDiagonal, isTorus);
	}

	@Override
	public List<int[]> calcNeighborLocations(int row, int col) {
		
		ArrayList<int[]> neighborLocs = new ArrayList<>();
		for(int x = row - 2; x <= row + 2; x++)
		{
			if(x == row)
				continue;
			addLocation(x,col,neighborLocs);
				
		}
		int shift_constant = 2 * (row % 2) - 1;
		for(int x = row - 1; x <= row + 1; x++)
		{
			addLocation(x,col + shift_constant,neighborLocs);
		}
		return neighborLocs;
	}

}
