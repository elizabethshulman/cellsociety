package cellVariants;

import javafx.scene.paint.Color;

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
