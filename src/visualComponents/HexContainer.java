package visualComponents;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HexContainer extends Container {
	private double useful = 1 / Math.tan(Math.PI / 6);
	private double height;
	
	public HexContainer() {
		super();
		myDisplay.setId("container-display-borderless");
	}

	protected HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols, int num_rows) {
		height = Container.GRID_SIZE.doubleValue() / num_rows;
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			ImageView image_view = graph_grid[r][c];
			image_view.setPreserveRatio(true);
			image_view.setFitHeight(height);
			row.getChildren().add(image_view);
		}
		double padding = - height / 4 - 0.5;
		if (r % 2 == 1) {
			row.setPadding(new Insets(padding, 0, padding, getOffset()));
		} else {
			row.setPadding(new Insets(padding, 0, padding, 0));
		}
		double side_length = graph_grid[0][0].getBoundsInLocal().getWidth() / 2;
		row.setSpacing(side_length);
		return row;
	}
	
	private double getOffset() {
		return height / 2 * useful - 0.5;
	}

	
	protected double calcShapeHeight(int num_cols) {
		return height;
	}

}
