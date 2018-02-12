package cellVariants;

import javafx.scene.paint.Color;
/**
 * @author elizabethshulman
 *
 * This extension of cell contains the colors relevant to a GameOfLife simulation,
 * plus the values and methods inherited from Cell.
 */
public class GameOfLifeCell extends Cell {
	private static Color NAVY = Color.rgb(3, 35, 87);
	private static Color TURQUOISE = Color.rgb(1, 159, 157);

	public GameOfLifeCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, NAVY);
		myStatesAndColors.put(1, TURQUOISE);
	}
}
