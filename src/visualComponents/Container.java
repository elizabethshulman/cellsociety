package visualComponents;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

/**
 * @author benhubsch
 * 
 * The Class Container, which is an abstract class that holds the visual graph
 * objects themselves. Visualization centers it on the page, and all it needs is
 * the Graph object to run.
 */
public abstract class Container {
	public static final double GRID_SIZE = 350.0;
	
	protected VBox myVBox = new VBox();
	protected Group myDisplay  = new Group();
	protected Polygon[][] myPolygonArr;
	
	/**
	 * Instantiates a new container.
	 */
	public Container() {
		myVBox.setId("container-vbox");
		myVBox.getChildren().add(myDisplay);
	}
	
	/**
	 * Gets the container.
	 *
	 * @return VBox
	 */
	public VBox getContainer() {
		return myVBox;
	};
	
	/**
	 * Sets the graph display, drawing it on screen and setting all the
	 * click handlers for changing color.
	 *
	 * @param g
	 */
	public void setGraphDisplay(Graph g) {
		myDisplay.getChildren().clear();
		myPolygonArr = new Polygon[g.getRows()][g.getCols()];
		drawGraph(g);
		setColorAndClick(g);
	}
	
	/**
	 * Sets the color of an object on click.
	 *
	 * @param g
	 */
	protected void setColorAndClick(Graph g) {
		for (Cell cell : g.getCells()) {
			int r = cell.getRow();
			int c = cell.getCol();
			Polygon match = myPolygonArr[r][c];
			match.setFill(cell.getColor());
			match.setOnMouseClicked(e -> cell.nextColor(match));
		}
	}
	
	/**
	 * This function draws the graph. Given that each shape has a different way to
	 * draw the grid, the implementation is delegated to the subclasses.
	 *
	 * @param g
	 */
	protected abstract void drawGraph(Graph g);
}
