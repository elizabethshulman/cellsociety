package cellVariants;

import javafx.scene.paint.Color;

/**
 * @author elizabethshulman
 *
 * This extension of cell contains the colors relevant to a fire simulation,
 * plus the values and methods inherited from Cell.
 * Its states are as follows:
 * 		0: scorched land
 * 		1: live forest
 * 		2: burning forest
 */
public class FireCell extends Cell {
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color FOREST_GREEN = Color.rgb(0, 70, 25);
	private static Color BURNT_ORANGE = Color.rgb(165, 66, 2);
	
	public FireCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, BLACK);
		myStatesAndColors.put(1, FOREST_GREEN);
		myStatesAndColors.put(2, BURNT_ORANGE);
	}
}
