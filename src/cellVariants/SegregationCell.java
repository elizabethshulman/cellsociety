package cellVariants;

import javafx.scene.paint.Color;

/**
 * @author elizabethshulman
 *
 * This extension of cell contains the colors relevant to a segregation simulation,
 * plus the values and methods inherited from Cell.
 * Its states are as follows:
 * 		0: empty space
 * 		1: group A
 * 		2: group B
 */
public class SegregationCell extends Cell {
	private static Color MAROON = Color.rgb(128, 0, 0);
	private static Color NAVY = Color.rgb(3, 35, 87);
	private static Color LIGHT_BLUE = Color.rgb(207, 231, 243);
	
	public SegregationCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, LIGHT_BLUE);
		myStatesAndColors.put(1, MAROON);
		myStatesAndColors.put(2, NAVY);
	}
}
