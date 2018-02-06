package cellVariants;

import visualComponents.Helper;

public class FireCell extends Cell {
	public FireCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("black.png"));
		statesAndColors.put(1, Helper.generateImageView("forestgreen.png"));
		statesAndColors.put(2, Helper.generateImageView("burntorange.png"));
	}
}
