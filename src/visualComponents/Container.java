package visualComponents;

import java.math.BigDecimal;

import graphVariants.Graph;
import javafx.scene.layout.VBox;

public abstract class Container {
	public static final BigDecimal GRID_SIZE = new BigDecimal(400);
	
	public abstract VBox getContainer();
	
	public abstract void setGraphDisplay(Graph g);
}
