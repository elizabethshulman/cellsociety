package cellsociety_team10;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Engine extends Application {
	
	private Timeline animation;
	private Grid myGrid;
	private Visualization myVis;
    private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 800;
	
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
		
		myVis = new Visualization(stage, new ControlPanel(SCREEN_WIDTH, SCREEN_HEIGHT));
 		simulationToTimeline();
	}
	
	private void simulationToTimeline() {
		//code from ExampleBounce.java, @author Robert C. Duvall
		
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}
	
	private void step (double elapsedTime) {
		
	}

}
