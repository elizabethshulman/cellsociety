package cellVariants;

import javafx.scene.paint.Color;

public class FireCell extends Cell {
//	hashmap of states to rgb values 
	public FireCell(int st, String shape) {
		super(st, shape);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Color.rgb(0,0,0));
		statesAndColors.put(1, Color.rgb(0, 70, 25));
		statesAndColors.put(2, Color.rgb(165, 66, 2));
	}
}
