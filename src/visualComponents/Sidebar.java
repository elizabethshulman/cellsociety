package visualComponents;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import cellsociety_team10.Engine;
import graphVariants.Graph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class Sidebar {
	public static final double WIDTH = 200;
	private static final double MIN_SLIDER = 4;
	private static final double START_SLIDER = 8;
	private static final double MAX_SLIDER = 40;
	private static final double SHAPE_SIZE = 20;

	private VBox myVBox = new VBox();
	private Engine myEngine;

	public Sidebar(ResourceBundle resources, Engine engine, Graph graph) {
		myEngine = engine;
		myVBox.setId("sidebar");
		Slider rows = createSlider();
		Slider cols = createSlider();

		textAndComponent(resources.getString("PickSim"), buildSimBox(resources));
		textAndComponent(resources.getString("Width"), rows);
		textAndComponent(resources.getString("Height"), cols);
		textAndComponent(resources.getString("Shape"), buildShapeOptions(resources));
		textAndComponent(resources.getString("Shape"), buildEdgeBox(resources));
		
		rows.setOnMouseReleased(e -> {
			graph.adjustRows((int) rows.getValue());
		});
		
		cols.setOnMouseReleased(e -> {
			graph.adjustCols((int) cols.getValue());
		});
	}

	private void textAndComponent(String text, Node node) {
		VBox pair = new VBox();
		pair.setId("pairing");
		Text temp = new Text(text);
		temp.setId("text-sidebar");
		
		HBox left_align = new HBox();
		left_align.setId("left-align");
		left_align.getChildren().add(temp);
		
		pair.getChildren().addAll(left_align, node);
		myVBox.getChildren().add(pair);
	}

	public VBox getVBox() {
		return myVBox;
	}

	private ComboBox<String> buildSimBox(ResourceBundle resources) {
		Map<String, String> translations = initTranslations(resources);
		ObservableList<String> sim_options = FXCollections.observableArrayList(
				resources.getString("LifeButton"),
				resources.getString("FireButton"),
				resources.getString("PredButton"),
				resources.getString("SegButton")
				);
		ComboBox<String> combo = new ComboBox<String>(sim_options);
		combo.setId("combo");
		combo.getSelectionModel().selectFirst();
		combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> ov,
	        		String old_val, String new_val) {
	        		myEngine.loadSimulation(new File(translations.get(new_val)));
			}
	    });

		return combo;
	}
	
	private ComboBox<String> buildEdgeBox(ResourceBundle resources) {
		ObservableList<String> edge_options = FXCollections.observableArrayList(
				resources.getString("FiniteEdge"),
				resources.getString("ToroidalEdge")
				);
		
		ComboBox<String> combo = new ComboBox<String>(edge_options);
		combo.setId("combo");
		combo.getSelectionModel().selectFirst();
		combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> ov,
	        		String old_val, String new_val) {
	        		System.out.println(new_val);;
			}
	    });

		return combo;
	}
	
	private Map<String, String> initTranslations(ResourceBundle resources) {
		HashMap<String, String> translations = new HashMap<>();
		translations.put(resources.getString("LifeButton"), "data/simulations/life/gameoflife1.xml");
		translations.put(resources.getString("FireButton"), "data/simulations/fire/fire1.xml");
		translations.put(resources.getString("PredButton"), "data/simulations/predator/predatorprey1.xml");
		translations.put(resources.getString("SegButton"), "data/simulations/segregation/segregation1.xml");
		return translations;
	}
	
	private HBox buildShapeOptions(ResourceBundle resources) {
		HBox hbox = new HBox();
		hbox.setId("shape-hbox");
		RadioButton rb1 = createRadioButton("sidebar_square.png");
		rb1.setId("radio");
		rb1.setSelected(true);
		RadioButton rb2 = createRadioButton("sidebar_triangle.png");
		rb2.setId("radio");
		RadioButton rb3 = createRadioButton("sidebar_hex.png");
		rb3.setId("radio");
		
		
		ToggleGroup toggleGroup = new ToggleGroup();
		rb1.setToggleGroup(toggleGroup);
		rb2.setToggleGroup(toggleGroup);
		rb3.setToggleGroup(toggleGroup);
		
		hbox.getChildren().addAll(rb1, rb2, rb3);
		return hbox;
	}
	
	private RadioButton createRadioButton(String filename) {
		RadioButton temp = new RadioButton();
		temp.setGraphic(Helper.generateImageView(filename, SHAPE_SIZE));
		return temp;
	}
	
	private Slider createSlider() {
		Slider temp = new Slider(MIN_SLIDER, MAX_SLIDER, START_SLIDER);
		temp.getStyleClass().add("axis");
		temp.setId("slider-sidebar");

		temp.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n == MIN_SLIDER) {
					return "  4";
				} else if (n == MAX_SLIDER) {
					return "40   ";
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
