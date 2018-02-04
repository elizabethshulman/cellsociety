package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SquareContainer extends Container {
	private static final double GRID_SIZE = 400;
	private static final double n = 9;
	
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
//	
//	
//	public void setGraphDisplay(HashMap<Cell, ArrayList<Cell>> cell_map) {
//		myDisplay.getChildren().clear();
//		for (int r=0; r < n; r++) {
//			myDisplay.getChildren().add(buildGraphRow(r));
//		}
//	}
	
	public void setGraphDisplay(Graph g) {
		myDisplay.getChildren().clear();
		ImageView[][] graph_grid = mapToGrid(g);
		for (int c=0; c < g.getCols(); c++) {
			HBox row = new HBox();
			row.setId("container-row");
			for (int r=0; r < g.getRows() / 4; r++) {
				row.getChildren().add(graph_grid[r][c]);
			}
			myDisplay.getChildren().add(row);
		}
	}
	
	private ImageView[][] mapToGrid(Graph g) {
//		need to think about this line...what if it's not a perfect square?
		ImageView[][] curr_grid = new ImageView[g.getRows()][g.getCols()];
		for (Cell curr : g.getCells()) {
			ImageView curr_image_view = curr.getImageView();
			curr_image_view.setPreserveRatio(true);
			curr_image_view.setFitHeight((GRID_SIZE - 2*2) / n);
			curr_grid[curr.getRow()][curr.getCol()] = curr_image_view;
		}
		return curr_grid;
	}
}
