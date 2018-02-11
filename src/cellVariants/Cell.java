package cellVariants;

import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class Cell {

	protected int myState;
	protected HashMap<Integer, Color> statesAndColors = new HashMap<>();
	private int row;
	private int col;
	private String myShape;
	
	public Cell(int st, String shape) {
		myShape = shape;
		myState = st;
		buildHashMap();
	}
	
	protected String getShapeType() {
		return myShape;
	}

	protected abstract void buildHashMap();
	
	public Color getColor() {
		return statesAndColors.get(myState);
	}
	
	public void nextColor(Polygon polygon) {
		myState = (myState + 1) % statesAndColors.size();
		polygon.setFill(statesAndColors.get(myState));
		setState(myState);
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
		return myState;
	}
	
	public void setState(int state) {
		myState = state;
	}
	
	public int getStateCount() {
		return statesAndColors.size();
	}
	
	public String getCorrespondingColor(int state) {
		Color color = statesAndColors.get(state);
		return String.format("rgb(%s, %s, %s)", rgbS(color.getRed()), rgbS(color.getGreen()), rgbS(color.getBlue()));
	}
	
	private String rgbS(Double d) {
		return String.valueOf((int) (d * 255));
	}
}