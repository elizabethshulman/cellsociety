package cellVariants;

import javafx.scene.paint.Color;

public class SegregationCell extends Cell {
	public SegregationCell(int st, String shape) {
		super(st, shape);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Color.rgb(195, 224, 229));
		statesAndColors.put(1, Color.rgb(3, 35, 37));
		statesAndColors.put(2, Color.rgb(1, 59, 57));
	}
}
