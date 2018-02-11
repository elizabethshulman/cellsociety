package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class RockPaperScissorsCell extends Cell{
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color NAVY = Color.rgb(3, 35, 87);
	private static Color FOREST_GREEN = Color.rgb(0, 70, 25);
	private static Color GRASS_GREEN = Color.rgb(68, 139, 65);

	private int health;
	
	public RockPaperScissorsCell(int st, int gradient) {
		super(st);
		health = gradient;
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, BLACK);
		myStatesAndColors.put(1, NAVY);
		myStatesAndColors.put(2, FOREST_GREEN);
		myStatesAndColors.put(3, GRASS_GREEN);
	}

	public boolean beats(RockPaperScissorsCell neighbor) {
		 return ((myState==1 && neighbor.getState()==3) || 
				(myState==2 && neighbor.getState()==1) ||
				(myState==3 && neighbor.getState()==2) ||
				(neighbor.getState()==0 && !(myState==0)));
	}
	
	public List<RockPaperScissorsCell> getNonEmptyNeighbors(List<RockPaperScissorsCell> neighbors){
		ArrayList<RockPaperScissorsCell> toReturn = new ArrayList<RockPaperScissorsCell>();
		for(RockPaperScissorsCell c:neighbors) {
			if(c.getState()!=0) {
				toReturn.add(c);
			}
		} return toReturn;
	}
	
	public void increaseHealth() {
		if (health<=10) {
			health+=2;
		}
	}

	public void decreaseHealth() {
		health -=2;
	}

	public void setHealth(int newhealth) {
		health = newhealth;
		if(health>0) {
			health=0;
			myState=0;
		}
	}

	public int getHealth() {
		return health;
	}
}
