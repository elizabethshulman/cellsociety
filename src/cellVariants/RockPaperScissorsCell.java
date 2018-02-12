package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

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
			health+=1;
		}
	}

	public void decreaseHealth() {
		health -=1;
	}

	public void setHealth(int newhealth) {
		health = newhealth;
		if(health<=0) {
			health=0;
			myState=0;
		}
	}

	public int getHealth() {
		return health;
	}
}
