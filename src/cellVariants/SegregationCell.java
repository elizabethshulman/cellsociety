package cellVariants;

import javafx.scene.paint.Color;

public class SegregationCell extends Cell {
	private static Color MAROON = Color.rgb(128, 0, 0);
	private static Color NAVY = Color.rgb(3, 35, 87);
	private static Color LIGHT_BLUE = Color.rgb(207, 231, 243);
	
	public SegregationCell(int st, String shape) {
		super(st, shape);
	}
	
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, LIGHT_BLUE);
		myStatesAndColors.put(1, MAROON);
		myStatesAndColors.put(2, NAVY);
	}
}
