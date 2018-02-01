package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;

import cellsociety_team10.Cell;
import cellsociety_team10.FileProcessor;
import cellsociety_team10.Graph;
import cellsociety_team10.Rules;

/**
 * 
 * @author elizabethshulman
 *	Assuming 0 for empty, 1 & 2 for red and blue
 *
 *	TODO: Determine satisfaction threshold for dissatisfied method
 */
public class SegregationRules extends Rules{

	private FileProcessor segregationFP;
	private final double satisfactionThreshold;
	
	public SegregationRules(FileProcessor fp) {
		//UPDATE with new getSatisfactionThreshold()
		segregationFP = fp;
		satisfactionThreshold = fp.getSatisfactionThreshold();
	}
	
	/**
	 * Update graph according to two-party rules of segregation
	 * @param g 		graph to update
	 * @return g, modified graph
	 */
	@Override
	public Graph applyGraphRules(Graph g) {
		
		ArrayList<Cell> needChange = new ArrayList<Cell>();
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		
		for(Cell c : g.getCells()) {
			if(c.getState()==0) {
				emptyCells.add(c);
			} else {
				if(dissatisfied(c.getState(), g.getNeighbors(c))) {
					needChange.add(c);
				}
			}
		}
		
		Collections.shuffle(needChange);
		Collections.shuffle(emptyCells);
		
		if(emptyCells.size()==0 || needChange.size()==0) {
			return g;
		}
		
		while(emptyCells.size()>0 && needChange.size()>0) {
			Cell currentEmpty = emptyCells.remove(emptyCells.size()-1);
			Cell cellToChange = needChange.remove(needChange.size()-1);
			
			currentEmpty.setState(cellToChange.getState());
			cellToChange.setState(0);
		}
		return g;
	}

	
	
	/**
	 * Returns true if cell needs to change position, as its neighbors are too different
	 */
	@Override
	public Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		int similarCount=0;
		for(Cell c:neighbors) if(c.getState()==state) similarCount+=1;
		if(similarCount/neighbors.size() < satisfactionThreshold) {
			return true; 
		}
		return false;
	}

	

}
