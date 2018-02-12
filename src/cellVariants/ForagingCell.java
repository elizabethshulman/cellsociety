package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * 
 * @author elizabethshulman
 *
 * This class contains the values relevant to a ForagingCell within the Foraging simulation.
 * These cells are the base cells of the Foraging simulation graph; ants are superimposed on top of them.
 * Their states are as follows:
 * 	0: neutral		4: neutral cell containing ants
 *  1: nest			5: nest cell containing ants
 *  2: food source	6: food source containing ants
 *  3: obstacle
 */
public class ForagingCell extends Cell {
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color DIRT = Color.rgb(234, 210, 173);
	private static Color FOOD = Color.rgb(100, 114, 28);
	private static Color FIREANT = Color.rgb(130, 18, 10);
	private static Color DARKFOOD = Color.rgb(24, 79, 31);
	private static Color NEST = Color.rgb(164, 66, 0);
	private static Color DARKNEST = Color.rgb(105, 42, 0);

	private List<AntCell> antsHere;
	private double homePheromoneLevel = 0;				//max levels specified in AntManager
	private double foodPheromoneLevel = 0;				
	private double MAX_PHEROMONE_LEVEL = 1000;
	
	/**
	 * This constructor extends the Cell constructor, while also initializing
	 * a list to store references to the ants currently occupying this cell.
	 * @param st		current state of the cell
	 */
	public ForagingCell(int st) {
		super(st);
		antsHere = new ArrayList<>();
		if(4 <= st) {
			AntCell a = new AntCell(this);
		}
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, DIRT);
		myStatesAndColors.put(1, NEST);
		myStatesAndColors.put(2, FOOD);
		myStatesAndColors.put(3, BLACK);
		myStatesAndColors.put(4, FIREANT);
		myStatesAndColors.put(5, DARKNEST);
		myStatesAndColors.put(6, DARKFOOD);
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
	/**
	 * This methood sets the state of a cell according to whether or not it contains ants,
	 * is a nest, is an obstacle, or is a food source.
	 */
	public void setState(int st) {
		if(myState < 3 && 4<=st) {
			AntCell a = new AntCell(this);
		}
		else if(myState >= 4 && st <= 3) {
			antsHere = new ArrayList<>();
		}
		super.setState(st);
	}
	
	/**
	 * This method uses the default Cell method setState
	 * @param st		state to which cell should change
	 */
	public void originalSetState(int st) {
		super.setState(st);
	}
	
	/**
	 * This method increases the food pheromone levels by increment diff
	 * @param diff	amount by which to increase pheromone levels
	 */
	public void increaseFoodPheromones(double diff) {
		foodPheromoneLevel+=diff;
		if(foodPheromoneLevel>MAX_PHEROMONE_LEVEL) {
			maxFoodPheromones();
		}
	}

	/**
	 * This method max'es out the food pheromone levels
	 */
	public void maxFoodPheromones() {
		this.foodPheromoneLevel = MAX_PHEROMONE_LEVEL;
	}
	
	/**
	 * This method increases the home pheromone levels by increment diff
	 * @param diff	amount by which to increase pheromone levels
	 */
	public void increaseHomePheromones(double diff) {
		homePheromoneLevel+=diff;
		if(homePheromoneLevel>MAX_PHEROMONE_LEVEL) {
			maxHomePheromones();
		}
	}
	
	/**
	 * This method max'es out the home pheromone levels
	 */
	public void maxHomePheromones() {
		this.homePheromoneLevel = MAX_PHEROMONE_LEVEL;
	}
	
	
	//STATE CHECKS
	/**
	 * @return boolean indicating whether cell is a nest
	 */
	public boolean isNest() {
		return (myState==1) || (myState==5);
	}

	/**
	 * @return boolean indicating whether cell is a food source
	 */
	public boolean isFoodSource() {
		return (myState==2) || (myState==6);
	}
	
	/**
	 * @return boolean indicating whether cell is a nest
	 */
	public boolean isObstacle() {
		return (myState==3) || (myState==7);
	}
}
