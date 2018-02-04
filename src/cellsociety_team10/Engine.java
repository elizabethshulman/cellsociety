package cellsociety_team10;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

import graphVariants.Graph;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rulesVariants.*;

public class Engine extends Application {
	private static final double ANIM_RATE = 2.5;
	private static final int MILLISECOND_DELAY = 500;
	private static final double SECOND_DELAY = 50.0;
	private static final String SIM_FOLDER = "data/simulations/";
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String LANGUAGE = "English";

	private ResourceBundle myResources;
	private Timeline myAnimation;        
	private Graph myGraph;
	private Visualization myVis;
	private Scene myStartScene;
	private Stage myStage;
	private FileChooser myFileChooser;

	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initializeSimulation(primaryStage);
	}

	private void initializeSimulation(Stage stage) {
		/**
		 * read first line of file, determine simulation type
		 * build appropriate fileProcessor, pass fp into grid
		 * initialize Visualization myVis
		 * 
		 */
		myStage = stage;
		myStartScene = new StartPage(e -> buildFileChooser("predator"),
									e -> buildFileChooser("segregation"),
									e -> buildFileChooser("life"), 
									e -> buildFileChooser("fire")).getScene();

		myStage.setScene(myStartScene);
		myStage.setTitle("Cell Society");
		myStage.show();
		
//		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		
		myFileChooser = new FileChooser();
		myFileChooser.setTitle("Cell Simulation File Chooser");

		myAnimation = new Timeline();
		myVis = new Visualization(new ControlPanel(myAnimation, e -> play(), e -> pause(), e -> end(), e -> next()));
		setupAnimation();
	}

	private void setupAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.setRate(ANIM_RATE);
	}

	private void step(double elapsedTime) {
		myGraph.buildNextGrid();
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}

	private void pause() {
		myAnimation.pause();
	}

	private void play() {
		myAnimation.play();
	}

	// would be called stop, but stop can't be overwritten with a lower
	// visibility since it's implemented in the Application class
	private void end() {
		myAnimation.stop();
		myVis.reset();
		myStage.setScene(myStartScene);
	}

	private void next() {
		myAnimation.pause();
		step(SECOND_DELAY);
	}

	private void buildFileChooser(String directory) {
		String source = SIM_FOLDER + directory;
		myFileChooser.setInitialDirectory(new File(source));
		// need to handle if they hit cancel
		File f = myFileChooser.showOpenDialog(myStage);
		selectFile(f);
	}
	
	private void selectFile(File filename) {
		try {
			FileProcessor fp = new FileProcessor(filename.getAbsolutePath());
			fp.readFile();
			String className = "rulesVariants." + fp.getType() + "Rules";
			Rules ruleset = (Rules) Class.forName(className).getDeclaredConstructor(HashMap.class).newInstance(fp.getGlobalVars());
			myGraph = new Graph(ruleset,fp);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(filename.getAbsolutePath());
			throw new IllegalArgumentException("Invalid filepath");
		}
		//	sets up simulation to run with particular file specifications
		myVis.ammendHeader("Currently running: " + filename.getName());
		play();
	}
}
