package cellVariants;

import javafx.scene.paint.Color;

public class SegregationCell extends Cell {
	public SegregationCell(int st, String shape) {
		super(st, shape);
	}
	
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, Color.rgb(195, 224, 229));
		myStatesAndColors.put(1, Color.rgb(3, 35, 37));
		myStatesAndColors.put(2, Color.rgb(1, 59, 57));
	}
}
