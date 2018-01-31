package cellsociety_team10;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Engine extends Application {
	private static final int MILLISECOND_DELAY = 500;
	private static final double SECOND_DELAY = 50.0;
	private static final double START_RATE = 2.5;

	private Timeline animation;        
	private Grid myGrid;
	private Visualization myVis;
	private Scene myStartScene;
	private Stage myStage;

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
		myStartScene = new StartPage(e -> selectFile("predator"),
									e -> selectFile("segregation"),
									e -> selectFile("life"), 
									e -> selectFile("fire")).getScene();
		
		myStage.setScene(myStartScene);
		myStage.setTitle("Cell Society");
		myStage.show();
		
		animation = new Timeline();
		myVis = new Visualization(new ControlPanel(animation, e -> play(), e -> pause(), e -> end(), e -> next()));
		setupAnimation();
	}

	private void setupAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.setRate(START_RATE);
	}

	private void step(double elapsedTime) {	
		myVis.visualizeGrid(null);
		myStage.setScene(myVis.getScene());
	}

	private void pause() {
		animation.pause();
	}

	private void play() {
		animation.play();
	}
	
	// would be called stop, but stop can't be overwritten with a lower
	// visibility since it's implemented in the Application class
	private void end() {
		animation.stop();
		myVis.reset();
		myStage.setScene(myStartScene);
		
	}
	
	private void next() {
		animation.pause();
		step(SECOND_DELAY);
	}
	
	private void selectFile(String filename) {
		//	sets up simulation to run with particular file specifications
		System.out.println(filename);
		play();
	}
}
