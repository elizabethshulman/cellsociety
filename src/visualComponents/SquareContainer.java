package visualComponents;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SquareContainer extends Container {
	private VBox myGrid;
	private VBox myDisplay;
	
	public SquareContainer() {
		myDisplay = new VBox();
		myDisplay.setId("container-display-square");
		
		myGrid = new VBox();
		myGrid.setId("container-vbox");
		myGrid.getChildren().add(myDisplay);
	}
	
	@Override
	public VBox getContainer() {
		return myGrid;
	}
	
	private HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			row.getChildren().add(graph_grid[r][c]);
		}
		row.setId("container-row");
		return row;
	}
	
	@Override
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
			curr_image_view.setFitHeight(calcSquareHeight(g.getCols()));
			curr_grid[curr.getRow()][curr.getCol()] = curr_image_view;
		}
		
		return curr_grid;
	}

	/* Uses deprecated BigDecimal rounding division but works like a charm!
	 * Necessary to prevent gaps between squares resulting from imprecise 
	 * double division.
	 */
	private double calcSquareHeight(int num_cols) {
		BigDecimal cols = new BigDecimal(num_cols);
		return Container.GRID_SIZE.divide(cols, BigDecimal.ROUND_UP).doubleValue();
	}
}
