package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * @author elizabethshulman
 *
 * This extension of cell contains the colors relevant to a RockPaperScissors simulation,
 * plus the values and methods inherited from Cell.
 * Its states are as follows:
 * 		0: empty cell
 * 		1: group A
 * 		2: group B
 * 		3: group C
 */
public class RockPaperScissorsCell extends Cell{
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color LAVENDER = Color.rgb(184, 179, 233);
	private static Color DUSTYROSE = Color.rgb(217, 153, 185);
	private static Color PALERED = Color.rgb(209, 123, 136);

	private int health;
	
	public RockPaperScissorsCell(int st, int gradient) {
		super(st);
		health = gradient;
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, BLACK);
		myStatesAndColors.put(1, LAVENDER);
		myStatesAndColors.put(2, DUSTYROSE);
		myStatesAndColors.put(3, PALERED);
	}

	/**
	 * This method determines which of two cells in combat defeats the other
	 * @param neighbor cell
	 * @return true if this cell defeats neighbor
	 */
	public boolean beats(RockPaperScissorsCell neighbor) {
		 return ((myState==1 && neighbor.getState()==3) || 
				(myState==2 && neighbor.getState()==1) ||
				(myState==3 && neighbor.getState()==2) ||
				(neighbor.getState()==0 && !(myState==0)));
	}
	
	/**
	 * Gathers a list of all the cells bordering this one with non-empty states
	 * @param neighbors	cells bordering this
	 * @return list of neighbors that have non-empty states
	 */
	public List<RockPaperScissorsCell> getNonEmptyNeighbors(List<RockPaperScissorsCell> neighbors){
		ArrayList<RockPaperScissorsCell> toReturn = new ArrayList<RockPaperScissorsCell>();
		for(RockPaperScissorsCell c:neighbors) {
			if(c.getState()!=0) {
				toReturn.add(c);
			}
		} return toReturn;
	}
	
	/**
	 * Add one health, up to maximum health value of 10
	 */
	public void increaseHealth() {
		if (health<=10) {
			health+=1;
		}
	}

	/**
	 * Decrease health by one
	 */
	public void decreaseHealth() {
		health -=1;
	}

	/**
	 * Reset health to given value
	 * @param newhealth 	new value to which health is set
	 */
	public void setHealth(int newhealth) {
		health = newhealth;
		if(health<=0) {
			health=0;
			myState=0;
		}
	}

	/**
	 * Gets current health value
	 * @return health value
	 */
	public int getHealth() {
		return health;
	}
}
