package visualComponents;

import java.math.BigDecimal;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Container {
	public static final BigDecimal GRID_SIZE = new BigDecimal(400);
	
	protected VBox myVBox;
	protected VBox myDisplay;
	
	public Container() {
		myDisplay = new VBox();
		
		myVBox = new VBox();
		myVBox.setId("container-vbox");
		myVBox.getChildren().add(myDisplay);
	}
	
	public VBox getContainer() {
		return myVBox;
	};
	
	public void setGraphDisplay(Graph g) {
		myDisplay.getChildren().clear();
		ImageView[][] graph_grid = mapToGrid(g);
		for (int r=0; r < g.getRows(); r++) {
			myDisplay.getChildren().add(buildGraphRow(graph_grid, r, g.getCols()));
		}
	}
	
	private ImageView[][] mapToGrid(Graph g) {
		ImageView[][] curr_grid = new ImageView[g.getRows()][g.getCols()];
		for (Cell curr : g.getCells()) {
			ImageView curr_image_view = curr.getImageView();
			curr_image_view.setPreserveRatio(true);
			curr_image_view.setFitHeight(calcShapeHeight(g.getCols()));
			curr_grid[curr.getRow()][curr.getCol()] = curr_image_view;
		}
		return curr_grid;
	}
	
	protected abstract double calcShapeHeight(int num_cols);
	
	protected abstract HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols);
}
