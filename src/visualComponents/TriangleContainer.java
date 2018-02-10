package visualComponents;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TriangleContainer extends Container {
	public static final int test_value = 15;
	public static final double height = Container.GRID_SIZE.doubleValue() / test_value * 0.75;
	
	public TriangleContainer() {
		super();
		myDisplay.setId("container-display-borderless");
	}

//	private ImageView[][] buildImageView() {
//		ImageView[][] temp = new ImageView[test_value][test_value];
//		for (int r=0; r < test_value; r++) {
//			for (int c=0; c < test_value; c++) {
//				if (c % 2 == 1) {
//					temp[r][c] = Helper.generateImageView("triangle1.png", height);
//					temp[r][c].setRotate(180);
//				} else {
//					temp[r][c] = Helper.generateImageView("triangle2.png", height);
//				}
//			}
//		}
//		
//		return temp;
//	}
	
	protected HBox buildGraphRow(ImageView[][] graph_grid, int r, int num_cols) {
		HBox row = new HBox();
		for (int c=0; c < num_cols; c++) {
			ImageView image_view = graph_grid[r][c];
			image_view.setPreserveRatio(true);
			image_view.setFitHeight(Container.GRID_SIZE.doubleValue() / num_cols * 0.75);
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
