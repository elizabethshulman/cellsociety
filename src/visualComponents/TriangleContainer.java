package visualComponents;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TriangleContainer extends Container {
	private double height;
	
	public TriangleContainer() {
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
			if (c % 2 == 1) {
				image_view.setRotate(180);
			}
			row.getChildren().add(image_view);
		}
		
		row.setSpacing(-graph_grid[0][0].getBoundsInLocal().getWidth() / 2);
		if (r % 2 == 1) {
			row.setPadding(new Insets(0, 0, 0, -graph_grid[0][0].getBoundsInLocal().getWidth() / 2));
		} 
		return row;
	}
	
	protected double calcShapeHeight(int num_cols) {
		return height;
	}

}
