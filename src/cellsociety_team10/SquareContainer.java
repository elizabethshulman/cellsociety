package cellsociety_team10;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SquareContainer extends Container {
	private static final double GRID_SIZE = 400;
	private static final int n = 8;
	
	private VBox myVBox;
	private VBox myDisplay;
	
	public SquareContainer() {
		myDisplay = new VBox();
		myDisplay.setId("container-display");
		
		myVBox = new VBox();
		myVBox.setId("container-vbox");
		myVBox.getChildren().add(myDisplay);
	}
	
	public VBox getVBox() {
		return myVBox;
	}
	
//	private HBox buildGraphRow(int r) {
//		String[] arr = new String[]{"yellow.png", "orange.png"};
//		HBox hbox = new HBox();
//		for (int c=0; c < n; c++) {
//			int dex = new Random().nextInt(arr.length);
//			hbox.getChildren().add(Helper.generateImageView(arr[dex], (GRID_SIZE - 2*2) / n));
//		}
//		hbox.setAlignment(Pos.CENTER);
//		hbox.setMaxWidth(0);
//		
//		return hbox;
//	}
//	
//	public void setGraphDisplay(Graph g) {
//		myDisplay.getChildren().clear();
//		for (int r=0; r < n; r++) {
//			myDisplay.getChildren().add(buildGraphRow(r));
//		}
//	}
	
	private HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			row.getChildren().add(graph_grid[r][c]);
		}
		row.setAlignment(Pos.CENTER);
		row.setMaxWidth(0);
		return row;
	}
	
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
			curr_image_view.setFitHeight((GRID_SIZE - 2 * 2) / g.getCols());
			curr_grid[curr.getRow()][curr.getCol()] = curr_image_view;
		}
		return curr_grid;
	}
}
