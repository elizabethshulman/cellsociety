package cellVariants;

import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class Cell {

	protected int myState;
	protected HashMap<Integer, Color> myStatesAndColors = new HashMap<>();
	private int myRow;
	private int myCol;

	public Cell(int st) {
		buildHashMap();
	}

	protected abstract void buildHashMap();
	
	public Color getColor() {
		return myStatesAndColors.get(myState);
	}
	
	public void nextColor(Polygon polygon) {
		myState = (myState + 1) % myStatesAndColors.size();
		polygon.setFill(myStatesAndColors.get(myState));
		setState(myState);
	}
	
	public int getRow() {
		return myRow;
	}

	public void setRow(int r) {
		myRow = r;
	}

	public int getCol() {
		return myCol;
	}

	public void setCol(int c) {
		myCol = c;
	}
	
	public int getState() {
		return myState;
	}
	
	public void setState(int state) {
		myState = state;
	}
	
	public int getStateCount() {
		return myStatesAndColors.size();
	}
	
	public String getCorrespondingColor(int state) {
		Color color = myStatesAndColors.get(state);
		return String.format("rgb(%s, %s, %s)", rgbS(color.getRed()), rgbS(color.getGreen()), rgbS(color.getBlue()));
	}
	
	private String rgbS(Double d) {
		return String.valueOf((int) (d * 255));
	}
	
	public void setRandom() {
		setState(new Random().nextInt(myStatesAndColors.keySet().size()));
	}
}