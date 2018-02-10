package cellVariants;

import java.util.Random;

public class RockPaperScissorsCell extends Cell{

	private int health;
	
	public RockPaperScissorsCell(int st) {
		super(st);
		if(st==0) {
			health=0;
		} else {
			health = new Random().nextInt(10);
		}
	}

	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, buildCellImage("black.png"));
		statesAndColors.put(1, buildCellImage("darkblue.png"));
		statesAndColors.put(2, buildCellImage("forestgreen.png"));
		statesAndColors.put(3, buildCellImage("lightteal.png"));
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
	}

	public int getHealth() {
		return health;
	}

}
