package visualComponents;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class Sidebar {
	public static final double WIDTH = 200;
	private static final double MIN_SLIDER = 0;
	private static final double START_SLIDER = 15;
	private static final double MAX_SLIDER = 40;
	private static final double SHAPE_SIZE = 20;

	private VBox myVBox = new VBox();

	public Sidebar(ResourceBundle resources) {
		myVBox.setId("sidebar");
		myVBox.getChildren().addAll(buildSimBox(resources), createSlider(), createSlider(), buildShapeOptions(resources));
	}

	public VBox getVBox() {
		return myVBox;
	}

	private ComboBox<String> buildSimBox(ResourceBundle resources) {
		ObservableList<String> sim_options = FXCollections.observableArrayList(
				resources.getString("LifeButton"),
				resources.getString("FireButton"),
				resources.getString("PredButton"),
				resources.getString("SegButton")
				);
		ComboBox<String> combo = new ComboBox<String>(sim_options);
		combo.setId("combo");

		combo.setValue("Simulation type:");

		return combo;
	}
	
	private HBox buildShapeOptions(ResourceBundle resources) {
		HBox hbox = new HBox();
		RadioButton rb1 = new RadioButton();
		RadioButton rb2 = new RadioButton();
		RadioButton rb3 = new RadioButton();
		rb1.setGraphic(Helper.generateImageView("burgundy.png", SHAPE_SIZE));
		rb2.setGraphic(Helper.generateImageView("triangle1.png", SHAPE_SIZE));
		rb3.setGraphic(Helper.generateImageView("hex1.png", SHAPE_SIZE));
		
		rb1.setSelected(true);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		rb1.setToggleGroup(toggleGroup);
		rb2.setToggleGroup(toggleGroup);
		rb3.setToggleGroup(toggleGroup);
		
		hbox.getChildren().addAll(rb1, rb2, rb3);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(15);
		return hbox;
	}
	
	private Slider createSlider() {
		Slider temp = new Slider(MIN_SLIDER, MAX_SLIDER, START_SLIDER);
		temp.getStyleClass().add("axis");
		temp.setShowTickLabels(true);
		temp.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				System.out.println(Math.round(new_val.doubleValue()));
			}
		});
		temp.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n == MIN_SLIDER) {
					return "0";
				} else if (n == MAX_SLIDER) {
					return "40";
				}
				return "";
			}

			@Override
			public Double fromString(String s) {
				return 0.0;
			}
		});
		return temp;
	}
}
