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
		myControlPanel = new ControlPanel(myAnimation, 
										e -> play(), 
										e -> pause(), 
										e -> end(), 
										e -> next());
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

	private void end() {
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
	
	private void resetEngine() {
		myAnimation.setRate(ANIM_RATE);
		myAnimation.stop();
		
		myVis.reset();
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
		
		resetEngine();
		
		myVis.amendHeader(createHeaderText(fp.getTitle(), fp.getAuthor()));
		myVis.visualizeGraph(myGraph);
		myStage.setScene(myVis.getScene());
	}
	
	private String createHeaderText(String title, String author) {
		return String.format("%s %s %s", title, myResources.getString("By"), author);
	}
}
