package cellsociety_team10;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public class ControlPanel {
	
	private HBox hb;

	public ControlPanel() {
		hb = new HBox();
		
		hb.getChildren().add(makeButton("Play"));
		hb.getChildren().add(makeButton("Pause"));
		hb.getChildren().add(makeButton("Stop"));
		hb.getChildren().add(makeButton("Next"));

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
                    System.out.println(old_val);
                    System.out.println(new_val);
            }
        });
		
		hb.getChildren().add(slider);

		hb.setSpacing(20);
	}
	
	private Button makeButton(String title) {
		Button temp = new Button(title);
		temp.getStyleClass().add("button");
		return temp;
	}
	
	public HBox getHBox() {
		return hb;
	}
}
