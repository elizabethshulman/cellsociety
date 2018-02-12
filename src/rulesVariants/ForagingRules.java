package rulesVariants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellVariants.AntCell;
import cellVariants.Cell;
import cellVariants.ForagingCell;
import rulesVariants.RulesVariantsManagers.AntManager;

/**
 * 
 * @author elizabethshulman
 *
 * This class manages the logic of the Foraging Ants simulation. Its primary purpose
 * is to take in a graph, apply the Foraging Ants rules, and return an updated graph.
 */
public class ForagingRules extends Rules {

	private Map<ForagingCell, List<ForagingCell>> tempEnvironment;
	private AntManager manager;

	public ForagingRules(Map<String, Double> specificationsMap) {
		manager = new AntManager();
	}

	
	/**
	 * This method applies the rules of the Foraging simulation to the graph. This
	 * entails directing ants on the field toward the nest or food source, and 
	 * leaving pheromones along the way for direction.
	 * 
	 * @param g		This is the simulation's current graph.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		tempEnvironment = new HashMap(g);
		ArrayList<AntCell> movingHomes = new ArrayList<AntCell>();
		for(ForagingCell c:tempEnvironment.keySet()) {
			List<AntCell> currentants = c.getAntsHere();
			for(AntCell ant:currentants) {
				antForage(ant);
				if(ant.hasMovedThisTurn()) {
					movingHomes.add(ant);
					ant.resetMovedThisTurn();
				}
			}
		}
		updateAntHomes(movingHomes);
		updateEnvironmentStates(tempEnvironment);
		Map<Cell, List<Cell>> returnGraph = new HashMap(tempEnvironment);
		return returnGraph;
	}
	
	/**
	 * This method is responsible for directing the ant.
	 * 
	 * @param ant	This is the ant moving.
	 */
	private void antForage(AntCell ant) {
		if(ant.hasFoodItem()) {
			manager.returnToNest(ant, tempEnvironment);
		} else{
			manager.findFoodSource(ant, tempEnvironment);
		}
	}

	
	/**
	 * This method is responsible for updating the state each ForagingCell in the simulation
	 * according to whether or not it holds ants.
	 * 
	 * @param tempEnvironment	This is the current graph, cast with ForagingCells.
	 */
	private void updateEnvironmentStates(Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		for(ForagingCell c:tempEnvironment.keySet()) {
			if(c.getState()<3 && c.getAntsHere().size()>0) {
				c.originalSetState(c.getState()+4);
			} else if (c.getState()>3 && c.getAntsHere().isEmpty()) {
				c.originalSetState(c.getState()-4);
			}
		}
	}

	/**
	 * This method is responsible for adding and removing ants from their home cells.
	 * 
	 * @param movingHomes This is a list of the ants that have changed location this step.
	 */
	private void updateAntHomes(List<AntCell> movingHomes) {
		for(AntCell ant:movingHomes) {
			ant.getPreviousHome().getAntsHere().remove(ant);
			ant.getHome().getAntsHere().add(ant);
		}
	}
}