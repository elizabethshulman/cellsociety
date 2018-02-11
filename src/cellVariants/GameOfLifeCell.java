package cellVariants;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int st, String shape) {
		super(st, shape);
	}
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Color.rgb(3, 35, 87));
		statesAndColors.put(1, Color.rgb(1, 159, 157));
	}
}
