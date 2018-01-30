package cellsociety_team10;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * 
 * @author Ben Hubsch
 * 
 * This class represents the status bar shown at the top of every level,
 * informing users of their current level, their score, and the number
 * of lives they have remaining.
 *
 */
public class StatusBar {
	private static final int BAR_HEIGHT = 75;
	private double HORIZ_SPACING = 25;
	private int BORDER_WIDTH = 3;
	private String DIVIDER_COLOR = "rgb(236,238,237)";
	private String TEXT_COLOR = "rgb(251,251,251)";
	private String BACKGROUND_COLOR = "rgb(54,68,97)";
	private String FONT_SIZE = "40";
	private String START_LIVES = "3"; 

	private HBox hbox;
	private Text lives_text;
	private Text score_text;
	private BorderPane border_pane;

	/**
	 * This is the constructor for the StatusBar object.
	 * @param header This is the BorderPane that we will use to organize the bar.
	 * @param level_num This is the level number corresponding to this StatusBar.
	 * @param width This is the desired width of StatusBar (equal to the width of
	 * the screen). 
	 * @param height This is the desired height of the StatusBar.
	 * @param score This is the score of the Level for the StatusBar to display. In 
	 * this implementation, the initial score is always 0. 
	 */
	public StatusBar(String sim_tye) {

		hbox = new HBox();
		hbox.setPrefHeight(BAR_HEIGHT);
//		hbox.setPrefWidth(width);

		Text level_text = new Text ("Level " + Integer.toString(0));
		level_text.setFill(Color.web(TEXT_COLOR));
		level_text.setStyle("-fx-font-family: 'Roboto Mono', monospace; -fx-font-size: " + FONT_SIZE + ";");
		hbox.getChildren().add(level_text);


		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setStyle("-fx-color: " + DIVIDER_COLOR);
		separator.setPadding(new Insets(0, HORIZ_SPACING, 0, HORIZ_SPACING));
		hbox.getChildren().add(separator);

		lives_text = new Text ("Game of Life");
		lives_text.setFill(Color.web(TEXT_COLOR));
		lives_text.setStyle("-fx-font-family: 'Roboto Mono', monospace; -fx-font-size: " + FONT_SIZE + ";");
		hbox.getChildren().add(lives_text);

		Separator separator2 = new Separator();
		separator2.setOrientation(Orientation.VERTICAL);
		separator2.setStyle("-fx-color: " + DIVIDER_COLOR);
		separator2.setPadding(new Insets(0, HORIZ_SPACING, 0, HORIZ_SPACING));
		hbox.getChildren().add(separator2);

		score_text = new Text ("Score: " + Integer.toString(1000));
		score_text.setFill(Color.web(TEXT_COLOR));
		score_text.setStyle("-fx-font-family: 'Roboto Mono', monospace; -fx-font-size: " + FONT_SIZE + ";");
		hbox.getChildren().add(score_text);

		BackgroundFill myBF = new BackgroundFill(Color.web(BACKGROUND_COLOR), new CornerRadii(0), null);
		// then you set to your node or container or layout
		hbox.setBackground(new Background(myBF));


		BorderStroke myB = new BorderStroke(null, null, Color.web(DIVIDER_COLOR), null, 
				null, null, BorderStrokeStyle.SOLID, null,
				new CornerRadii(0), new BorderWidths(BORDER_WIDTH), null);
		hbox.setBorder(new Border(myB));

		hbox.setAlignment(Pos.CENTER);
	}

	/**
	 * This is effectively a setter a function on the lives and score, which
	 * are displayed as Text objects.
	 * @param lives This is the number of lives the player has remaining.
	 * @param score This is the players current score for the level.
	 */
	public void updateText(int lives, int score) {
		lives_text.setText("Lives: " + Integer.toString(lives));
		score_text.setText("Score: " + Integer.toString(score));
	}

	/**
	 * This is a getter on the StatusBar's HBox property. 
	 * @return HBox This is the StatusBar's HBox property.
	 */
	public HBox getHBox() {
		return hbox;
	}

	/**
	 * This is a getter on the StatusBar's BorderPane property.
	 * @return BorderPane This is the StatusBar's BorderPane property.
	 */
	public BorderPane getBorderPane() {
		return border_pane;
	}
}
