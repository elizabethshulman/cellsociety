package visualComponents;

import graphVariants.Graph;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	public static final double SCREEN_WIDTH = 700;
	private static final String CSS_STRING = "main.css";
	private static final String FONT_URL = "https://fonts.googleapis.com/css?family=Roboto:700";
	private static final double CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
	
	private BorderPane myBorderPane = new BorderPane();
	private int myIteration;
	private Container myVisualContainer;
	private ControlPanel myControlPanel;
	private Scene myScene;
	private HeaderBar myBar;
	private LineGraph myLineGraph;
	private Stage myStage;
	
	public Visualization(ControlPanel cp, Stage stage) {
		myStage = stage;
		
		myBorderPane.setId("main-pane");
		
		myScene = new Scene(myBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(CSS_STRING);
		
		myBar = new HeaderBar("");
		myVisualContainer = new TriangleContainer();
		myControlPanel = cp;
		myLineGraph = new LineGraph();
		
		VBox center = new VBox();
		center.getChildren().add(myLineGraph.getLineChart());
		center.getChildren().add(myVisualContainer.getContainer());
		
		myBorderPane.setTop(myBar.getHBox());
		myBorderPane.setCenter(center);
		myBorderPane.setBottom(myControlPanel.getVBox());
	}
	
	public void visualizeGraph(Graph g) {
		myIteration += 1;
		myControlPanel.setIteration(myIteration);
		
		myVisualContainer.setGraphDisplay(g);
		myLineGraph.addCoordinates(g);
	}

	public Scene getScene() {
		return myScene;
	}
	
	public void reset(boolean backToHome) {
		if (backToHome) {
			myBorderPane.setLeft(null);
		}
		myIteration = -1;
		myControlPanel.resetSlider();
		myControlPanel.enableButtons();
		myLineGraph.resetChart();
		centerOnScreen();
	}
	
	public void reset() {
		
	}
	
	public void amendHeader(String header) {
		myBar.setSimHeader(header);
	}
	
	public void addSidebar(Sidebar sidebar) {
		myStage.setWidth(SCREEN_WIDTH + Sidebar.WIDTH);
		myBorderPane.setLeft(sidebar.getVBox());
		centerOnScreen();
	}
	
	private void centerOnScreen() {
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double centerX = bounds.getMinX() + (bounds.getWidth() - myStage.getWidth()) * CENTER_ON_SCREEN_X_FRACTION;
        myStage.setX(centerX);
    }
}
