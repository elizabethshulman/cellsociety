package rulesVariants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellVariants.Cell;
import cellVariants.ForagingCell;

public class ForagingRules extends Rules {

	Map<ForagingCell, List<ForagingCell>> tempEnvironment;
	
	public ForagingRules(Map<String, Double> specificationsMap) {
		
	}
	
	@Override
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		tempEnvironment = new HashMap(g);
		for(ForagingCell c:tempEnvironment.keySet()) { //all cells or just ant cells?
			if(c.hasFoodItem()) {
				c.returnToNest();
			} else{
				c.findFoodSource();
			}
		}
		Map<Cell, List<Cell>> returnGraph = new HashMap(tempEnvironment);
		return returnGraph;
	}
	
	
	
}
