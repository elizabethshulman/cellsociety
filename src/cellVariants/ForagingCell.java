package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class ForagingCell extends Cell {
	private static Color BLACK = Color.rgb(0,0,0);

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
	private double homePheromoneLevel = 0;				//max levels specified in AntManager
	private double foodPheromoneLevel = 0;				
	private double MAX_PHEROMONE_LEVEL = 50;
	
	public ForagingCell(int st, String shape) {
		super(st, shape);
		mapCellState();									//when to set state=nest or obstacle -> ask andrew?
		antsHere = new ArrayList<AntCell>();
	}

	private void mapCellState() {
		if(myState==1) {
			nest = true;
		} else if(myState==2) {
			foodsource = true;
		} else if(myState==3) {
			obstacle = true;
		}
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, BLACK);
		myStatesAndColors.put(1, BLACK);
		myStatesAndColors.put(2, BLACK);
		myStatesAndColors.put(3, BLACK);
	}

	
	
	//GETTERS
	public List<AntCell> getAntsHere() {
		return antsHere;
	}
	
	public double getHomePheromones() {
		return homePheromoneLevel;
	}
	
	public double getFoodPheromones() {
		return foodPheromoneLevel;
	}


	
	//SETTERS
	public void increaseFoodPheromones(double diff) {
		foodPheromoneLevel+=diff;
		if(foodPheromoneLevel>MAX_PHEROMONE_LEVEL) {
			maxFoodPheromones();
		}
	}

	public void maxFoodPheromones() {
		this.foodPheromoneLevel = MAX_PHEROMONE_LEVEL;
	}
	
	public void increaseHomePheromones(double diff) {
		homePheromoneLevel+=diff;
		if(homePheromoneLevel>MAX_PHEROMONE_LEVEL) {
			maxHomePheromones();
		}
	}
	
	public void maxHomePheromones() {
		this.homePheromoneLevel = MAX_PHEROMONE_LEVEL;
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
