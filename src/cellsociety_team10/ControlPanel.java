package cellsociety_team10;

import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	private VBox control_panel;
	private Text iteration_count;
	private Timeline animation;

	public ControlPanel(Timeline curr_animation) {
		animation = curr_animation;
		
		control_panel = new VBox();
		HBox hb_top = topHBox();
		HBox hb_bottom = bottomHBox();
		control_panel.getChildren().add(hb_top);
		control_panel.getChildren().add(hb_bottom);
		BackgroundFill myBF = new BackgroundFill(Color.rgb(0, 137, 117), new CornerRadii(0), null);
		control_panel.setBackground(new Background(myBF));
		control_panel.setAlignment(Pos.CENTER);
	}
	
	private HBox bottomHBox() {
		HBox hb_bottom = new HBox();
		
		hb_bottom.getChildren().add(makeButton(PLAY));
		hb_bottom.getChildren().add(makeButton(PAUSE));
		hb_bottom.getChildren().add(makeButton(STOP));
		hb_bottom.getChildren().add(makeButton(NEXT));
		
		hb_bottom.setSpacing(30);
		hb_bottom.setAlignment(Pos.CENTER);
		hb_bottom.setPadding(new Insets(30,0,30,0));
		
		return hb_bottom;
	}
	
	private HBox topHBox() {
		HBox hb_top = new HBox();
		
		iteration_count = new Text("Iteration Count: 0");
		iteration_count.setId("main-text");
		iteration_count.setFill(Color.rgb(214, 214, 214));
		hb_top.getChildren().add(iteration_count);
		
		HBox slider_hbox = new HBox();
		Text animation_speed = new Text("Animation Speed:");
		animation_speed.setId("main-text");
		animation_speed.setFill(Color.rgb(214, 214, 214));
		hb_top.getChildren().add(animation_speed);
		
		Slider slider = new Slider(0, 100, 50);
		slider.setShowTickLabels(true);
		slider.setLabelFormatter(new StringConverter<Double>() {
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
				switch (s) {
				case "Slowest":
					return 0.0;
				case "Fast":
					return 100.0;
				default:
					return 1d;
				}
			}
		});
		slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            		animation.setRate(new_val.doubleValue() / 20);
            }
        });
		slider.getStyleClass().add("axis");
		slider.setPadding(new Insets(0,0,-20,0));
		
		slider_hbox.getChildren().add(animation_speed);
		slider_hbox.getChildren().add(slider);
		slider_hbox.setAlignment(Pos.CENTER);
		slider_hbox.setSpacing(20);
		
		hb_top.getChildren().add(slider_hbox);
		
		hb_top.setAlignment(Pos.CENTER);
		hb_top.setSpacing(40);
		hb_top.setPadding(new Insets(20,0,0,0));
		
		return hb_top;
	}
	
	private Button makeButton(String filename) {
		Button temp = new Button();
		temp.getStyleClass().add("button");
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(filename));
		ImageView image_view = new ImageView(image);
		image_view.setPreserveRatio(true);
		image_view.setFitHeight(20);
		temp.setGraphic(image_view);
		return temp;
	}
	
	public VBox getVBox() {
		return control_panel;
	}
	
	public void setIteration(int iteration) {
		iteration_count.setText("Iteration Count: " + Integer.toString(iteration));
	}
}
