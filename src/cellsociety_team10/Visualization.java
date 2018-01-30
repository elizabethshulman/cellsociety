package cellsociety_team10;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Visualization {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	
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
		scene.getStylesheets().add(FONT_URL);
		scene.getStylesheets().add("main.css");
		
		border_pane.setTop(bar.getHBox());
		border_pane.setCenter(new GridContainer().getVBox());
		border_pane.setBottom(new ControlPanel().getHBox());
		
		BackgroundFill myBF = new BackgroundFill(Color.rgb(249, 249, 249), new CornerRadii(0), null);
		border_pane.setBackground(new Background(myBF));
		
		stage.setScene(scene);
	}
	

}
