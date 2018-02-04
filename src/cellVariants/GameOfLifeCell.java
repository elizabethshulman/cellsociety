package cellVariants;

import java.util.HashMap;

import cellsociety_team10.Helper;
import javafx.scene.image.ImageView;

public class GameOfLifeCell extends Cell {
	

	public GameOfLifeCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("darkblue.png"));
		statesAndColors.put(1, Helper.generateImageView("midblue.png"));
	}
}
