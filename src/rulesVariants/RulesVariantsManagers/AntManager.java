package rulesVariants.RulesVariantsManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cellVariants.AntCell;
import cellVariants.ForagingCell;

/**
 * 
 * @author elizabethshulman
 *
 * This class contains the methods necessary for implementing the Foraging Ants simulation-specific
 * logic. It is primarily used in ForagingRules.
 */
public class AntManager {

	
	public static final double TOO_MANY_ANTS = 10;
	
	public AntManager() {
	}
	
	/**
	 * Sends an ant en route to nest by following home pheromones
	 * @param c					ant currently moving
	 * @param tempEnvironment	current simulation grid
	 */
	public void returnToNest(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepHome(c, tempEnvironment.get(c.getHome()));
		if(nextStop!=null) {
			dropFoodPheromones(c, tempEnvironment.get(c.getHome()));
			c.move(nextStop);
		}
	}

	/**
	 * Sends an ant en route to food source by following food source pheromones
	 * @param c					ant currently moving
	 * @param tempEnvironment	current simulation grid
	 */
	public void findFoodSource(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepFood(c, tempEnvironment.get(c.getHome()));
		if(nextStop!=null) {
			dropHomePheromones(c, tempEnvironment.get(c.getHome()));		
			c.move(nextStop);
		}
	}
	
	/**
	 * Determines the next cell to which an ant will move, in the direction of the home nest
	 * @param c			ant cell moving
	 * @param neighbors	cells surrounding ant c
	 * @return the next cell ant c will move to
	 */
	private ForagingCell nextStepHome(AntCell c, List<ForagingCell> neighbors) {
		double maxNeighboringLevel = 0;
		ForagingCell nextStop=null;
		Collections.shuffle(neighbors);

		for(ForagingCell neighbor:neighbors) {
			if(neighbor.getHomePheromones()>maxNeighboringLevel) {
				nextStop=neighbor;
				maxNeighboringLevel=neighbor.getHomePheromones();
			}
		} if(nextStop == null && neighbors!=null) {
			nextStop = neighbors.get(0);
		}
		return nextStop;
	}
	
	/**
	 * Determines the next cell to which an ant will move, in the direction of food
	 * @param c			ant cell moving
	 * @param neighbors	cells surrounding ant c
	 * @return the next cell ant c will move to
	 */
	private ForagingCell nextStepFood(AntCell c, List<ForagingCell> neighbors) {
		ArrayList<ForagingCell> moveOptions = new ArrayList<ForagingCell>();
		for(ForagingCell neighbor:neighbors) {
			if(!neighbor.isObstacle() && neighbor.getAntsHere().size()<TOO_MANY_ANTS) {
				moveOptions.add(neighbor);
			}
		} 
		ForagingCell nextStop = null;
		if(!moveOptions.isEmpty()) {
			double maxNeighboringLevel = 0;
			for(ForagingCell option:moveOptions) {
				if(option.getFoodPheromones()>maxNeighboringLevel) {
					nextStop = option;
					maxNeighboringLevel = option.getFoodPheromones();
				}
			} if(nextStop==null) {
				Collections.shuffle(moveOptions);
				nextStop = moveOptions.remove(0);
			}
		}
		return nextStop;
	}
	
	/**
	 * Releases pheromones leading the way to food
	 * @param c			ant cell leaving pheromones
	 * @param neighbors	cells surrounding ant cell c
	 */
	private void dropFoodPheromones(AntCell c, List<ForagingCell> neighbors) {
		ForagingCell home = c.getHome();
		if(home.isFoodSource()) {
			home.maxFoodPheromones();
		} else {
			double maxNearby = 0;
			for(ForagingCell n:neighbors) {
				if(n.getFoodPheromones()>maxNearby) {
					maxNearby = n.getFoodPheromones();
				}
			}
			double diff = maxNearby-2-home.getFoodPheromones();
			if(diff>0) {
				home.increaseFoodPheromones(diff);
			}
		}
	}
	
	/**
	 * Releases pheromones leading the way to an ant's nest
	 * @param c			ant cell leaving pheromones
	 * @param neighbors	cells surrounding ant cell c
	 */
	private void dropHomePheromones(AntCell c, List<ForagingCell> neighbors) {
		ForagingCell home = c.getHome();
		if(home.isNest()) {
			home.maxHomePheromones();
		} else {
			double maxNearby = 0;
			for(ForagingCell n:neighbors) {
				if(n.getHomePheromones()>maxNearby) {
					maxNearby = n.getHomePheromones();
				}
			}
			double diff = maxNearby-2-home.getHomePheromones();
			if(diff>0) {
				home.increaseHomePheromones(diff);
			}
		}
	}
}
