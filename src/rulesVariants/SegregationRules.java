package rulesVariants;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import cellVariants.Cell;

/**
 * 
 * @author elizabethshulman
 * 
 * This class manages the logic for the Segregation simulation. Its primary purpose
 * is to take in a graph, account for cell self-segregation, check if the cells are
 * satisfied, then return the updated simulation graph. It assumes states 0 for empty, 
 * 1 & 2 for groups A and B.
 */
public class SegregationRules extends Rules {

	private final double satisfactionThreshold;

	public SegregationRules(Map<String, Double> map) {
		satisfactionThreshold = map.get("satisfactionThreshold");
	}
	

	@Override
	/**
	 * Update graph according to two-party rules of segregation
	 * @param g 		graph to update
	 * @return g 	modified graph
	 */
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		List<Cell> needChange = new ArrayList<>();
		List<Cell> emptyCells = new ArrayList<>();
		for(Cell c : g.keySet()) {
			if(c.getState()==0) {
				emptyCells.add(c);
			} else {
				if(dissatisfied(c.getState(), g.get(c))) {
					needChange.add(c);
				}
			}
		}
		Collections.shuffle(needChange);
		Collections.shuffle(emptyCells);
		if(emptyCells.isEmpty() || needChange.isEmpty()) {
			updateDeath(g);
			return g;
		}
		tradeCellStates(emptyCells, needChange);
		return g;
	}
	

	@Override
	/**
	 * This method checks if a cell needs to change position, as its neighbors are too different from itself.
	 * @return boolean indicating dissatisfaction
	 */
	protected Boolean dissatisfied(int state, List<Cell> neighbors) {
		if(state==0) {
			return false;
		}
		int similarCount=0;
		int notEmptyCount=0;
		for(Cell c:neighbors) {
			if(c.getState()==state) {
				similarCount+=1;
			} if(c.getState()!=0) {
				notEmptyCount+=1;
			}
		}
		if(notEmptyCount==0) {
			return false;
		}
		return ((similarCount/(notEmptyCount*1.0)) < satisfactionThreshold);
	}
	
	@Override
	/**
	 * This method checks if all cells are satisfied with their position.
	 */
	protected void updateDeath(Map<Cell, List<Cell>> g) {
		dead=true;
	}
	
	/**
	 * Move dissatisfied cell to an empty cell.
	 * 
	 * @param empty		List of empty cells
	 * @param changing	List of cells that are dissatisfied with their location
	 */
	private void tradeCellStates(List<Cell> empty, List<Cell> changing) {
		while(! empty.isEmpty() && ! changing.isEmpty()) {
			Cell currentEmpty = empty.remove(empty.size()-1);
			Cell cellToChange = changing.remove(changing.size()-1);
			currentEmpty.setState(cellToChange.getState());
			cellToChange.setState(0);
		}
	}
}