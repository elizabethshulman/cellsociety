package cellsociety_team10;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartPage {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	private static final String STYLESHEET_NAME = "main.css";


	private HashMap<String, String> myNameMap = createNameMap();
	private Scene myScene;

	public StartPage(EventHandler<MouseEvent> pred_handler, EventHandler<MouseEvent> seg_handler, EventHandler<MouseEvent> life_handler, EventHandler<MouseEvent> fire_handler) {
		BorderPane border_pane = new BorderPane();
		border_pane.setId("background-bp");
		
		myScene = new Scene(border_pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(STYLESHEET_NAME);

		HBox hbox = new HBox();
		hbox.setId("header-start");

		Text title_text = new Text("Cell Society");
		title_text.setId("title-text");
		hbox.getChildren().add(title_text);

		border_pane.setTop(hbox);
		border_pane.setCenter(buttonBonanza(pred_handler, seg_handler, life_handler, fire_handler));
	}

	private VBox buttonBonanza(EventHandler<MouseEvent> pred_handler, EventHandler<MouseEvent> seg_handler, EventHandler<MouseEvent> life_handler, EventHandler<MouseEvent> fire_handler) {
		VBox vbox = new VBox();
		vbox.setId("box-bonanza");

		Text pick_sim = new Text("Select a simulation:");
		pick_sim.setId("pick-sim");
		
		HBox row_one = createRow(makeButton("life", life_handler), makeButton("fire", fire_handler));
		HBox row_two = createRow(makeButton("predator", pred_handler), makeButton("segregation", seg_handler));
		
		vbox.getChildren().addAll(pick_sim, row_one, row_two);
		return vbox;
	}

	private Button makeButton(String filename, EventHandler<MouseEvent> handler) {
		ImageView image_view = Helper.generateImageView(filename + ".png", 150);
		Button tester = new Button(myNameMap.get(filename), image_view);
		tester.setOnMouseClicked(handler);
		tester.setContentDisplay(ContentDisplay.TOP);
		tester.setId(filename);

		return tester;
	}
	
	private HBox createRow(Button one, Button two) {
		HBox row = new HBox();
		row.setId("box-bonanza");
		row.getChildren().addAll(one, two);
		return row;
	}

	private HashMap<String, String> createNameMap() {
		HashMap<String, String> name_map = new HashMap<String, String>();
		name_map.put("predator", "Predator-Prey Relationships");
		name_map.put("segregation", "Model of Segregation");
		name_map.put("life", "Game of Life");
		name_map.put("fire", "Spreading of Fire");
		return name_map;
	}
	
	public Scene getScene() {
		return myScene;
	}
}
