package cellsociety_team10;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GridContainer {
	private static final double GRID_SIZE = 400;
	private static final double BORDER_WIDTH = 2;
	private static final double CORNER_RADII = 5;
	private static final int n = 17;
	
	private VBox vb;
	
	public GridContainer() {
		VBox grid = new VBox();
		
		ImageView[][] images = generateTestGrid();
		
		for (int r=0; r < images.length; r++) {
			grid.getChildren().add(buildGridRow(r));
		}
		
		grid.setPadding(new Insets(0));
		grid.setMaxWidth(0);
		
		vb = new VBox();
		vb.getChildren().add(grid);
		vb.setAlignment(Pos.CENTER);
	}
	
	private HBox buildGridRow(int r) {
		String[] even = new String[] {"red.png", "blue.png"};
		String[] odd = new String[] {"blue.png", "red.png"};
		HBox hbox = new HBox();
		if (r % 2 == 0) {
			for (int c=0; c < n; c++) {
				hbox.getChildren().add(generateTile(even[c % even.length]));
			}
		} else {
			for (int c=0; c < n; c++) {
				hbox.getChildren().add(generateTile(odd[c % odd.length]));
			}
		}
		hbox.setAlignment(Pos.CENTER);
		hbox.setMaxWidth(0);
		
		return hbox;
	}
	
	private ImageView[][] generateTestGrid() {
		ImageView[][] images = new ImageView[n][n];
		for (int r=0; r < n; r++) {
			for (int c=0; c < n; c++) {
				if ((r % 2 == 0) && (c % 2 == 1)) {
					images[r][c] = generateTile("blue.png");
				} else if ((r % 2 == 0) && (c % 2 == 0)) {
					images[r][c] = generateTile("red.png");
				} else if ((r % 2 == 1) && (c % 2 == 1)) {
					images[r][c] = generateTile("red.png");
				} else {
					images[r][c] = generateTile("blue.png");
				}
			}
		}
		return images;
	}
	
	private ImageView generateTile(String filename) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(filename));
		ImageView temp = new ImageView(image);
		temp.setPreserveRatio(true);
		temp.setFitHeight((GRID_SIZE - 2 * BORDER_WIDTH) / n);
		return temp;
	}
	
	public VBox getVBox() {
		return vb;
	}
}
