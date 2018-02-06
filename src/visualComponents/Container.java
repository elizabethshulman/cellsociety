package visualComponents;

import graphVariants.Graph;
import javafx.scene.layout.VBox;

public abstract class Container {
	public abstract VBox getVBox();
	
	public abstract void setGraphDisplay(Graph g);
}