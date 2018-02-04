package cellsociety_team10;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartPage {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	private static final String STYLESHEET_NAME = "main.css";
	private static final double IMAGE_SIZE = 150;


	private HashMap<String, String> myNameMap;
	private Scene myScene;
	private ResourceBundle myResources;

	public StartPage(ResourceBundle resource_bundle, HandlerHolder handler) {
		myResources = resource_bundle;
		
		BorderPane border_pane = new BorderPane();
		border_pane.setId("background-bp");

		myScene = new Scene(border_pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(STYLESHEET_NAME);

		createNameMap();

		border_pane.setTop(new HeaderBar(myResources.getString("Title")).getHBox());
		border_pane.setCenter(buttonBonanza(handler));
	}

	private VBox buttonBonanza(HandlerHolder handler) {
		VBox vbox = new VBox();
		vbox.setId("box-bonanza");

		Text pick_sim = new Text(myResources.getString("SelectSim"));
		pick_sim.setId("pick-sim");

		HBox row_one = createRow(makeButton("life", handler.getLifeHandler()), makeButton("fire", handler.getFireHandler()));
		HBox row_two = createRow(makeButton("predator", handler.getPredHandler()), makeButton("segregation", handler.getSegHandler()));

		vbox.getChildren().addAll(pick_sim, row_one, row_two);
		return vbox;
	}

	private Button makeButton(String filename, EventHandler<MouseEvent> handler) {
		ImageView image_view = Helper.generateImageView(filename + ".png", IMAGE_SIZE);
		Button tester = new Button(myNameMap.get(filename), image_view);
		tester.setOnMouseClicked(handler);
		tester.setId(filename);

		return tester;
	}

	private HBox createRow(Button one, Button two) {
		HBox row = new HBox();
		row.setId("box-bonanza");
		row.getChildren().addAll(one, two);
		return row;
	}

	private void createNameMap() {
		myNameMap = new HashMap<String, String>();
		myNameMap.put("predator", myResources.getString("PredButton"));
		myNameMap.put("segregation", myResources.getString("SegButton"));
		myNameMap.put("life", myResources.getString("LifeButton"));
		myNameMap.put("fire", myResources.getString("FireButton"));
	}

	public Scene getScene() {
		return myScene;
	}
}
