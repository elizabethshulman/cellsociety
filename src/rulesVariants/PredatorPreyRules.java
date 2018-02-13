package rulesVariants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;
import rulesVariants.RulesVariantsManagers.PredatorManager;
import rulesVariants.RulesVariantsManagers.PreyManager;

/**
 * 
 * @author elizabethshulman
 *
 * This class manages the logic of the PredatorPrey simulation, modeling relationships between
 * predators and their prey using the analogy of sharks and fish. This class's primary responsibility
 * is to update the current graph to represent the changing states of the cells.
 */
public class PredatorPreyRules extends Rules {

	Double fishReproductionAge;
	Double sharkReproductionAge;
	Double sharkStarveTime;
	PredatorManager sharkManager;
	PreyManager fishManager;
	HashMap<PredatorPreyCell, List<PredatorPreyCell>> tempOcean;
	
	public PredatorPreyRules(Map<String, Double> map) {
		fishReproductionAge = map.get("fishBreedTime");
		sharkReproductionAge = map.get("sharkBreedTime");
		sharkStarveTime = map.get("sharkStarveTime");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * This method applies the logic of the simulation. It moves sharks and fish according to their
	 * respective rules; accounts for reproduction; and checks if the simulation has ended.
	 * 
	 * @param g		This is the current graph for the simulation.
	 * @return returnGraph	This is an updated version of the graph.
	 */
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		tempOcean = new HashMap(g);
		sharkManager = new PredatorManager(tempOcean, sharkStarveTime);
		fishManager = new PreyManager(tempOcean);
		initialCellMovement();
		sharkManager.manageSharks();
		resetMovementAndReproduction();
		Map<Cell, List<Cell>> returnGraph = new HashMap(tempOcean);
		updateDeath(returnGraph);
		return returnGraph;
	}

	/**
	 * This method iterates through the cells of the graph, calling the Manager to move cells.
	 */
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
	
	
	/**
	 * This method checks if it is time for a cell to reproduce and sets movement indicators to false.
	 */
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
		
}