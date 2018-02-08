package rulesVariants.PredatorPreyHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import cellVariants.PredatorPreyCell;

public class PredatorManager {

	Map<PredatorPreyCell, ArrayList<PredatorPreyCell>> ocean;
	double sharkStarveTime;
	
	
	public PredatorManager(Map<PredatorPreyCell, ArrayList<PredatorPreyCell>> currentgraph,
			double starveTime) {
		ocean = currentgraph;
		sharkStarveTime=starveTime;
	}
	
	public void manageSharks() {
		updateSharkEnergy();
	}
	
	private void updateSharkEnergy() {
		for(PredatorPreyCell c:ocean.keySet()) {
			if (c.getState()==2) {
				c.increaseSharkEnergy();
			} if(c.getSharkEnergy()>=sharkStarveTime) {
				c.setState(0);
			}
		}
	}
	
	public void moveSharks(PredatorPreyCell c, ArrayList<PredatorPreyCell> neighbors) {
		PredatorPreyCell cellToMoveTo = whereToMoveShark(c);
		if(cellToMoveTo.equals(c)) { //indicates no possible movement options
			c.setReproductiveTime(c.getReproductiveTime()+1);
			return; 
		}
		if(cellToMoveTo.getState()==1) {
			cellToMoveTo.setSharkEnergy(c.getSharkEnergy()-2);
		} else {
			cellToMoveTo.setSharkEnergy(c.getSharkEnergy());
		}
		cellToMoveTo.setState(2);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime()+1);
		handleReproduction(c);
	}
	
	private PredatorPreyCell whereToMoveShark(PredatorPreyCell c){
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<>();
		ArrayList<PredatorPreyCell> fishOptions = new ArrayList<>();
		for(PredatorPreyCell n:ocean.get(c)) {
			if(n.getState()==0 && (!n.hasMovedThisTurn())) {
				emptyOptions.add(n);
			} else {
				if(n.getState()==1 && (!n.hasMovedThisTurn())) {
					fishOptions.add(n);
				}
			}
		}
		PredatorPreyCell cellToMoveTo = c;
		if(fishOptions.isEmpty()) {
			if(emptyOptions.isEmpty()) {
				return c;
			} else {
				Collections.shuffle(emptyOptions); //randomize fish movement
				cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
			}
		} else {
			Collections.shuffle(fishOptions);
			cellToMoveTo = fishOptions.remove(fishOptions.size()-1);
		}
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
	
	private void handleReproduction(PredatorPreyCell c) {
		if(c.getReproduce()) {
			c.setReproduce(false);
			c.setSharkEnergy(0);
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
}