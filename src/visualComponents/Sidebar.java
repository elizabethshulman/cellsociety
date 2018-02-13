package visualComponents;

import java.io.File;
import java.util.ResourceBundle;

import cellsociety_team10.Engine;
import cellsociety_team10.Graph;
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

/**
 * @author benhubsch
 * 
 * The Class Sidebar handles all of the customization options in the DIY segment of 
 * the application, representing all the choices so that the user may modify a given
 * simulation dynamically. It is instantiated in Engine and passed to Visualization,
 * where it is displayed to the user on-screen in a larger window.
 */
public class Sidebar {
	private static final double MIN_SLIDER = 4;
	private static final double MAX_SLIDER = 40;
	private static final double SHAPE_SIZE = 20;
	private static final double DIR_SIZE = 40;
	private static final String DEFAULT_DIR = "data/simulations/default/";

	private VBox myVBox = new VBox();
	private String myCurrShape = "Square";
	private Boolean isToroidal = false;
	private Boolean isDiagonal = true;
	private ResourceBundle myResources;
	private ComboBox<String> mySimBox;
	private Slider myRowsSlider;
	private Slider myColsSlider;
	private Engine myEngine;

	/**
	 * Instantiates a new Sidebar.
	 *
	 * @param resources The ResourceBundle that is used for displaying text.
	 * @param engine The application's main Engine component.
	 * @param graph The graph that holds information about the current cell
	 * state. 
	 */
	public Sidebar(ResourceBundle resources, Engine engine, Graph graph) {
		myEngine = engine;
		myVBox.setId("sidebar");
		myRowsSlider = createSlider(graph.getRows());
		myColsSlider = createSlider(graph.getCols());
		
		myResources = resources;
		
		buildSimBox(resources);
		textAndComponent(myResources.getString("PickSim"), mySimBox);
		textAndComponent(myResources.getString("Rows"), myRowsSlider);
		textAndComponent(myResources.getString("Columns"), myColsSlider);
		textAndComponent(myResources.getString("Shape"), buildShapeOptions(resources));
		textAndComponent(myResources.getString("EdgeType"), buildEdgeBox(resources));
		textAndComponent(myResources.getString("NeighborType"), buildNeighborOptions(resources));

		setSliders(graph);
	}

	/**
	 * This function simply bundles text with some components, which is useful because
	 * every component comes with a text prompt.
	 *
	 * @param text The text to be displayed.
	 * @param node The node that the text accompanies.
	 */
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

	/**
	 * Gets the VBox.
	 *
	 * @return VBox
	 */
	public VBox getVBox() {
		return myVBox;
	}
	
	/**
	 * Builds the simulation dropdown box.
	 *
	 * @param resources The ResourceBundle that is used for displaying text.
	 */
	private void buildSimBox(ResourceBundle resources) {
		ObservableList<String> sim_options = FXCollections.observableArrayList(
				resources.getString("LifeButton"),
				resources.getString("FireButton"),
				resources.getString("PredButton"),
				resources.getString("SegButton"),
				resources.getString("AntButton"),
				resources.getString("RPSButton"));
		mySimBox = new ComboBox<String>(sim_options);
		mySimBox.setId("combo");
		mySimBox.getSelectionModel().selectFirst();
		mySimBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				String filename = DEFAULT_DIR + new_val + ".xml";
				myEngine.loadSimulation(new File(filename));
				myEngine.updateSettings(myCurrShape, isDiagonal, isToroidal);
			}
		});
	}


	/**
	 * Builds the edge box.
	 *
	 * @param resources The ResourceBundle that is used for displaying text.
	 * @return ComboBox<String> The edge selection box.
	 */
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
				isToroidal = new_val.equals("Toroidal");
				myEngine.updateSettings(myCurrShape, isToroidal, isDiagonal);
			}
		});

		return combo;
	}
	

	/**
	 * Builds the shape options.
	 *
	 * @param resources The ResourceBundle that is used for displaying text.
	 * @return HBox The container for the shape toggle box.
	 */
	private HBox buildShapeOptions(ResourceBundle resources) {
		HBox hbox = new HBox();
		hbox.setId("shape-hbox");
		RadioButton rb1 = createRadioButton("sidebar_square.png", "Square", SHAPE_SIZE);
		rb1.setId("radio");
		rb1.setSelected(true);
		RadioButton rb2 = createRadioButton("sidebar_triangle.png", "Triangle", SHAPE_SIZE);
		rb2.setId("radio");
		RadioButton rb3 = createRadioButton("sidebar_hex.png", "Hexagon", SHAPE_SIZE);
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
				
				myEngine.updateSettings(myCurrShape, isDiagonal, isToroidal);
			}
		});

		hbox.getChildren().addAll(rb1, rb2, rb3);
		return hbox;
	}
	
	/**
	 * Builds the options for the edge toggle box.
	 *
	 * @param resources The ResourceBundle that is used for displaying text.
	 * @return HBox The container for the neighbor toggle group.
	 */
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
				
				isDiagonal = neighbor_type.equals("diag");
				
				myEngine.updateSettings(myCurrShape, isToroidal, isDiagonal);
			}
		});

		hbox.getChildren().addAll(rb1, rb2);
		return hbox;
	}

	/**
	 * Creates the radio button.
	 *
	 * @param filename The filename of the .png file
	 * @param keyword The keyword used to identify the object later 
	 * @param size Desired size of the grahic
	 * @return RadioButton The RadioButton object
	 */
	private RadioButton createRadioButton(String filename, String keyword, double size) {
		RadioButton temp = new RadioButton();
		temp.setGraphic(Helper.generateImageView(filename, size));
		temp.getGraphic().setUserData(keyword);
		return temp;
	}

	/**
	 * Creates the slider.
	 *
	 * @param beginning_val The value that the slider will start at.
	 * @return Slider The Slider object used for sizing. 
	 */
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
	
	/**
	 * Sets the sliders.
	 *
	 * @param graph
	 */
	public void setSliders(Graph graph) {
		myRowsSlider.setOnMouseReleased(e -> {
			graph.adjustRows((int) myRowsSlider.getValue());
			myEngine.updateSettings(myCurrShape, isToroidal, isDiagonal);
		});

		myColsSlider.setOnMouseReleased(e -> {
			graph.adjustCols((int) myColsSlider.getValue());
			myEngine.updateSettings(myCurrShape, isToroidal, isDiagonal);
		});
		
		myRowsSlider.setValue(graph.getRows());
		myColsSlider.setValue(graph.getCols());
	}
}
