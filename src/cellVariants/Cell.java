package cellVariants;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import visualComponents.Helper;

public abstract class Cell {

	protected int state;
	protected HashMap<Integer, Image> statesAndImages;
	protected HashMap<Integer, Color> statesAndColors;
	private int row;
	private int col;
	protected ImageView imageView;
	
	public Cell(int st) {
		state = st;
		statesAndImages = new HashMap<>();
		buildHashMap();
		imageView = new ImageView(statesAndImages.get(state));
		imageView.setOnMouseClicked(e -> {
			nextImage();
		});
	}

	protected abstract void buildHashMap();
	
	public Color getCorrespondingColor(Integer state) {
		return statesAndColors.get(state);
	}
	
	protected void nextImage() {
		state = (state + 1) % statesAndImages.size();
		setState(state);
		imageView.setImage(statesAndImages.get(state));
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
		imageView.setImage(statesAndImages.get(st));
	}
	
	public ImageView getImageView() {
		return imageView;
	}
}