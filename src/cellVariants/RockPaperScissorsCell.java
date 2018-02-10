package cellVariants;

import java.util.Random;

public class RockPaperScissorsCell extends Cell{

	private int health;
	
	public RockPaperScissorsCell(int st, String shape) {
		super(st, shape);
		health = new Random().nextInt(10);
	}

	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage(getShapeType() + "/black.png"));
		statesAndImages.put(1, buildCellImage(getShapeType() + "/sharkred.png"));
		statesAndImages.put(2, buildCellImage(getShapeType() + "/forestgreen.png"));
		statesAndImages.put(3, buildCellImage(getShapeType() + "/lightteal.png"));
		
		///ADD STATESANDCOLORS FOR GRAPH
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
