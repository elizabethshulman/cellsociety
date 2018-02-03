package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SquareContainer extends Container {
	private static final double GRID_SIZE = 400;
	private static final double n = 9;
	
	private VBox myVBox;
	private VBox myGraph;
	
	public SquareContainer() {
		myGraph = new VBox();
		myGraph.setId("container-display");
		
		myVBox = new VBox();
		myVBox.setId("container-vbox");
		myVBox.getChildren().add(myGraph);
	}
	
	private HBox buildGraphRow(int r) {
		String[] arr = new String[]{"yellow.png", "orange.png"};
		HBox hbox = new HBox();
		for (int c=0; c < n; c++) {
			int dex = new Random().nextInt(arr.length);
			hbox.getChildren().add(Helper.generateImageView(arr[dex], (GRID_SIZE - 2*2) / n));
		}
		hbox.setAlignment(Pos.CENTER);
		hbox.setMaxWidth(0);
		
		return hbox;
	}
	
	public VBox getVBox() {
		return myVBox;
	}
	
	public void setGraphDisplay(HashMap<Cell, ArrayList<Cell>> cell_map) {
		myGraph.getChildren().clear();
		for (int r=0; r < n; r++) {
			myGraph.getChildren().add(buildGraphRow(r));
		}
	}
	
//	public void setGraphDisplay(HashMap<Cell, ArrayList<Cell>> cell_map) {
//		myGraph.getChildren().clear();
//		ImageView[][] graph_grid = mapToGrid(cell_map);
//		for (int c=0; c < cell_map.size() / 4; c++) {
//			HBox row = new HBox();
//			row.setId("container-row");
//			for (int r=0; r < cell_map.size() / 4; r++) {
//				row.getChildren().add(graph_grid[r][c]);
//			}
//			myGraph.getChildren().add(row);
//		}
//	}
//	
//	private ImageView[][] mapToGrid(HashMap<Cell, ArrayList<Cell>> cell_map) {
////		need to think about this line...what if it's not a perfect square?
//		ImageView[][] curr_grid = new ImageView[cell_map.size() / 4][cell_map.size() / 4];
//		for (Cell curr : cell_map.keySet()) {
//			curr_grid[curr.getRow()][curr.getCol()] = curr.getImageView();
//		}
//		return curr_grid;
//	}
}
