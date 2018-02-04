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
import rulesVariants.Rules;

public class Engine extends Application {
	private static final double ANIM_RATE = 2.5;
	private static final int MILLISECOND_DELAY = 500;
	private static final String SIM_FOLDER = "data/simulations/";
	private static final String LANGUAGE = "English";

	private ResourceBundle myResources;
	private Timeline myAnimation;        
	private Graph myGraph;
	private Visualization myVis;
	private Scene myStartScene;
	private Stage myStage;
	private FileChooser myFileChooser;
	private RulesFactory myRulesFactory;
	private ControlPanel myControlPanel;

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
		
		myResources = ResourceBundle.getBundle(LANGUAGE);
		
		myStartScene = new StartPage(myResources,
									e -> showFileChooser("predator"),
									e -> showFileChooser("segregation"),
									e -> showFileChooser("life"), 
									e -> showFileChooser("fire")).getScene();
		
		myFileChooser = new FileChooser();
		myFileChooser.setTitle(myResources.getString("FileTitle"));
		
		myRulesFactory = new RulesFactory();

		myAnimation = new Timeline();
		myControlPanel = new ControlPanel(myAnimation, e -> play(), e -> pause(), e -> end(), e -> next());
		myVis = new Visualization(myControlPanel);
		setupAnimation();
		
		myStage.setScene(myStartScene);
		myStage.setTitle(myResources.getString("Title"));
		myStage.show();
	}

	private void setupAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step());
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.setRate(ANIM_RATE);
	}

	private void step() {
		if (myGraph.isDead()) {
			myAnimation.pause();
			myAnimation.setRate(0);
			myControlPanel.disableButtons();
			return;
		}
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
		step();
	}

	private void showFileChooser(String directory) {
		String source = SIM_FOLDER + directory;
		myFileChooser.setInitialDirectory(new File(source));
		File f = myFileChooser.showOpenDialog(myStage);
		if (f != null) {
			handleChosenFile(f);
		}
	}
	
	private void resetState() {
		myControlPanel.enableButtons();
		myAnimation.setRate(ANIM_RATE);
	}
	
	private void handleChosenFile(File filename) {
		FileProcessor fp;
		try {
			fp = new FileProcessor(filename.getAbsolutePath());
			fp.readFile();
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filepath.");
		}
		
		Rules curr_rules = myRulesFactory.createRules(fp.getType(), fp.getGlobalVars());
		myGraph = new Graph(curr_rules, fp);
		
		resetState();
		
		myVis.amendHeader(buildHeader(fp.getTitle(), fp.getAuthor()));
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}
	
	private String buildHeader(String title, String author) {
		return String.format("%s %s %s", title, myResources.getString("By"), author);
	}
}
