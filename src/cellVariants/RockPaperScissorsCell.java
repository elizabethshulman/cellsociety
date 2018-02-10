package cellVariants;

import java.util.Random;

public class RockPaperScissorsCell extends Cell{

	private int health;
	
	public RockPaperScissorsCell(int st, String shape) {
		super(st, shape);
		if(st==0) {
			health=0;
		} else {
			health = new Random().nextInt(10);
		}
	}

	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage(getShapeType() + "black.png"));
		statesAndImages.put(1, buildCellImage(getShapeType() + "darkblue.png"));
		statesAndImages.put(2, buildCellImage(getShapeType() + "forestgreen.png"));
		statesAndImages.put(3, buildCellImage(getShapeType() + "lightteal.png"));
		
		statesAndColors.put(0, "rgb(0, 0, 0)");
		statesAndColors.put(1, "rgb(3, 35, 87)");
		statesAndColors.put(2, "rgb(0, 70, 25)");
		statesAndColors.put(3, "rgb(68, 139, 65)");
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
