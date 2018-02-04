package cellsociety_team10;

import graphVariants.Graph;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String CSS_STRING = "main.css";
	private static final String FONT_URL = "https://fonts.googleapis.com/css?family=Roboto:700";
	
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
		myScene.getStylesheets().add(CSS_STRING);
		
		myBorderPane.setTop(myBar.getHBox());
		myBorderPane.setCenter(myVisualContainer.getVBox());
		myBorderPane.setBottom(myControlPanel.getVBox());
	}
	
	public void visualizeGraph(Graph g) {
		myIteration += 1;
		myControlPanel.setIteration(myIteration);
		
		myVisualContainer.setGraphDisplay(g);
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public void reset() {
		myIteration = 0;
		myControlPanel.resetSlider();
	}
	
	public void amendHeader(String header) {
		myBar.setSimHeader(header);
	}
}
