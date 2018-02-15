package visualComponents;

import cellsociety_team10.Graph;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author benhubsch
 * 
 * This class handles almost everything related to the actual display of the simulation. 
 * It is instantiated from within Engine and holds references to components of the UI
 * like the ControlPanel, HeaderBar, and LineGraph. In many ways, this class can be seen 
 * as a "UI Manager" class. It implements the Visualizer interface.
 * 
 * I believe this class (and the accompanying Visualizer interface) demonstrates good 
 * design for a variety of reasons. For starters, the use of the interface means that the
 * API that this class exposes is explicit and digestible. Anyone can open up Visualizer.java
 * and very quickly get a good sense for the responsibilities of this class -- the readability 
 * of this class is increased substantially. Additionally, the use of the interface allows 
 * someone else to come along and implement their own instances of Visualizer with ease. The
 * interface therefore satisfies the open-closed principle of object-oriented design because
 * an engineer may, without modifying the original source code, write their own implementation
 * of Visualizer that extends the capabilities of the project. More to this point, in Engine,
 * I make use of Visualizer as the type (i.e., "private Visualizer myVis"), so that the program 
 * is able to take advantage of polymorphism, unaware of the exact type of the object until 
 * runtime.
 * 
 * This class also doesn't give up any of its data. You'll notice that there's not a single
 * getter or setter in this class, nor does it use getters and setters on other objects from
 * the program, with one exception. This is because responsibility has been appropriately del-
 * egated. Instead of giving up information for other classes to act on, information is passed
 * one level down. This yields intelligent and active classes, as opposed to static storage
 * classes. Instead of asking the ControlPanel for its HBox object, I've instead chosen to
 * ask it to set itself on the BorderPane. This allows for greater flexibility moving forward
 * because someone may decide to change the ControlPanel to be a different kind of Node, and
 * they would have the freedom to do that because the information is encapsulated within those
 * classes.
 * 
 * Last but not least, I've employed the Factory Design Pattern here. It allows me to encaps-
 * ulate the logic of instantiation of a certain Container object within the factory and only
 * have to worry about the return type, a Container. Because of the inheritance hierarchy, I
 * can rely on polymorphism to handle the logic surrounding method calls on specific instances
 * of those classes. The bottom line is that myContainerFactory allows me to program to the
 * abstraction rather than a concretion. This cleans up the logic of setupContainer() and makes 
 * the class easier to read and digest.
 * 
 */
public class Visualization implements Visualizer {
	private static final double SIDEBAR_WIDTH = 200;
	private static final double SCREEN_WIDTH = 700;
	private static final double SCREEN_HEIGHT = 750;
	private static final String CSS_STRING = "main.css";
	private static final String FONT_URL = "https://fonts.googleapis.com/css?family=Roboto:700";
	private static final double SCREEN_MIDPOINT_RATIO = 0.5;

	private BorderPane myBorderPane = new BorderPane();
	private HeaderBar myBar = new HeaderBar();
	private ContainerFactory myContainerFactory = new ContainerFactory();
	private LineGraph myLineGraph = new LineGraph();
	private Scene myScene;
	private Container myVisualContainer;
	private ControlPanel myControlPanel;
	private Stage myStage;

	/**
	 * Instantiates a new Visualization object.
	 *
	 * @param cp The ControlPanel object, which is to be placed at the bottom of 
	 * this Visualization and allows the user to manipulate the animation of a
	 * given simulation.
	 * @param stage The Stage object, which is really useful to have in this class
	 * for purposes of setting the Scene object and manipulating the window.
	 */
	public Visualization(ControlPanel cp, Stage stage) {
		myControlPanel = cp;
		myStage = stage;

		myBorderPane.setId("main-pane");

		myScene = new Scene(myBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(CSS_STRING);

		myBar.setPane(myBorderPane);
		myControlPanel.setPane(myBorderPane);
	}

	/**
	 * Resets the display at the end of a simulation depending on whether or not
	 * the user is returning to the home page or is in DIY mode.
	 *
	 * @param backToStart This boolean specifies whether the user is returning back to
	 * the StartPage or is simply changing a setting in DIY mode.
	 */
	public void reset(boolean backToStart) {
		if (backToStart) {
			myStage.setWidth(SCREEN_WIDTH);
			myBorderPane.setLeft(null);
			centerOnScreen();
		}
		myControlPanel.reset();
		myLineGraph.reset();
		
		myVisualContainer = null;
	}

	/**
	 * This function changes the text that is displayed in the HeaderBar, which is
	 * useful when the user switches between simulations. Instead of instantiating
	 * a new HeaderBar, it's much more straightforward to simply change its contents.
	 *
	 * @param name This is the new header text containing author and title info.
	 */
	public void changeName(String name) {
		myBar.setHeader(name);
	}

	/**
	 * Adds a Sidebar object to the user's application window if the user enters 
	 * DIY mode.
	 *
	 * @param sidebar A Sidebar object with buttons wired appropriately.
	 */
	public void addSidebar(Sidebar sidebar) {
		myStage.setWidth(SCREEN_WIDTH + SIDEBAR_WIDTH);
		sidebar.setPane(myBorderPane);
		centerOnScreen();
	}
	/**
	 * This is called when the user changes setting in the DIY portion of the
	 * application. It updates the graph without incrementing the iteration count
	 * or handling the animation.
	 *
	 * @param g This Graph object (and the API that it exposes) can be used in any way
	 * to build the desired updated image by myVisualContainer.
	 */
	public void updateGraph(Graph g) {
		myVisualContainer.setGraphDisplay(g);
	}

	/**
	 * This method is called from inside step() and manages a single iteration of
	 * the simulation.
	 *
	 * @param g This Graph object is used to seed the UI with information about the 
	 * current state of the simulation.
	 */
	public void visualize(Graph g) {
		if (myVisualContainer == null) {
			setupContainer(g);
		}
		myControlPanel.incrementIteration();

		updateGraph(g);
		myLineGraph.addCoordinates(g);
		
		setScene();
	}
	
	/**
	 * Sets the up container by creating the appropriate instance of Container and
	 * placing it in the middle of the screen.
	 *
	 * @param g The Graph object that contains information about the appropriate 
	 * Cell shapes. 
	 */
	private void setupContainer(Graph g) {
		myVisualContainer = myContainerFactory.createContainer(g.getCellShape());

		VBox center = new VBox();
		myLineGraph.addToVBox(center);
		myVisualContainer.addToVBox(center);
		myBorderPane.setCenter(center);
	}

	/**
	 * Sets the scene object when called from Engine.
	 */
	private void setScene() {
		myStage.setScene(myScene);
	}

	/**
	 * This method centers the application on the user's screen, which is
	 * used when switching between DIY mode and the home screen, as it can
	 * be kind of jarring to have an application take up uneven space. 
	 */
	private void centerOnScreen() {
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double centerX = calcCenterOfScreen(bounds);
		myStage.setX(centerX);
	}
	
	/**
	 * This method calculates the center of the screen, which is ultimately
	 * used to center the application on the user's screen. 
	 * 
	 * @param bounds This represents the dimensions of the user's current screen,
	 * taking into account things like menu bars and such.
	 * @return The x-coordinate representing the center of the screen. 
	 */
	private double calcCenterOfScreen(Rectangle2D bounds) {
		return bounds.getMinX() + (bounds.getWidth() - myStage.getWidth()) * SCREEN_MIDPOINT_RATIO;
	}
}
