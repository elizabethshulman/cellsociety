package visualComponents;

import graphVariants.Graph;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HexContainer extends Container {
	public static final int n = 5;
	public static final ImageView baseline = Helper.generateImageView("hex1.png");
	public static final double height = Container.GRID_SIZE.doubleValue() / n * 0.75;
	
	
	private double side_length = Helper.generateImageView("hex1.png", height).getBoundsInLocal().getWidth() / 2;
	private double useful = 1 / Math.tan(Math.PI / 6);
	private VBox myVBox;
	private VBox myDisplay;
	
	public HexContainer() {
		myDisplay = new VBox();
		myDisplay.setId("container-display-hex");
		
		myVBox = new VBox();
		myVBox.setId("container-vbox");
		myVBox.getChildren().add(myDisplay);
	}

	@Override
	public VBox getContainer() {
		return myVBox;
	}

	@Override
	public void setGraphDisplay(Graph g) {
		myDisplay.getChildren().clear();
		ImageView[][] graph_grid = buildImageView();
		for (int r=0; r < n; r++) {
			myDisplay.getChildren().add(buildGraphRow(graph_grid, r, n));
		}
	}
	
	private HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			row.getChildren().add(graph_grid[r][c]);
		}
		double padding = - height / 4 - 0.5;
		if (r % 2 == 1) {
			row.setPadding(new Insets(padding, 0, padding, getOffset()));
		} else {
			row.setPadding(new Insets(padding, 0, padding, 0));
		}
		row.setSpacing(side_length);
		return row;
	}
	
	private double getOffset() {
		return height / 2 * useful - 0.5;
	}

	private ImageView[][] buildImageView() {
		ImageView[][] temp = new ImageView[n][n];
		for (int r=0; r < n; r++) {
			for (int c=0; c < n; c++) {
				if (c % 2 == 1) {
					temp[r][c] = Helper.generateImageView("hex1.png", height);
				} else {
					temp[r][c] = Helper.generateImageView("hex2.png", height);
				}
			}
		}
		
		return temp;
	}

}
