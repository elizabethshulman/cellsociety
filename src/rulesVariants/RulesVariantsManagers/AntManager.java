package rulesVariants.RulesVariantsManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cellVariants.AntCell;
import cellVariants.ForagingCell;


public class AntManager {

	public static final double MAX_PHEROMONE_LEVEL = 50;
	public static final double TOO_MANY_ANTS = 10;
	
	public AntManager() {
	}

	
	public void returnToNest(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepHome(c, tempEnvironment.get(c.getHome()));
		if(nextStop!=null) {
			dropFoodPheromones(c);
			c.move(nextStop);
		}
	}

	public void findFoodSource(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepFood(c, tempEnvironment.get(c.getHome()));
		if(nextStop!=null) {
			dropHomePheromones(c);																					
			c.move(nextStop);
		}
	}

	
	
	private ForagingCell nextStepHome(AntCell c, List<ForagingCell> neighbors) {

		double maxNeighboringLevel = 0;
		ForagingCell nextStop=null;
		Collections.shuffle(neighbors);

		for(ForagingCell neighbor:neighbors) {
			if(neighbor.getHomePheromones()>maxNeighboringLevel) {
				nextStop=neighbor;
				maxNeighboringLevel=neighbor.getHomePheromones();
			}
		} 
		
		if(nextStop == null && neighbors!=null) {
			nextStop = neighbors.get(0);
		}
		
		return nextStop;
	}
	
	private ForagingCell nextStepFood(AntCell c, List<ForagingCell> neighbors) {
		
		ArrayList<ForagingCell> moveOptions = new ArrayList<ForagingCell>();
		for(ForagingCell neighbor:neighbors) {
			if(!neighbor.isObstacle()) {
				moveOptions.add(neighbor);
			}
		} 
		
		ForagingCell nextStop = null;
		
		if(moveOptions!=null) {
			double maxNeighboringLevel = 0;
			for(ForagingCell option:moveOptions) {
				if(option.getFoodPheromones()>maxNeighboringLevel) {
					nextStop = option;
					maxNeighboringLevel = option.getFoodPheromones();
				}
			}
			if(nextStop==null) {
				Collections.shuffle(moveOptions);
				nextStop = moveOptions.remove(0);
			}
		}
		
		return nextStop;
	}
	
	
	
	//COMPLETE
	private void dropFoodPheromones(AntCell c) {
		if(c.getHome().isFoodSource()) {
			c.getHome().setFoodPheromones(MAX_PHEROMONE_LEVEL);
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
	
	//COMPLETE
	private void dropHomePheromones(AntCell c) {
		if(c.getHome().isNest()) {
			c.getHome().setHomePheromones(MAX_PHEROMONE_LEVEL);
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
