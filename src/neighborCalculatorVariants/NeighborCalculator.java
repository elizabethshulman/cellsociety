package neighborCalculatorVariants;

import java.util.List;

public abstract class NeighborCalculator {
	protected int gridRowLength;
	protected int gridColLength;
	protected boolean includesDiagonals;
	protected boolean isToroidal;
	public NeighborCalculator(int numRows, int numCols, boolean diag, boolean torus)
	{
		gridRowLength = numRows;
		gridColLength = numCols;
		includesDiagonals = diag;
		isToroidal = torus;
	}
	public abstract List<int[]> calcNeighborLocations(int row, int col);
	public boolean isValid(int row, int col) {
		return row >= 0 && row < gridRowLength && col >= 0 && col < gridColLength;
	}
	public void addLocation(int row, int col, List<int[]> neighborList){
		if(isToroidal || isValid(row, col))
			neighborList.add(new int[] {(row + gridRowLength) % gridRowLength,(col + gridColLength)%gridColLength});
	}
}
