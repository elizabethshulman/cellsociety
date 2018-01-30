package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	
	private Stage stage;
	private int iteration = 0;
	private BorderPane border_pane;
	private SquareContainer graph_visual;
	private ControlPanel control_panel;
	
	public Visualization(Stage primaryStage, ControlPanel cp) {
		stage = primaryStage;
		
		border_pane = new BorderPane();
		StatusBar bar = new StatusBar("Game of Life");
		graph_visual = new SquareContainer();
		control_panel = cp;
		
		Scene scene = new Scene(border_pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		scene.getStylesheets().add(FONT_URL);
		scene.getStylesheets().add("main.css");
		
		border_pane.setTop(bar.getHBox());
		border_pane.setCenter(graph_visual.getVBox());
		border_pane.setBottom(control_panel.getVBox());
		BackgroundFill myBF = new BackgroundFill(Color.rgb(249, 249, 249), new CornerRadii(0), null);
		border_pane.setBackground(new Background(myBF));
		
		stage.setTitle("Cell Society");
		stage.setScene(scene);
		stage.show();
	}
	
	public void visualizeGrid(HashMap<Cell, ArrayList<Cell>> cell_map) {
		iteration += 1;
		control_panel.setIteration(iteration);
		
		graph_visual.setGraphDisplay(cell_map);
		
	}
}
