package visualComponents;

import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * @author benhubsch
 * 
 * The ControlPanel class builds the part of the UI that appears at the bottom of the
 * screen containing components that control the simulation. It depends on the Timeline
 * passed in from Engine in order to set the animation rate depending on the position
 * of the slider.
 */
public class ControlPanel {
	private static final String PLAY = "play.png";
	private static final String PAUSE = "pause.png";
	private static final String STOP = "stop.png";
	private static final String NEXT = "next.png";
	private static final String SAVE = "save.png";
	private static final double MIN_SLIDER = 0;
	private static final double MAX_SLIDER = 100;

	private VBox myVBox;
	private Text myIterationCount;
	private Timeline myAnimation;
	private Slider mySlider;
	private HBox myButtonBox;
	private Button myStopButton;
	private Button mySaveButton;

	
	/**
	 * Instantiates a new ControlPanel object.
	 *
	 * @param animation This is the animation that advances the simulation.
	 * @param play_handler This is an event handler on the Play button.
	 * @param pause_handler This is an event handler on the Pause button.
	 * @param stop_handler This is an event handler on the Stop button.
	 * @param next_handler This is an event handler on the Next button.
	 * @param save_handler This is an event handler on the Save button.
	 */
	public ControlPanel(Timeline animation, EventHandler<MouseEvent> play_handler, EventHandler<MouseEvent> pause_handler, EventHandler<MouseEvent> stop_handler, EventHandler<MouseEvent> next_handler, EventHandler<MouseEvent> save_handler) {
		myAnimation = animation;

		myVBox = new VBox();
		myVBox.setId("control-panel-overall");

		HBox hb_top = topHBox();
		bottomHBox(play_handler, pause_handler, stop_handler, next_handler, save_handler);
		myVBox.getChildren().addAll(hb_top, myButtonBox);
	}

	/**
	 * This function arranges the buttons on the bottom row of the ControlPanel.
	 *
	 * @param play_handler This is an event handler on the Play button.
	 * @param pause_handler This is an event handler on the Pause button.
	 * @param stop_handler This is an event handler on the Stop button.
	 * @param next_handler This is an event handler on the Next button.
	 * @param save_handler This is an event handler on the Save button.
	 */
	private void bottomHBox(EventHandler<MouseEvent> play_handler, EventHandler<MouseEvent> pause_handler, EventHandler<MouseEvent> stop_handler, EventHandler<MouseEvent> next_handler, EventHandler<MouseEvent> save_handler) {
		myButtonBox = new HBox();
		myButtonBox.setId("control-panel-bottom");

		myStopButton = makeButton(STOP, stop_handler);
		mySaveButton = makeButton(SAVE, save_handler);
		myButtonBox.getChildren().addAll(makeButton(PLAY, play_handler),
				makeButton(PAUSE, pause_handler),
				myStopButton,
				makeButton(NEXT, next_handler),
				mySaveButton);
	}

	/**
	 * This function arranges the buttons on the top row of the ControlPanel.
	 *
	 * @return HBox
	 */
	private HBox topHBox() {
		HBox hb_top = new HBox();
		hb_top.setId("control-panel-top");

		myIterationCount = new Text("Iteration Count: 0");
		myIterationCount.setId("cp-text");
		hb_top.getChildren().add(myIterationCount);

		HBox slider_hbox = new HBox();
		slider_hbox.setId("slider-hbox");

		Text animation_speed = new Text("Animation Speed:");
		animation_speed.setId("cp-text");
		hb_top.getChildren().add(animation_speed);

		mySlider = new Slider(MIN_SLIDER, MAX_SLIDER, (MAX_SLIDER + MIN_SLIDER) / 2);		
		mySlider.getStyleClass().add("axis");
		mySlider.setShowTickLabels(true);
		mySlider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n == MIN_SLIDER) {
					return "Slowest";
				} else if (n == MAX_SLIDER) {
					return "Fastest";
				}
				return "";
			}

			@Override
			public Double fromString(String s) {
				return 0.0;
			}
		});
		mySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				myAnimation.setRate(new_val.doubleValue() / 20 + 0.1);
			}
		});

		slider_hbox.getChildren().addAll(animation_speed, mySlider);

		hb_top.getChildren().add(slider_hbox);

		return hb_top;
	}

	/**
	 * This is a helper function that creates a button with an image on it.
	 *
	 * @param filename the filename
	 * @param click_action the click action
	 * @return Button
	 */
	private Button makeButton(String filename, EventHandler<MouseEvent> click_action) {
		ImageView image_view = Helper.generateImageView(filename, 20);

		Button temp = new Button();
		temp.setGraphic(image_view);

		temp.setOnMouseClicked(click_action);
		return temp;
	}

	/**
	 * Gets the VBox object.
	 *
	 * @return VBox
	 */
	public VBox getVBox() {
		return myVBox;
	}

	/**
	 * Sets the iteration.
	 *
	 * @param iteration the new iteration
	 */
	public void setIteration(int iteration) {
		myIterationCount.setText("Iteration Count: " + Integer.toString(iteration));
	}

	/**
	 * Reset slider.
	 */
	public void resetSlider() {
		mySlider.setValue((MAX_SLIDER + MIN_SLIDER) / 2);
	}

	/**
	 * Disable buttons.
	 */
	public void disableButtons() {
		for (Node curr : myButtonBox.getChildren()) {
			if (curr != myStopButton && curr != mySaveButton) {
				curr.setId("control_disabled");
			}
		}
	}

	/**
	 * Enable buttons.
	 */
	public void enableButtons() {
		for (Node curr : myButtonBox.getChildren()) {
			curr.setId("control");
		}
	}
}
