package cellVariants;

import javafx.scene.paint.Color;

public class FireCell extends Cell {
//	hashmap of states to rgb values 
	public FireCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage("black.png"));
		statesAndImages.put(1, buildCellImage("forestgreen.png"));
		statesAndImages.put(2, buildCellImage("burntorange.png"));
		
		statesAndColors.put(0, Color.BLACK);
		statesAndColors.put(1, Color.rgb(0, 70, 25));
		statesAndColors.put(2, Color.rgb(165, 66, 2));
	}
}
