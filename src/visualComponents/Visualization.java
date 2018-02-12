package visualComponents;

import graphVariants.Graph;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author benhubsch
 * 
 * The Class Visualization, which handles almost everything related to the actual display
 * of the simulation to the user. It is instantiated from within Engine and holds references
 * to things that comprise the interface, like the ControlPanel, HeaderBar, and LineGraph.
 */
public class Visualization {
	
	private static final double SCREEN_WIDTH = 700;
	private static final double SCREEN_HEIGHT = 750;
	private static final String CSS_STRING = "main.css";
	private static final String FONT_URL = "https://fonts.googleapis.com/css?family=Roboto:700";
	private static final double CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;

	private BorderPane myBorderPane = new BorderPane();
	private HeaderBar myBar = new HeaderBar();
	private ContainerFactory myContainerFactory = new ContainerFactory();
	private LineGraph myLineGraph = new LineGraph();
	private Scene myScene = new Scene(myBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
	private int myIteration = -1;
	private Container myVisualContainer;
	private ControlPanel myControlPanel;
	private Stage myStage;

	/**
	 * Instantiates a new visualization.
	 *
	 * @param cp The ControlPanel object, which is to be placed at the bottom of any visualizations.
	 * @param stage The Stage object, which is used to manage screen size.
	 */
	public Visualization(ControlPanel cp, Stage stage) {
		myControlPanel = cp;
		myStage = stage;

		myBorderPane.setId("main-pane");

		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(CSS_STRING);

		myBorderPane.setTop(myBar.getHBox());
		myBorderPane.setBottom(myControlPanel.getVBox());
	}

	/**
	 * Sets the up container by creating the appropriate instance of Container and
	 * placing it in the middle of the screen.
	 *
	 * @param g The Graph object that contains information about the appropriate 
	 * Cell shapes. 
	 */
	private void setupContainer(Graph g) {
		myVisualContainer = myContainerFactory.create(g.getCellShape());

		VBox center = new VBox();
		center.getChildren().add(myLineGraph.getLineChart());
		center.getChildren().add(myVisualContainer.getContainer());

		myBorderPane.setCenter(center);
	}

	/**
	 * This is called when the user changes setting in the DIY portion of the
	 * application. It updates the graph without incrementing the iteration count
	 * or handling the animation.
	 *
	 * @param g
	 */
	public void updateGraphOnly(Graph g) {
		myVisualContainer.setGraphDisplay(g);
	}

	/**
	 * This method is called from inside step() and manages a single iteration of
	 * the simulation.
	 *
	 * @param g
	 */
	public void visualizeGraph(Graph g) {
		if (myVisualContainer == null) {
			setupContainer(g);
		}
		myIteration += 1;
		myControlPanel.setIteration(myIteration);

		myVisualContainer.setGraphDisplay(g);
		myLineGraph.addCoordinates(g);
	}


	/**
	 * Gets the scene.
	 *
	 * @return Scene
	 */
	public Scene getScene() {
		return myScene;
	}

	/**
	 * Resets everything at the end of a simulation depending on whether or not
	 * the user is returning to the home page or is in DIY mode.
	 *
	 * @param backToHome
	 */
	public void reset(boolean backToHome) {
		if (backToHome) {
			myStage.setWidth(SCREEN_WIDTH);
			myBorderPane.setLeft(null);
		}
		myVisualContainer = null;
		myIteration = -1;
		myControlPanel.resetSlider();
		myControlPanel.enableButtons();
		myLineGraph.resetChart();
		
		centerOnScreen();
	}

	/**
	 * Change header text.
	 *
	 * @param header
	 */
	public void changeHeaderText(String header) {
		myBar.setHeader(header);
	}

	/**
	 * Adds the sidebar if the user enters DIY mode.
	 *
	 * @param sidebar
	 */
	public void addSidebar(Sidebar sidebar) {
		myStage.setWidth(SCREEN_WIDTH + Sidebar.WIDTH);
		myBorderPane.setLeft(sidebar.getVBox());
		centerOnScreen();
	}

	/**
	 * Center on screen.
	 */
	private void centerOnScreen() {
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double centerX = bounds.getMinX() + (bounds.getWidth() - myStage.getWidth()) * CENTER_ON_SCREEN_X_FRACTION;
		myStage.setX(centerX);
	}
}
