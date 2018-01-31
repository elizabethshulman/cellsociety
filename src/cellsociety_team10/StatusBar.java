package cellsociety_team10;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
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
	private static final int BAR_HEIGHT = 85;
	private static final double BORDER_WIDTH = 1.5;
	private static final Color BORDER_COLOR = Color.WHITE;
	private static final double HEADER_SPACING = 30;

	private HBox hbox;
	private Text lives_text;
	private Text score_text;

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
	public StatusBar(String sim_type) {

		hbox = new HBox();
		hbox.setPrefHeight(BAR_HEIGHT);
		
		score_text = new Text(sim_type);
		score_text.setFill(Color.rgb(229, 229, 229));
		score_text.setId("title-text");
		hbox.getChildren().add(score_text);

		BackgroundFill myBF = new BackgroundFill(Color.rgb(0, 137, 117), new CornerRadii(0), null);
		hbox.setBackground(new Background(myBF));


		BorderStroke myB = new BorderStroke(null, null, BORDER_COLOR, null, 
				null, null, BorderStrokeStyle.SOLID, null,
				new CornerRadii(0), new BorderWidths(BORDER_WIDTH), null);
		hbox.setBorder(new Border(myB));

		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(HEADER_SPACING);
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
}
