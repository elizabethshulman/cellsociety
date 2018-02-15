package cellsociety_team10;

import java.io.File;
import java.util.ResourceBundle;

import cellVariants.Cell;
import cellVariants.CellFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rulesVariants.RulesFactory;
import visualComponents.ControlPanel;
import visualComponents.Sidebar;
import visualComponents.StartPage;
import visualComponents.Visualization;
import visualComponents.IVisualizer;

/**
 * @author benhubsch
 * @author Elizabeth Shulman
 * @author Andrew Yeung
 * 
 * This class handles the high-level logic and coordination between the various
 * components of the simulation. It is the highest class in our class hierarchy.
 */
public class Engine {
	private static final double ANIM_RATE = 2.5;
	private static final int MILLISECOND_DELAY = 500;
	private static final String SIM_FOLDER = "data/simulations/";
	private static final String DEFAULT_DIY = "data/simulations/default/Game of Life.xml";
	private static final String LANGUAGE = "English";

	private ResourceBundle myResources;
	private Timeline myAnimation;        
	private Graph myGraph;
	private IVisualizer myVis;
	private Scene myStartScene;
	private Stage myStage;
	private FileChooser myFileChooser;
	private RulesFactory myRulesFactory;
	private CellFactory myCellFactory;
	private ControlPanel myControlPanel;
	private Sidebar mySidebar;
	private FileProcessor myFileProcessor;

	/**
	 * This function is the entry point into the significant portion of the simulation
	 * and initializes many of the important components that will later be called to
	 * perform microservices.
	 *
	 * @param stage This is the application's stage.
	 */
	public void initializeSimulation(Stage stage) {
		myStage = stage;

		myResources = ResourceBundle.getBundle(LANGUAGE);

		myStartScene = new StartPage(myResources,
				e -> showFileChooser("predator"),
				e -> showFileChooser("segregation"),
				e -> showFileChooser("life"), 
				e -> showFileChooser("fire"),
				e -> showFileChooser("ant"),
				e -> showFileChooser("rps"),
				e -> setupDIY()
				).getScene();

		myFileChooser = new FileChooser();
		myFileChooser.setTitle(myResources.getString("FileTitle"));
		
		myRulesFactory = new RulesFactory();
		myCellFactory = new CellFactory();

		myAnimation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myAnimation.getKeyFrames().add(frame);
		
		myControlPanel = new ControlPanel(myAnimation, 
				e -> play(), 
				e -> pause(), 
				e -> end(), 
				e -> next(),
				e -> save());
		myVis = new Visualization(myControlPanel, stage);

		myStage.setScene(myStartScene);
		myStage.setTitle(myResources.getString("Title"));
		myStage.show();
	}

	/**
	 * Resets the animation after a simulation ends.
	 */
	private void resetAnimation() {
		myAnimation.stop();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		
		myAnimation.setRate(ANIM_RATE);
	}

	/**
	 * This is the loop that powers the iteration to iteration grid change that
	 * is central to the application's logic.
	 */
	private void step() {
		if (myGraph.isDead()) {
			myAnimation.pause();
			myAnimation.setRate(0);
			myControlPanel.disableButtons();
			return;
		}
		myGraph.buildNextGrid();
		myVis.visualize(myGraph);
	}

	/**
	 * Pauses the animation.
	 */
	private void pause() {
		myAnimation.pause();
	}

	/**
	 * Plays the animation.
	 */
	private void play() {
		myAnimation.play();
	}

	/**
	 * Ends the animation.
	 */
	private void end() {
		myAnimation.stop();
		myStage.setScene(myStartScene);
		myVis.reset(true);
	}

	/**
	 * Allows the user to step through the animation one iteration at a time.
	 */
	private void next() {
		myAnimation.pause();
		step();
	}
	
	/**
	 * Saves the current grid state of a simulation to a file.
	 */
	private void save() {
		File saved_file = myFileChooser.showSaveDialog(myStage);
		if (saved_file != null) {
			try {
				myFileProcessor.setRowsAndCols(myGraph.getRows(), myGraph.getCols());
				myFileProcessor.saveGridState(myGraph.getCells(), saved_file);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR, e.getLocalizedMessage());
				alert.show();
			}
			
		}
	}

	/**
	 * Allows the user to pick a simulation to run from an initial configuration file.
	 *
	 * @param directory The directory to which the FileChooser should open by default.
	 */
	private void showFileChooser(String directory) {
		String source = SIM_FOLDER + directory;
		myFileChooser.setInitialDirectory(new File(source));
		File f = myFileChooser.showOpenDialog(myStage);
		if (f != null) {
			loadSimulation(f);
		}
	}
	
	/**
	 * Initializes the FileProcessor.
	 *
	 * @param file
	 * @return Boolean
	 */
	private Boolean initFileProcessor(File file) {
		try {
			myFileProcessor = new FileProcessor(file);
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getLocalizedMessage());
			alert.show();
			return false;
		}
	}

	/**
	 * Instantiates a simulation given a file, and also ensures that the components
	 * are properly configured to run a simulation.
	 *
	 * @param file
	 */
	public void loadSimulation(File file) {
		Boolean success = initFileProcessor(file);
		if (! success) {
			return;
		}
		myGraph = new Graph(myFileProcessor, myRulesFactory, myCellFactory);
		
		resetAnimation();
		
		myVis.reset(false);
		
		if (mySidebar != null) {
			mySidebar.setSliders(myGraph);
		}
		
		myVis.changeName(createHeaderText(myFileProcessor.getTitle(), myFileProcessor.getAuthor()));
		myVis.visualize(myGraph);
	}


	/**
	 * Set's up the interface for the DIY portion of the simulation, should the user
	 * choose to click on it.
	 */
	private void setupDIY() {
		File file = new File(DEFAULT_DIY);
		loadSimulation(file);
		
		randomizeDIY();

		mySidebar = new Sidebar(myResources, this, myGraph);
		myVis.addSidebar(mySidebar);
	}
	
	/**
	 * Randomize the initial cell state configuration given a new DIY simulation load.
	 */
	public void randomizeDIY() {
		for (Cell c : myGraph.getCells()) {
			c.setRandom();
		}
		myVis.updateGraph(myGraph);
	}

	/**
	 * Creates the header text for a given simulation.
	 *
	 * @param title The title of the simulation.
	 * @param author The author of the simulation.
	 * @return String
	 */
	private String createHeaderText(String title, String author) {
		return String.format("%s %s %s", title, myResources.getString("By"), author);
	}
	
	/**
	 * This function updates the current visualization settings given a dynamic change by
	 * a user in DIY mode.
	 *
	 * @param shape The shape that needs to be displayed.
	 * @param isDiagonal The kind of neighbors that need to be considered.
	 * @param isToroidal The kind of dimension that we're dealing with.
	 */
	public void updateSettings(String shape, boolean isToroidal, boolean isDiagonal) {
		myFileProcessor.setCellShape(shape);
		myFileProcessor.setBorders(isToroidal);
		myFileProcessor.setNeighbors(isDiagonal);
		myGraph.resetIsDead();
		
		resetAnimation();
		myGraph.updateGraph();
		randomizeDIY();
		
		myVis.reset(false);
		
		myVis.visualize(myGraph);
	}
}
