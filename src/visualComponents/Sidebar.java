package visualComponents;

import java.io.File;
import java.util.HashMap;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class Sidebar {
	public static final double WIDTH = 200;
	private static final double MIN_SLIDER = 4;
	private static final double MAX_SLIDER = 40;
	private static final double SHAPE_SIZE = 20;
	private static final double DIR_SIZE = 40;

	private VBox myVBox = new VBox();
	private Engine myEngine;
	private String myCurrShape = "square";
	private HashMap<String, String> myConversions = new HashMap<>();
	private ResourceBundle myResources;
	private ComboBox<String> mySimBox;
	private Slider myRowsSlider;
	private Slider myColsSlider;

	public Sidebar(ResourceBundle resources, Engine engine, Graph graph) {
		myEngine = engine;
		myVBox.setId("sidebar");
		myRowsSlider = createSlider(graph.getRows());
		myColsSlider = createSlider(graph.getCols());
		
		myResources = resources;
		setTranslations();
		
		buildSimBox(resources);
		textAndComponent(myResources.getString("PickSim"), mySimBox);
		textAndComponent(myResources.getString("Rows"), myRowsSlider);
		textAndComponent(myResources.getString("Columns"), myColsSlider);
		textAndComponent(myResources.getString("Shape"), buildShapeOptions(resources));
		textAndComponent(myResources.getString("EdgeType"), buildEdgeBox(resources));
		textAndComponent(myResources.getString("NeighborType"), buildNeighborOptions(resources));

		setSliders(graph);
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

	private void buildSimBox(ResourceBundle resources) {
		ObservableList<String> sim_options = FXCollections.observableArrayList(
				resources.getString("LifeButton"),
				resources.getString("FireButton"),
				resources.getString("PredButton"),
				resources.getString("SegButton"));
		mySimBox = new ComboBox<String>(sim_options);
		mySimBox.setId("combo");
		mySimBox.getSelectionModel().selectFirst();
		mySimBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				myEngine.loadSimulation(new File(myConversions.get(new_val)));
				myEngine.updateDIY();
			}
		});
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
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				Boolean b;
				if (new_val == "Toroidal") {
					b = true;
				} else {
					b = false;
				}
				myEngine.updateBorders(b);
			}
		});

		return combo;
	}

	private void setTranslations() {
		myConversions.put(myResources.getString("LifeButton"), "data/simulations/default/life/life_" + myCurrShape + ".xml");
		myConversions.put(myResources.getString("FireButton"), "data/simulations/default/fire/fire_" + myCurrShape + ".xml");
		myConversions.put(myResources.getString("PredButton"), "data/simulations/default/predator/pred_" + myCurrShape + ".xml");
		myConversions.put(myResources.getString("SegButton"), "data/simulations/default/segregation/seg_" + myCurrShape + ".xml");
	}

	private HBox buildShapeOptions(ResourceBundle resources) {
		HBox hbox = new HBox();
		hbox.setId("shape-hbox");
		RadioButton rb1 = createRadioButton("sidebar_square.png", "square", SHAPE_SIZE);
		rb1.setId("radio");
		rb1.setSelected(true);
		RadioButton rb2 = createRadioButton("sidebar_triangle.png", "triangle", SHAPE_SIZE);
		rb2.setId("radio");
		RadioButton rb3 = createRadioButton("sidebar_hex.png", "hex", SHAPE_SIZE);
		rb3.setId("radio");


		ToggleGroup toggleGroup = new ToggleGroup();
		rb1.setToggleGroup(toggleGroup);
		rb2.setToggleGroup(toggleGroup);
		rb3.setToggleGroup(toggleGroup);

		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
				RadioButton selectedRadioButton = (RadioButton) t1.getToggleGroup().getSelectedToggle();
				myCurrShape = selectedRadioButton.getGraphic().getUserData().toString();
				
				setTranslations();
				
				String curr_sim = mySimBox.getSelectionModel().getSelectedItem().toString();
				myEngine.loadSimulation(new File(myConversions.get(curr_sim)));
				myEngine.updateDIY();
			}
		});

		hbox.getChildren().addAll(rb1, rb2, rb3);
		return hbox;
	}

	private HBox buildNeighborOptions(ResourceBundle resources) {
		HBox hbox = new HBox();
		hbox.setId("edge-hbox");
		RadioButton rb1 = createRadioButton("8dir.png", "diag", DIR_SIZE);
		rb1.setId("radio");
		rb1.setSelected(true);
		RadioButton rb2 = createRadioButton("4dir.png", "adjacent", DIR_SIZE);
		rb2.setId("radio");


		ToggleGroup toggleGroup = new ToggleGroup();
		rb1.setToggleGroup(toggleGroup);
		rb2.setToggleGroup(toggleGroup);
		
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
				RadioButton selectedRadioButton = (RadioButton) t1.getToggleGroup().getSelectedToggle();
				String neighbor_type = selectedRadioButton.getGraphic().getUserData().toString();
				
				Boolean b;
				if (neighbor_type == "diag") {
					b = true;
				} else {
					b = false;
				}
				
				myEngine.updateNeighbors(b);
			}
		});

		hbox.getChildren().addAll(rb1, rb2);
		return hbox;
	}

	private RadioButton createRadioButton(String filename, String shape, double size) {
		RadioButton temp = new RadioButton();
		temp.setGraphic(Helper.generateImageView(filename, size));
		temp.getGraphic().setUserData(shape);
		return temp;
	}

	private Slider createSlider(int beginning_val) {
		Slider temp = new Slider(MIN_SLIDER, MAX_SLIDER, beginning_val);
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
	
	public void setSliders(Graph graph) {
		myRowsSlider.setOnMouseReleased(e -> {
			graph.adjustRows((int) myRowsSlider.getValue());
			myEngine.updateDIY();
		});

		myColsSlider.setOnMouseReleased(e -> {
			graph.adjustCols((int) myColsSlider.getValue());
			myEngine.updateDIY();
		});
		
		myRowsSlider.setValue(graph.getRows());
		myColsSlider.setValue(graph.getCols());
	}
}
