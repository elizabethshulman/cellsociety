package cellVariants;

import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * 
 * @author elizabethshulman
 * @author benhubsch
 * 
 * This class contains the information pertinent to a cell's identity. These values
 * include its current state, color, and location within the graph.
 */
public abstract class Cell {

	protected int myState;
	protected HashMap<Integer, Color> myStatesAndColors = new HashMap<>();
	private int myRow;
	private int myCol;

	public Cell(int st) {
		myState = st;
		buildHashMap();
	}

	/**
	 * This method maps states to their respective colors.
	 */
	protected abstract void buildHashMap();
	
	/**
	 * This method returns the color corresponding to a state.
	 * @return color to be used for visualization
	 */
	public Color getColor() {
		return myStatesAndColors.get(myState);
	}
	
	/**
	 * Increases the state of the polygon by one and modifies the color accordingly.
	 * @param polygon	Shape being modified
	 */
	public void nextColor(Polygon polygon) {
		myState = (myState + 1) % myStatesAndColors.size();
		polygon.setFill(myStatesAndColors.get(myState));
		setState(myState);
	}
	
	/**
	 * Gets the vertical location of this cell.
	 * @return the row in which cell is located
	 */
	public int getRow() {
		return myRow;
	}

	/**
	 * Updates the row value for this cell's location
	 * @param r 	 	Current row value
	 */
	public void setRow(int r) {
		myRow = r;
	}

	/**
	 * Gets the horizontal location of this cell.
	 * @return the column in which cell is located
	 */
	public int getCol() {
		return myCol;
	}

	/**
	 * Updates the column value for this cell's location
	 * @param c 	 	Current column value
	 */
	public void setCol(int c) {
		myCol = c;
	}
	
	/**
	 * Gets this current state
	 * @return current state for this cell
	 */
	public int getState() {
		return myState;
	}
	
	/**
	 * Updates this cell's current state
	 * @param state		New value for myState
	 */
	public void setState(int state) {
		myState = state;
	}
	
	/**
	 * Retrieves the rgb value for the color corresponding to a particular state
	 * @param state 	the state for which a color is desired 
	 * @return	string representing the color corresponding to state
	 */
	public String getCorrespondingColor(int state) {
		Color color = myStatesAndColors.get(state);
		return String.format("rgb(%s, %s, %s)", rgbS(color.getRed()), rgbS(color.getGreen()), rgbS(color.getBlue()));
	}
	
	/**
	 * Converts double to String rgb value
	 * @param d 		value to be converted
	 * @return String rgb value
	 */
	private String rgbS(Double d) {
		return String.valueOf((int) (d * 255));
	}
	
	/**
	 * Sets a state to a random value within the range of possible states
	 */
	public void setRandom() {
		setState(new Random().nextInt(myStatesAndColors.keySet().size()));
	}
}