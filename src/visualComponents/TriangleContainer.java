package visualComponents;

import graphVariants.Graph;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TriangleContainer extends Container {
	public static final int test_value = 15;
	public static final double height = Container.GRID_SIZE.doubleValue() / test_value * 0.75;
	
	private VBox myVBox;
	private VBox myDisplay;

	public TriangleContainer() {
		super();
		myDisplay.setId("container-display-borderless");
	}

	@Override
	public void setGraphDisplay(Graph g) {
		myDisplay.getChildren().clear();
		ImageView[][] graph_grid = buildImageView();
		for (int r=0; r < test_value; r++) {
			myDisplay.getChildren().add(buildGraphRow(graph_grid, r, test_value));
		}
	}
	
	private ImageView[][] buildImageView() {
		ImageView[][] temp = new ImageView[test_value][test_value];
		for (int r=0; r < test_value; r++) {
			for (int c=0; c < test_value; c++) {
				if (c % 2 == 1) {
					temp[r][c] = Helper.generateImageView("triangle1.png", height);
					temp[r][c].setRotate(180);
				} else {
					temp[r][c] = Helper.generateImageView("triangle2.png", height);
				}
			}
		}
		
		return temp;
	}
	
	protected HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			row.getChildren().add(graph_grid[r][c]);
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
