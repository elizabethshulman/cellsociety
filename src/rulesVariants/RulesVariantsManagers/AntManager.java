package rulesVariants.RulesVariantsManagers;

import java.util.List;
import java.util.Map;

import cellVariants.AntCell;
import cellVariants.ForagingCell;


public class AntManager {

	private static final double maxPheromoneLevel = 50;

	
	
	public AntManager() {
		
	}

	
	
	public void returnToNest(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {					//duplicate code here
		ForagingCell nextStop = nextStepHome(c, tempEnvironment);
		dropFoodPheromones(c);
		c.move(nextStop);
	}

	public void findFoodSource(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepFood(c, tempEnvironment);
		dropHomePheromones(c);																					//potential null pointer?
		c.move(nextStop);
	}

	
	
	//COMPLETE
	private ForagingCell nextStepHome(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {

		return null;
	}
	
	//COMPLETE
	private ForagingCell nextStepFood(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {

		return null;
	}
	
	
	
	
	private void dropFoodPheromones(AntCell c) {
		if(c.getHome().isFoodSource()) {
			c.getHome().setFoodPheromoneLevels(maxPheromoneLevel);
		} else {
			/*
			 * double MAX = max food pheromones of neighbor locations
			 * DES = MAX-2
			 * D = DES-food pheromones at current cell
			 * if(D>0) { 
			 * 	currentCell.setFoodPheromoneLevels(currentCell.getFoodPheromoneLevels + D) 			//create method to avoid topping too high
			 */
		}
	}
	
	
	private void dropHomePheromones(AntCell c) {
		if(c.getHome().isNest()) {
			c.getHome().setHomePheromoneLevels(maxPheromoneLevel);
		} else {
			/*
			 * double MAX = max home pheromones of neighbor locations
			 * DES = MAX-2
			 * D = DES-home pheromones at current cell
			 * if(D>0) {
			 * 	currentCell.setHomePheromoneLevels(currentCell.getHomePheromoneLevels + D) 			//create method to avoid topping too high
			 */
		}
	}
}
