package visualComponents;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cellVariants.Cell;
import graphVariants.Graph;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SquareContainer extends Container {
	public SquareContainer() {
		super();
		myDisplay.setId("container-display-square");
	}
	
	protected HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			row.getChildren().add(graph_grid[r][c]);
		}
		row.setId("container-row");
		return row;
	}


	/* Uses deprecated BigDecimal rounding division but works like a charm!
	 * Necessary to prevent gaps between squares resulting from imprecise 
	 * double division.
	 */
	@Override
	protected double calcShapeHeight(int num_cols) {
		BigDecimal cols = new BigDecimal(num_cols);
		return Container.GRID_SIZE.divide(cols, RoundingMode.UP).doubleValue();
	}
}
