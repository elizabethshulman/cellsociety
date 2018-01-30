package cellsociety_team10;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Engine extends Application {
	private static final int MILLISECOND_DELAY = 500;
    private static final double SECOND_DELAY = 50.0;
    private static final double START_RATE = 2.5;
	
	private Timeline animation;        
	private Grid myGrid;
	private Visualization myVis;
	
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
		animation = new Timeline();
 		simulationToTimeline();
 		myVis = new Visualization(stage, new ControlPanel(animation));	
	}
	
	private void simulationToTimeline() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        animation.setRate(START_RATE);
	}
	
	private void step (double elapsedTime) {
		// get new hashmap here from Graph which we can pass as the 
		// argument to visualizeGrid		
		myVis.visualizeGrid(null);
	}

}
