package visualComponents;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

public abstract class Container {
	public static final double GRID_SIZE = 350.0;
	
	protected VBox myVBox = new VBox();
	protected Group myDisplay  = new Group();
	protected Polygon[][] myPolygonArr;
	
	public Container() {
		myVBox.setId("container-vbox");
		myVBox.getChildren().add(myDisplay);
	}
	
	public VBox getContainer() {
		return myVBox;
	};
	
	public void setGraphDisplay(Graph g) {
		myDisplay.getChildren().clear();
		myPolygonArr = new Polygon[g.getRows()][g.getCols()];
		drawGraph(g);
		setColorAndClick(g);
	}
	
	protected void setColorAndClick(Graph g) {
		for (Cell cell : g.getCells()) {
			int r = cell.getRow();
			int c = cell.getCol();
			Polygon match = myPolygonArr[r][c];
			match.setFill(cell.getColor());
			match.setOnMouseClicked(e -> cell.nextColor(match));
		}
	}
	
	protected abstract void drawGraph(Graph g);
}
