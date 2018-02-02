package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	
	private int myIteration = 0;
	private BorderPane myBorderPane;
	private Container myVisualContainer;
	private ControlPanel myControlPanel;
	private Scene myScene;
	private HeaderBar myBar;
	
	public Visualization(ControlPanel cp) {
		myBorderPane = new BorderPane();
		myBorderPane.setId("main-pane");
		
		myBar = new HeaderBar("");
		myVisualContainer = new SquareContainer();
		myControlPanel = cp;
		
		myScene = new Scene(myBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add("main.css");
		
		myBorderPane.setTop(myBar.getHBox());
		myBorderPane.setCenter(myVisualContainer.getVBox());
		myBorderPane.setBottom(myControlPanel.getVBox());
	}
	
	public void visualizeGraph(HashMap<Cell, ArrayList<Cell>> cell_map) {
		myIteration += 1;
		myControlPanel.setIteration(myIteration);
		
		myVisualContainer.setGraphDisplay(cell_map);
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public void reset() {
		myIteration = 0;
		myControlPanel.resetSlider();
	}
	
	public HeaderBar getHeaderBar() {
		return myBar;
	}
}
