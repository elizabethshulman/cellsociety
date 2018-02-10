package cellVariants;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import visualComponents.Helper;

public abstract class Cell {

	protected int state;
	protected HashMap<Integer, Image> statesAndImages = new HashMap<>();
	protected HashMap<Integer, String> statesAndColors = new HashMap<>();
	private int row;
	private int col;
	protected ImageView imageView;
	private String myShape;
	
	public Cell(int st, String shape) {
		myShape = shape;
		state = st;
		buildHashMap();
		imageView = new ImageView(statesAndImages.get(state));
		imageView.setOnMouseClicked(e -> {
			nextImage();
		});
	}
	
	protected String getShapeType() {
		return myShape;
	}

	protected abstract void buildHashMap();
	
	public String getCorrespondingColor(Integer state) {
		return statesAndColors.get(state);
	}
	
	protected void nextImage() {
		state = (state + 1) % statesAndImages.size();
		setState(state);
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
	
	public int getStateCount() {
		return statesAndColors.size();
	}
}