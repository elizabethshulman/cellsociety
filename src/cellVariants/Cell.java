package cellVariants;

import java.util.HashMap;

import javafx.scene.image.ImageView;
import visualComponents.Helper;

public abstract class Cell {

	protected int state;
	protected HashMap<Integer, ImageView> statesAndColors;
	private int row;
	private int col;
	
	public Cell(int st) {
		state = st;
		statesAndColors = new HashMap<>();
		buildHashMap();
	}

	protected void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("darkblue.png"));
		statesAndColors.put(1, Helper.generateImageView("midblue.png"));
		statesAndColors.put(2, Helper.generateImageView("lightblue.png"));
	}

	public int getRow() {
		return row;
	}

	public void setRow(int r) {
		row = r;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int c) {
		col = c;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int st) {
		state = st;
	}
	
	public ImageView getImageView() {
		return statesAndColors.get(state);
	}
}