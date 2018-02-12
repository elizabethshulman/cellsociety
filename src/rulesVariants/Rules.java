package rulesVariants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cellVariants.Cell;

/**
 * 
 * @author elizabethshulman
 *
 * This abstract class is intended to apply the rules of any given simulation. When provided
 * a grid, it takes in a grid and returns one with cells updated per values stored in a
 * simulation's corresponding FileInfoExtractor.
 */
public abstract class Rules {
	
	protected boolean dead=false;
	
	/**
	 * This method implements a simulation's rules and returns an updated graph.
	 * 
	 * @param g		This is the current graph.
	 * @return an updated version of graph g.
	 */
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		ArrayList<Cell> needChange = new ArrayList<Cell>();	
		for(Cell c : g.keySet()) {
			if(dissatisfied(c.getState(), g.get(c))) {
				needChange.add(c);
			}
		} for(Cell toAct : needChange) {
			act(toAct);
		}
		updateDeath(g);
		return g;
	}
	
	/**
	 * This method checks if a simulation has completed movement.
	 * 
	 * @param field	This is the current graph used in the simulation.
	 */
	protected void updateDeath(Map<Cell, List<Cell>> field) {
		Set<Cell> cellSet = field.keySet();
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

	/**
	 * This method determies if a cell needs to change state.
	 * 
	 * @param state		This is a cell's current state.
	 * @param neighbors	This is a list of the current cell's neighbors.
	 * @return boolean indicating whether or not a cell needs to change state.
	 */
	protected Boolean dissatisfied(int state, List<Cell> neighbors) {
		return false;
	}
	
	/**
	 * This function changes a given cell's state according to the simulation-specific rules.
	 * @param c		This is the cell changing state.
	 */
	protected void act(Cell c) {
	}

	/**
	 * This method checks if a simulation has ceased producing new movement.
	 * @return boolean indicating whether the simulation has ended.
	 */
	public boolean simulationIsDead() {
		return dead;
	}
	
	/**
	 * This method resets the boolean indicator for end-of-simulation, allowing a user to restart
	 * a simulation after it has run its course.
	 */
	public void resetDead() {
		dead = false;
	}
}