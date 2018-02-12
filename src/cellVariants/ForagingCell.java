package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class ForagingCell extends Cell {
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color DIRT = Color.rgb(234, 210, 173);
	private static Color FOOD = Color.rgb(100, 114, 28);
	private static Color FIREANT = Color.rgb(130, 18, 10);
	private static Color DARKFOOD = Color.rgb(24, 79, 31);
	private static Color NEST = Color.rgb(164, 66, 0);
	private static Color DARKNEST = Color.rgb(105, 42, 0);
	/*
	 * FORAGING CELL STATES:
	 * 0: neutral
	 * 1: nest
	 * 2: food source
	 * 3: obstacle
	 */

	private List<AntCell> antsHere;
	private double homePheromoneLevel = 0;				//max levels specified in AntManager
	private double foodPheromoneLevel = 0;				
	private double MAX_PHEROMONE_LEVEL = 1000;
	
	public ForagingCell(int st) {
		super(st);
		antsHere = new ArrayList<>();
		if(4 <= st) {
			AntCell a = new AntCell(this);
		}
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, DIRT);			//empty, no ants
		myStatesAndColors.put(1, NEST);			//nest, no ants
		myStatesAndColors.put(2, FOOD);			//foodsource, no ants
		myStatesAndColors.put(3, BLACK);			//obstacle
		myStatesAndColors.put(4, FIREANT);		//foraging cell holding ants
		myStatesAndColors.put(5, DARKNEST);		//nest with ants
		myStatesAndColors.put(6, DARKFOOD);		//foodsource with ants
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
	
	@SuppressWarnings("unused")
	@Override 
	public void setState(int st) {
		if(myState < 3 && 4<=st) {
			AntCell a = new AntCell(this);
		}
		else if(myState >= 4 && st <= 3)
			antsHere = new ArrayList<>();
		super.setState(st);
	}
	public void originalSetState(int st) {
		super.setState(st);
	}
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
		return (myState==1) || (myState==5);
	}

	public boolean isFoodSource() {
		return (myState==2) || (myState==6);
	}
	
	public boolean isObstacle() {
		return (myState==3) || (myState==7);
		
	}
}
