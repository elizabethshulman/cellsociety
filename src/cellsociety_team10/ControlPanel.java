package cellsociety_team10;

import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class ControlPanel {
	private static final String PLAY = "play.png";
	private static final String PAUSE = "pause.png";
	private static final String STOP = "stop.png";
	private static final String NEXT = "next.png";
	
	private VBox myVBox;
	private Text myIterationCount;
	private Timeline myAnimation;
	private Slider mySlider;

	public ControlPanel(Timeline animation, EventHandler<MouseEvent> play_handler, EventHandler<MouseEvent> pause_handler, EventHandler<MouseEvent> stop_handler, EventHandler<MouseEvent> next_handler) {
		myAnimation = animation;
		
		myVBox = new VBox();
		HBox hb_top = topHBox();
		HBox hb_bottom = bottomHBox(play_handler, pause_handler, stop_handler, next_handler);
		myVBox.getChildren().add(hb_top);
		myVBox.getChildren().add(hb_bottom);
		BackgroundFill myBF = new BackgroundFill(Color.rgb(0, 137, 117), new CornerRadii(0), null);
		myVBox.setBackground(new Background(myBF));
		myVBox.setAlignment(Pos.CENTER);
	}
	
	private HBox bottomHBox(EventHandler<MouseEvent> play_handler, EventHandler<MouseEvent> pause_handler, EventHandler<MouseEvent> stop_handler, EventHandler<MouseEvent> next_handler) {
		HBox hb_bottom = new HBox();
		
		hb_bottom.getChildren().add(makeButton(PLAY, play_handler));
		hb_bottom.getChildren().add(makeButton(PAUSE, pause_handler));
		hb_bottom.getChildren().add(makeButton(STOP, stop_handler));
		hb_bottom.getChildren().add(makeButton(NEXT, next_handler));
		
		hb_bottom.setSpacing(30);
		hb_bottom.setAlignment(Pos.CENTER);
		hb_bottom.setPadding(new Insets(30,0,30,0));
		
		return hb_bottom;
	}
	
	private HBox topHBox() {
		HBox hb_top = new HBox();
		
		myIterationCount = new Text("Iteration Count: 0");
		myIterationCount.setId("main-text");
		myIterationCount.setFill(Color.rgb(214, 214, 214));
		hb_top.getChildren().add(myIterationCount);
		
		HBox slider_hbox = new HBox();
		Text animation_speed = new Text("Animation Speed:");
		animation_speed.setId("main-text");
		animation_speed.setFill(Color.rgb(214, 214, 214));
		hb_top.getChildren().add(animation_speed);
		
		mySlider = new Slider(0, 100, 50);
		mySlider.setShowTickLabels(true);
		mySlider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n == 0) {
					return "Slowest";
				} else if (n == 100) {
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
            		myAnimation.setRate(new_val.doubleValue() / 20);
            }
        });
		mySlider.getStyleClass().add("axis");
		mySlider.setPadding(new Insets(0,0,-20,0));
		
		slider_hbox.getChildren().add(animation_speed);
		slider_hbox.getChildren().add(mySlider);
		slider_hbox.setAlignment(Pos.CENTER);
		slider_hbox.setSpacing(20);
		
		hb_top.getChildren().add(slider_hbox);
		
		hb_top.setAlignment(Pos.CENTER);
		hb_top.setSpacing(40);
		hb_top.setPadding(new Insets(20,0,0,0));
		
		return hb_top;
	}
	
	private Button makeButton(String filename, EventHandler<MouseEvent> click_action) {
		ImageView image_view = Helper.generateImageView(filename, 20);
		
		Button temp = new Button();
		temp.setId("control");
		temp.setGraphic(image_view);
		
		temp.setOnMouseClicked(click_action);
		return temp;
	}
	
	public VBox getVBox() {
		return myVBox;
	}
	
	public void setIteration(int iteration) {
		myIterationCount.setText("Iteration Count: " + Integer.toString(iteration));
	}
	
	public void resetSlider() {
		mySlider.setValue(50);
	}
}
