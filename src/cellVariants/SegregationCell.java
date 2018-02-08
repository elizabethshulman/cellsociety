package cellVariants;

import visualComponents.Helper;

public class SegregationCell extends Cell {
	public SegregationCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, buildCellImage("lightblue.png"));
		statesAndColors.put(1, buildCellImage("darkblue.png"));
		statesAndColors.put(2, buildCellImage("midblue.png"));
	}
}
