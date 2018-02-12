package rulesVariants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellVariants.AntCell;
import cellVariants.Cell;
import cellVariants.ForagingCell;
import rulesVariants.RulesVariantsManagers.AntManager;


//remove suppress warnings??
public class ForagingRules extends Rules {

	private Map<ForagingCell, List<ForagingCell>> tempEnvironment;
	private AntManager manager;
	
	
	
	
	public ForagingRules(Map<String, Double> specificationsMap) {
		manager = new AntManager();
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		tempEnvironment = new HashMap(g);
		for(ForagingCell c:tempEnvironment.keySet()) {
			if(c.getState()<3 && c.getAntsHere().size()>0) {
				c.setState(c.getState()+4);
			} else if (c.getState()>3 && c.getAntsHere().isEmpty()) {
				c.setState(c.getState()-4);
			}
			List<AntCell> currentants = c.getAntsHere();
			for(AntCell ant:currentants) {
				antForage(ant);
			}
		}
		Map<Cell, List<Cell>> returnGraph = new HashMap(tempEnvironment);
		return returnGraph;
	}

	
	
	private void antForage(AntCell ant) {
		if(ant.hasFoodItem()) {
			manager.returnToNest(ant, tempEnvironment);
		} else{
			manager.findFoodSource(ant, tempEnvironment);
		}		
	}
}