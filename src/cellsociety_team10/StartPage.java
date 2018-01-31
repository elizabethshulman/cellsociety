package cellsociety_team10;

import java.util.Collection;
import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private static final double BAR_HEIGHT = 85;
	private static final Color BORDER_COLOR = Color.WHITE;
	private static final double BORDER_WIDTH = 1.5;
	private static final double HEADER_SPACING = 30;
	private static final String FONT_URL = "http://fonts.googleapis.com/css?family=Roboto:300";
	private static final Color BACKGROUND_COLOR = Color.rgb(249, 249, 249);
	private static final double BUTTON_SPACING = 30;


	private HashMap<String, String> myNameMap = createNameMap();
	private BorderPane border_pane;

	public StartPage() {
		BorderPane border_pane = new BorderPane();
		Scene scene = new Scene(border_pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		scene.getStylesheets().add(FONT_URL);
		scene.getStylesheets().add("test.css");

		HBox hbox = new HBox();
		hbox.setPrefHeight(BAR_HEIGHT);

		Text title_text = new Text("Cell Society");
		title_text.setFill(Color.rgb(229, 229, 229));
		title_text.setId("title-text");
		hbox.getChildren().add(title_text);

		BackgroundFill myBF = new BackgroundFill(Color.rgb(0, 137, 117), new CornerRadii(0), null);
		hbox.setBackground(new Background(myBF));

		BorderStroke myB = new BorderStroke(null, null, BORDER_COLOR, null, 
				null, null, BorderStrokeStyle.SOLID, null,
				new CornerRadii(0), new BorderWidths(BORDER_WIDTH), null);
		hbox.setBorder(new Border(myB));

		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(HEADER_SPACING);

		border_pane.setTop(hbox);
		border_pane.setCenter(buttonBonanza());

		BackgroundFill myBF2 = new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(0), null);
		border_pane.setBackground(new Background(myBF2));
	}

	private VBox buttonBonanza() {
		VBox vbox = new VBox();

		Text pick_sim = new Text("Pick a simulation to run:");
		pick_sim.setId("pick-sim");

		vbox.getChildren().addAll(pick_sim, 
				createRowOfButtons(new String[] {"life", "fire"}), 
				createRowOfButtons(new String[] {"predator", "segregation"}));
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(BUTTON_SPACING);

		return vbox;
	}

	private Button makeButton(String filename) {
		ImageView image_view = Helper.generateImageView(filename + ".png", 150);
		Button tester = new Button(myNameMap.get(filename), image_view);
		
		tester.setContentDisplay(ContentDisplay.TOP);
		tester.setId(filename);

		return tester;
	}
	
	private HBox createRowOfButtons(String[] names) {
		HBox row = new HBox();
		
		for (String s : names) {
			row.getChildren().add(makeButton(s));
		}
		
		row.setSpacing(BUTTON_SPACING);
		row.setAlignment(Pos.CENTER);
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
	
	public BorderPane getBorderPane() {
		return border_pane;
	}
}
