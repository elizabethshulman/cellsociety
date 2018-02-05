package cellVariants;

import visualComponents.Helper;

public class SegregationCell extends Cell {
	public SegregationCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("lightblue.png"));
		statesAndColors.put(1, Helper.generateImageView("darkblue.png"));
		statesAndColors.put(2, Helper.generateImageView("midblue.png"));
	}
}
