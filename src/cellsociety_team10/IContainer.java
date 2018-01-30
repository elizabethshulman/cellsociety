package cellsociety_team10;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.layout.VBox;

public interface IContainer {
	public VBox getVBox();
	
	public void setGraphDisplay(HashMap<Cell, ArrayList<Cell>> cell_map);
}
