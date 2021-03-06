package cellsociety_team10;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author benhubsch
 * @author Elizabeth Shulman
 * @author Andrew Yeung
 * 
 * This class is nothing more than an entry point into the project.
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Engine e = new Engine();
		e.initializeSimulation(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
