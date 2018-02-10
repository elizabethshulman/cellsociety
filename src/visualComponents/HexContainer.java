package visualComponents;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HexContainer extends Container {
	public static final int test_value = 5;
//	public static final ImageView baseline = Helper.generateImageView("hex1.png");
	public static final double height = Container.GRID_SIZE.doubleValue() / test_value * 0.75;
	
	
//	private double side_length = Helper.generateImageView("hex1.png", height).getBoundsInLocal().getWidth() / 2;
	private double useful = 1 / Math.tan(Math.PI / 6);
	
	public HexContainer() {
		super();
		myDisplay.setId("container-display-borderless");
	}

//	@Override
//	public void setGraphDisplay(Graph g) {
//		myDisplay.getChildren().clear();
//		ImageView[][] graph_grid = buildImageView();
//		for (int r=0; r < test_value; r++) {
//			myDisplay.getChildren().add(buildGraphRow(graph_grid, r, test_value));
//		}
//	}
	
	protected HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			ImageView image_view = graph_grid[r][c];
			image_view.setPreserveRatio(true);
			image_view.setFitHeight(Container.GRID_SIZE.doubleValue() / num_cols * 0.75);
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

	private ImageView[][] buildImageView() {
		ImageView[][] temp = new ImageView[test_value][test_value];
		for (int r=0; r < test_value; r++) {
			for (int c=0; c < test_value; c++) {
				if (c % 2 == 1) {
					temp[r][c] = Helper.generateImageView("hex1.png", height);
				} else {
					temp[r][c] = Helper.generateImageView("hex2.png", height);
				}
			}
		}
		
		return temp;
	}
	
	protected double calcShapeHeight(int num_cols) {
		return height;
	}

}
