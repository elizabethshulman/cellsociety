package cellVariants;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage("darkblue.png"));
		statesAndImages.put(1, buildCellImage("midblue.png"));
		
		statesAndColors.put(0, Color.rgb(3, 35, 37));
		statesAndColors.put(1, Color.rgb(1, 59, 57));
	}
}
