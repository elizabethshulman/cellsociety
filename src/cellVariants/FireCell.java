package cellVariants;

import javafx.scene.paint.Color;

public class FireCell extends Cell {
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color FOREST_GREEN = Color.rgb(0, 70, 25);
	private static Color BURNT_ORANGE = Color.rgb(165, 66, 2);
	
	public FireCell(int st, String shape) {
		super(st, shape);
	}
	
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, BLACK);
		myStatesAndColors.put(1, FOREST_GREEN);
		myStatesAndColors.put(2, BURNT_ORANGE);
	}
}
