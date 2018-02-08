package rulesVariants.PredatorPreyHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cellVariants.PredatorPreyCell;

public class PreyManager extends Manager {
	
	public PreyManager(HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> currentgraph) {
		super(currentgraph);
	}
	
	public void moveFish(PredatorPreyCell c) {

		PredatorPreyCell cellToMoveTo = whereToMove(c);
		if(c.equals(cellToMoveTo)) {
			return;
		}
		cellToMoveTo.setState(1);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime() + 1);
		handleReproduction(c);
	}
	
	protected PredatorPreyCell whereToMove(PredatorPreyCell c){
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<>();
		for(PredatorPreyCell n : ocean.get(c)) {
			if(n.getState() == 0 && !n.hasMovedThisTurn()) {
				emptyOptions.add(n);
			}
		}
		if(emptyOptions.isEmpty()) {
			return c;
		}
		Collections.shuffle(emptyOptions); //randomize fish movement
		PredatorPreyCell cellToMoveTo = emptyOptions.remove(emptyOptions.size() - 1);
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
}