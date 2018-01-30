package cellsociety_team10;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	
	
	private Stage stage;
	private StatusBar bar;
	
	public Visualization(Stage primaryStage, ControlPanel cp) {
		bar = new StatusBar("Game of Life");
		
		stage = primaryStage;
		Group root = new Group();
		Scene opening_scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		stage.setTitle("Cell Society");
		stage.setScene(opening_scene);
		stage.show();
	}
	
	public void visualizeGrid() {
		BorderPane border_pane = new BorderPane();
		Scene scene = new Scene(border_pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		scene.getStylesheets().add("main.css");
		
		border_pane.setTop(bar.getHBox());
		border_pane.setCenter(new GridContainer().getVBox());
		border_pane.setBottom(new ControlPanel().getHBox());
		
		stage.setScene(scene);
	}
	

}
