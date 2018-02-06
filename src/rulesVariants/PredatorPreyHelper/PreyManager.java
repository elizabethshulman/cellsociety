package rulesVariants.PredatorPreyHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cellVariants.PredatorPreyCell;

public class PreyManager {
	
	HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> ocean;
	
	public PreyManager(HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> currentgraph) {
		ocean = currentgraph;
	}
	
	public void moveFish(PredatorPreyCell c) {
		PredatorPreyCell cellToMoveTo = whereToMoveFish(c);
		if(c.equals(cellToMoveTo)) {
			return;
		}
		cellToMoveTo.setState(1);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime()+1);
		handleReproduction(c);
	}
	
	private PredatorPreyCell whereToMoveFish(PredatorPreyCell c) {
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<PredatorPreyCell>();
		for(PredatorPreyCell n:ocean.get(c)) {
			if(n.getState()==0 && !n.hasMovedThisTurn()) {
				emptyOptions.add(n);
			}
		}
		if(emptyOptions.isEmpty()) {
			return c;
		}
		Collections.shuffle(emptyOptions); //randomize fish movement
		PredatorPreyCell cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
	
	private void handleReproduction(PredatorPreyCell c) {
		if(c.getReproduce()==true) {
			c.setReproduce(false);
			c.setSharkEnergy(0);
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
	
}