package cellsociety_team10;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public class ControlPanel {
	private static final String PLAY = "play.png";
	private static final String PAUSE = "pause.png";
	private static final String STOP = "stop.png";
	private static final String NEXT = "next.png";
	
	private HBox hb;

	public ControlPanel() {
		hb = new HBox();
		
		hb.getChildren().add(makeButton(PLAY));
		hb.getChildren().add(makeButton(PAUSE));
		hb.getChildren().add(makeButton(STOP));
		hb.getChildren().add(makeButton(NEXT));

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
//                    System.out.println(old_val);
//                    System.out.println(new_val);
            }
        });
		
		hb.getChildren().add(slider);

		hb.setSpacing(20);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(0,0,20,0));
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
	
	public HBox getHBox() {
		return hb;
	}
}
