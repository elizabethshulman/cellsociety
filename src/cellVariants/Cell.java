package cellVariants;

import java.util.HashMap;

import cellsociety_team10.Helper;
import javafx.scene.image.ImageView;

public abstract class Cell {

	protected int state;
	protected HashMap<Integer, ImageView> statesAndColors;
	private int row;
	private int col;
	
	public Cell(int st) {
		state = st;
		statesAndColors = new HashMap<Integer, ImageView>();
		buildHashMap();
	}

	protected void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("lightblue.png"));
		statesAndColors.put(1, Helper.generateImageView("midblue.png"));
		statesAndColors.put(2, Helper.generateImageView("burgundy.png"));
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