package cellsociety_team10;

import java.util.ArrayList;
import java.util.List;

public class SquareNeighborCalculator extends NeighborCalculator {

	public SquareNeighborCalculator(int numRows, int numCols, boolean diag, boolean torus) {
		super(numRows, numCols, diag, torus);
	}

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
