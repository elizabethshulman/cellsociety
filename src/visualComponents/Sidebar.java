package visualComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class Sidebar {
	public static final double WIDTH = 200;

	private VBox myVBox = new VBox();

	public Sidebar() {
		myVBox.setId("sidebar");
		myVBox.getChildren().add(buildComboBox());
	}

	public VBox getVBox() {
		return myVBox;
	}

	public ComboBox<String> buildComboBox() {
		ObservableList<String> options = FXCollections.observableArrayList(
						"Game of Life",
						"Spreading of Fire",
						"Predator-Prey",
						"Segregation"
						);
		ComboBox<String> combo = new ComboBox<String>(options);
		
		 combo.setValue("Simulation type:");
		
		return combo;
	}
}
