package cellVariants;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class RockPaperScissorsCell extends Cell{

	private int health;
	
	public RockPaperScissorsCell(int st, int gradient, String shape) {
		super(st, shape);
		health = gradient;
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, Color.rgb(0, 0, 0));
		myStatesAndColors.put(1, Color.rgb(3, 35, 87));
		myStatesAndColors.put(2, Color.rgb(0, 70, 25));
		myStatesAndColors.put(3, Color.rgb(68, 139, 65));
	}

	public boolean beats(RockPaperScissorsCell neighbor) {
		if((myState==1 && neighbor.getState()==3) || 
				(myState==2 && neighbor.getState()==1) ||
				(myState==3 && neighbor.getState()==2) ||
				(neighbor.getState()==0 && !(myState==0))) {
					return true;
		} return false;
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
