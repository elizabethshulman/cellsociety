package cellVariants;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import visualComponents.Helper;

public abstract class Cell {

	protected int state;
	protected HashMap<Integer, Image> statesAndColors;
	private int row;
	private int col;
	private ImageView imageView;
	
	public Cell(int st) {
		state = st;
		statesAndColors = new HashMap<Integer, Image>();
		buildHashMap();
		imageView = new ImageView(statesAndColors.get(state));
		imageView.setOnMouseClicked(e -> {
			nextImage();
		});
	}

	protected abstract void buildHashMap();
	
	protected void nextImage() {
		state = (state + 1) % statesAndColors.size();
		setState(state);
		imageView.setImage(statesAndColors.get(state));
	}
	
	protected Image buildCellImage(String filename) {
		Image temp = Helper.generateImage(filename);
		return temp;
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
		imageView.setImage(statesAndColors.get(st));
	}
	
	public ImageView getImageView() {
		return imageView;
	}
}