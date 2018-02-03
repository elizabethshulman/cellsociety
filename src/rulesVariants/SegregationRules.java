package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cellVariants.Cell;
import cellsociety_team10.FileProcessor;
import graphVariants.Graph;

/**
 * 
 * @author elizabethshulman
 *	Assuming 0 for empty, 1 & 2 for red and blue
 */
public class SegregationRules extends Rules{

	private final double satisfactionThreshold;
	
	public SegregationRules(FileProcessor fp) {
		satisfactionThreshold = fp.getGlobalVars().get("satisfactionThreshold");
	}
	
	/**
	 * Update graph according to two-party rules of segregation
	 * @param g 		graph to update
	 * @return g, modified graph
	 */
	@Override
	public HashMap<Cell, ArrayList<Cell>> applyGraphRules(HashMap<Cell, ArrayList<Cell>> g) {
		
		ArrayList<Cell> needChange = new ArrayList<Cell>();
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		
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
		if(emptyCells.size()==0 || needChange.size()==0) {
			return g;
		}
		tradeCellStates(emptyCells, needChange);

		return g;
	}
	
	/**
	 * Returns true if cell needs to change position, as its neighbors are too different
	 */
	@Override
	public Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		int similarCount=0;
		for(Cell c:neighbors) {
			if(c.getState()==state) {
				similarCount+=1;
			}
		}
		return (similarCount/neighbors.size() < satisfactionThreshold);
	}

	/**
	 * Move dissatisfied cell to an empty cell
	 * @param empty		ArrayList of empty cells
	 * @param changing	ArrayList of cells that are dissatisfied with their location
	 */
	private void tradeCellStates(ArrayList<Cell> empty, ArrayList<Cell> changing) {
		while(empty.size()>0 && changing.size()>0) {
			
			Cell currentEmpty = empty.remove(empty.size()-1);
			Cell cellToChange = changing.remove(changing.size()-1);
			
			currentEmpty.setState(cellToChange.getState());
			cellToChange.setState(0);
		}
	}

}
