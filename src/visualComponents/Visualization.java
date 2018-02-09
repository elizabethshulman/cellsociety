package visualComponents;

import graphVariants.Graph;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String CSS_STRING = "main.css";
	private static final String FONT_URL = "https://fonts.googleapis.com/css?family=Roboto:700";
	
	private BorderPane myBorderPane = new BorderPane();
	private int myIteration;
	private Container myVisualContainer;
	private ControlPanel myControlPanel;
	private Scene myScene;
	private HeaderBar myBar;
	private LineGraph myLineGraph;
	
	public Visualization(ControlPanel cp) {
		this(cp, null);
	}
	
	public Visualization(ControlPanel cp, Sidebar sb) {
		myBorderPane.setId("main-pane");
		
		double total_width = SCREEN_WIDTH;
		if (sb != null) {
			myBorderPane.setLeft(sb.getVBox());
			total_width += Sidebar.WIDTH;
		}
		
		myScene = new Scene(myBorderPane, total_width, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(CSS_STRING);
		
		myBar = new HeaderBar("");
		myVisualContainer = new SquareContainer();
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
	
	public void reset() {
		myIteration = -1;
		myControlPanel.resetSlider();
		myControlPanel.enableButtons();
		myLineGraph.resetChart();
	}
	
	public void amendHeader(String header) {
		myBar.setSimHeader(header);
	}
}
