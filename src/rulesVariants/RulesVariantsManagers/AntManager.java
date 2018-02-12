package rulesVariants.RulesVariantsManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cellVariants.AntCell;
import cellVariants.ForagingCell;


public class AntManager {

	
	public static final double TOO_MANY_ANTS = 10;
	
	public AntManager() {
	}
	
	public void returnToNest(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepHome(c, tempEnvironment.get(c.getHome()));
		if(nextStop!=null) {
			dropFoodPheromones(c, tempEnvironment.get(c.getHome()));
			c.move(nextStop);
		}
	}

	public void findFoodSource(AntCell c, Map<ForagingCell, List<ForagingCell>> tempEnvironment) {
		ForagingCell nextStop = nextStepFood(c, tempEnvironment.get(c.getHome()));
		if(nextStop!=null) {
			dropHomePheromones(c, tempEnvironment.get(c.getHome()));		
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
			if(!neighbor.isObstacle() && neighbor.getAntsHere().size()<TOO_MANY_ANTS) {
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
			} if(nextStop==null) {
				Collections.shuffle(moveOptions);
				nextStop = moveOptions.remove(0);
			}
		}
		return nextStop;
	}
	
	
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
