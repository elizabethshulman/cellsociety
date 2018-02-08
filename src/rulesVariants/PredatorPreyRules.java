package rulesVariants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;
import rulesVariants.PredatorPreyHelper.PredatorManager;
import rulesVariants.PredatorPreyHelper.PreyManager;

public class PredatorPreyRules extends Rules {

	Double fishReproductionAge;
	Double sharkReproductionAge;
	Double sharkStarveTime;
	PredatorManager sharkManager;
	PreyManager fishManager;
	HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> tempOcean;
	
	public PredatorPreyRules(Map<String, Double> map) {
		fishReproductionAge = map.get("fishBreedTime");
		sharkReproductionAge = map.get("sharkBreedTime");
		sharkStarveTime = map.get("sharkStarveTime");
	}
	
	@Override
	public Map<Cell, ArrayList<Cell>> applyGraphRules(Map<Cell, ArrayList<Cell>> g) {
		tempOcean = new HashMap(g);
		sharkManager = new PredatorManager(tempOcean, sharkStarveTime);
		fishManager = new PreyManager(tempOcean);
		initialCellMovement();
		sharkManager.manageSharks();
		resetMovementAndReproduction();
		Map<Cell, ArrayList<Cell>> returnGraph = new HashMap(tempOcean);
		updateDeath(returnGraph);
		return returnGraph;
	}

	private void initialCellMovement() {
		for(PredatorPreyCell c:tempOcean.keySet()) {
			if(!c.hasMovedThisTurn()) {
				if(c.getState()==1) {
					fishManager.moveFish(c);
				} else if(c.getState()==2) {
					sharkManager.moveSharks(c, tempOcean.get(c));
				}
			} 
		}
	}
	
	private void resetMovementAndReproduction() {
		for(PredatorPreyCell c: tempOcean.keySet()) {
			c.setMovedThisTurn(false);
			if((c.getState()==1 && (c.getReproductiveTime()>=fishReproductionAge)) ||
					(c.getState()==2 && (c.getReproductiveTime()>=sharkReproductionAge))){
				c.setReproduce(true);
			} else {
				c.setReproduce(false);
			}
		}
	}
		
	@Override 
	protected void updateDeath(Map<Cell, ArrayList<Cell>> ocean) {
		Set<Cell> cellSet = ocean.keySet();
		Cell[] cellArray = cellSet.toArray(new Cell[0]);
		int sampleState = cellArray[0].getState();
		dead=true;
		for(Cell c:cellArray) {
			if(c.getState()!=sampleState) {
				dead=false;
				return;
			}
		}
	}	
}