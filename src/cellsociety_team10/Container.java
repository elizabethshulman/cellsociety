package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.layout.VBox;

public abstract class Container {
	public abstract VBox getVBox();
	
	public abstract void setGraphDisplay(HashMap<Cell, ArrayList<Cell>> cell_map);
}
