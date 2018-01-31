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
	private static final Color BACKGROUND_COLOR = Color.rgb(249, 249, 249);
	
	private int myIteration = 0;
	private BorderPane myBorderPane;
	private Container myVisual;
	private ControlPanel myControlPanel;
	private Scene myScene;
	
	public Visualization(ControlPanel cp) {
		
		myBorderPane = new BorderPane();
		StatusBar bar = new StatusBar("Game of Life");
		myVisual = new SquareContainer();
		myControlPanel = cp;
		
		myScene = new Scene(myBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add("main.css");
		
		myBorderPane.setTop(bar.getHBox());
		myBorderPane.setCenter(myVisual.getVBox());
		myBorderPane.setBottom(myControlPanel.getVBox());
		BackgroundFill myBF = new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(0), null);
		myBorderPane.setBackground(new Background(myBF));
	}
	
	public void visualizeGrid(HashMap<Cell, ArrayList<Cell>> cell_map) {
		myIteration += 1;
		myControlPanel.setIteration(myIteration);
		
		myVisual.setGraphDisplay(cell_map);
		
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public void reset() {
		myIteration = 0;
		myControlPanel.resetSlider();
	}
}
