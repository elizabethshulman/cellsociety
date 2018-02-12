package visualComponents;

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

/**
 * @author benhubsch
 * 
 * The Class StartPage represents the opening page that a user sees upon launching
 * the application. It relies on a number of EventHandlers being set properly in Engine,
 * which it then calls on-click. Fundamentally the StartPage class builds a Scene that
 * can then be re-used over and over.
 */
public class StartPage {
	private static final double SCREEN_HEIGHT = 750;
	private static final double SCREEN_WIDTH = 700;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	private static final String STYLESHEET_NAME = "main.css";
	private static final double IMAGE_SIZE = 75;


	private HashMap<String, String> myNameMap;
	private Scene myScene;
	private ResourceBundle myResources;

	/**
	 * Instantiates a new StartPage object.
	 *
	 * @param resource_bundle This is the object that stores translations from the .properties files.
	 * @param pred_handler This is a handler on-click for the Predator-Prey simulation.
	 * @param seg_handler This is a handler on-click for the Segregation simulation.
	 * @param life_handler This is a handler on-click for the Game of Life simulation.
	 * @param fire_handler This is a handler on-click for the Spreading Fire simulation.
	 * @param ant_handler This is a handler on-click for the Foraging Ant simulation.
	 * @param rps_handler This is a handler on-click for the Rocks, Paper, Scissors. simulation.
	 * @param diy_handler This is a handler on-click for the DIY mode.
	 */
	public StartPage(ResourceBundle resource_bundle, EventHandler<MouseEvent> pred_handler, 
			EventHandler<MouseEvent> seg_handler, EventHandler<MouseEvent> life_handler, 
			EventHandler<MouseEvent> fire_handler, EventHandler<MouseEvent> ant_handler, 
			EventHandler<MouseEvent> rps_handler, EventHandler<MouseEvent> diy_handler) {
		myResources = resource_bundle;
		
		BorderPane border_pane = new BorderPane();
		border_pane.setId("background-bp");

		myScene = new Scene(border_pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(FONT_URL);
		myScene.getStylesheets().add(STYLESHEET_NAME);

		createNameMap();

		border_pane.setTop(new HeaderBar(myResources.getString("Title")).getHBox());
		border_pane.setCenter(buttonBonanza(pred_handler, seg_handler, life_handler, fire_handler, diy_handler, ant_handler, rps_handler));
	}

	/**
	 * This class builds the main portion of the StartPage, which is the collection
	 * of buttons that the user is invited to click to start a simulation.
	 *
	 * @param pred_handler This is a handler on-click for the Predator-Prey simulation.
	 * @param seg_handler This is a handler on-click for the Segregation simulation.
	 * @param life_handler This is a handler on-click for the Game of Life simulation.
	 * @param fire_handler This is a handler on-click for the Spreading Fire simulation.
	 * @param diy_handler This is a handler on-click for the DIY mode.
	 * @param ant_handler This is a handler on-click for the Foraging Ant simulation.
	 * @param rps_handler This is a handler on-click for the Rocks, Paper, Scissors. simulation.
	 * @return VBox
	 */
	private VBox buttonBonanza(EventHandler<MouseEvent> pred_handler, EventHandler<MouseEvent> seg_handler, 
			EventHandler<MouseEvent> life_handler, EventHandler<MouseEvent> fire_handler,
			EventHandler<MouseEvent> diy_handler, EventHandler<MouseEvent> ant_handler,
			EventHandler<MouseEvent> rps_handler) {
		VBox vbox = new VBox();
		vbox.setId("box-bonanza");

		Text pick_sim = new Text(myResources.getString("SelectSim"));
		pick_sim.setId("pick-sim");

		HBox row_one = createRow(makeButton("life", life_handler), makeButton("ant", ant_handler), makeButton("fire", fire_handler));
		HBox row_two = createRow(makeButton("predator", pred_handler), makeButton("rps", rps_handler), makeButton("segregation", seg_handler));
		
		Text diy_sim = new Text(myResources.getString("DIY"));
		diy_sim.setId("pick-sim");
		Button diy_button = makeButton("diy", diy_handler);

		vbox.getChildren().addAll(pick_sim, row_one, row_two, diy_sim, diy_button);
		return vbox;
	}

	/**
	 * This is a helper function that creates the StartPage buttons with text and 
	 * the png file.
	 *
	 * @param filename The name under which the .png file exists.
	 * @param handler The on-click event handler.
	 * @return Button
	 */
	private Button makeButton(String filename, EventHandler<MouseEvent> handler) {
		ImageView image_view = Helper.generateImageView(filename + ".png", IMAGE_SIZE);
		Button tester = new Button(myNameMap.get(filename), image_view);
		tester.setOnMouseClicked(handler);
		tester.setId(filename);

		return tester;
	}

	/**
	 * Creates a given row of buttons on the StartPage.
	 *
	 * @param one the one
	 * @param two the two
	 * @param three the three
	 * @return HBox
	 */
	private HBox createRow(Button one, Button two, Button three) {
		HBox row = new HBox();
		row.setId("box-bonanza");
		row.getChildren().addAll(one, two, three);
		return row;
	}

	/**
	 * Creates the name map for the buttons to match file names to text content.
	 */
	private void createNameMap() {
		myNameMap = new HashMap<>();
		myNameMap.put("predator", myResources.getString("PredButton"));
		myNameMap.put("segregation", myResources.getString("SegButton"));
		myNameMap.put("life", myResources.getString("LifeButton"));
		myNameMap.put("fire", myResources.getString("FireButton"));
		myNameMap.put("ant", myResources.getString("AntButton"));
		myNameMap.put("rps", myResources.getString("RPSButton"));
	}

	/**
	 * Gets the Scene object.
	 *
	 * @return Scene
	 */
	public Scene getScene() {
		return myScene;
	}
}
