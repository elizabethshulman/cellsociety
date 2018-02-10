package visualComponents;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SquareContainer extends Container {
	public SquareContainer() {
		super();
		myDisplay.setId("container-display-square");
	}
	
	protected HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols, int num_rows) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			row.getChildren().add(graph_grid[r][c]);
		}
		row.setId("container-row");
		return row;
	}

	@Override
	protected double calcShapeHeight(int num_cols) {
		BigDecimal cols = new BigDecimal(num_cols);
		return Container.GRID_SIZE.divide(cols, RoundingMode.UP).doubleValue();
	}
}
