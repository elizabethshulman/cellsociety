package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

public class SquareContainer implements IContainer {
	private static final double GRID_SIZE = 400;
	private static final double BORDER_WIDTH = 2;
	private static final Color BORDER_COLOR = Color.DIMGREY;
	private static final int n = 8;
	
	private VBox vb;
	private VBox graph;
	
	public SquareContainer() {
		graph = new VBox();
		
		setGraphDisplay(null);
		
		BorderStroke myB = new BorderStroke(BORDER_COLOR, BorderStrokeStyle.SOLID,
				new CornerRadii(0), new BorderWidths(BORDER_WIDTH), null);
		graph.setBorder(new Border(myB));
		graph.setPadding(new Insets(0));
		graph.setMaxWidth(0);
		
		vb = new VBox();
		vb.getChildren().add(graph);
		vb.setAlignment(Pos.CENTER);
	}
	
	private HBox buildGraphRow(int r) {
		String[] arr = new String[]{"yellow.png", "orange.png"};
		HBox hbox = new HBox();
		for (int c=0; c < n; c++) {
			int dex = new Random().nextInt(arr.length);
			hbox.getChildren().add(generateTile(arr[dex]));
		}
		hbox.setAlignment(Pos.CENTER);
		hbox.setMaxWidth(0);
		
		return hbox;
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
	
	public void setGraphDisplay(HashMap<Cell, ArrayList<Cell>> cell_map) {
		graph.getChildren().clear();
		for (int r=0; r < n; r++) {
			graph.getChildren().add(buildGraphRow(r));
		}
	}
}
