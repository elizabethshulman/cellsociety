package cellVariants;

import java.util.ArrayList;
import java.util.List;

public class ForagingCell extends Cell {

	/*
	 * FORAGING CELL STATES:
	 * 0: neutral
	 * 1: nest
	 * 2: food source
	 * 3: obstacle
	 */
	
	private boolean nest = false;
	private boolean foodsource = false;
	private boolean obstacle = false;
	private List<AntCell> antsHere;
	private double homePheromoneLevels = 0;				//max levels specified in AntManager
	private double foodPheromoneLevels = 0;				
	
	public ForagingCell(int st, String shape) {
		super(st, shape);
		mapCellState();									//when to set state=nest or obstacle -> ask andrew?
		antsHere = new ArrayList<AntCell>();
	}

	private void mapCellState() {
		if(state==1) {
			nest = true;
		} else if(state==2) {
			foodsource = true;
		} else if(state==3) {
			obstacle = true;
		}
	}

	@Override
	protected void buildHashMap() {
		// TODO Auto-generated method stub
		
	}

	
	
	//GETTERS
	public List<AntCell> getAntsHere() {
		return antsHere;
	}
	
	public double getHomePheromones() {
		return homePheromoneLevels;
	}

	public void setHomePheromones(double homePheromoneLevels) {
		this.homePheromoneLevels = homePheromoneLevels;
	}
	
	public double getFoodPheromones() {
		return foodPheromoneLevels;
	}

	public void setFoodPheromones(double foodPheromoneLevels) {
		this.foodPheromoneLevels = foodPheromoneLevels;
	}

	
	
	//STATE CHECKS
	public boolean isNest() {
		return nest;
	}

	public boolean isFoodSource() {
		return foodsource;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}

	
}
