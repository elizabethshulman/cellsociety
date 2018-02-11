package cellVariants;

import javafx.scene.paint.Color;

public class RockPaperScissorsCell extends Cell{
	private static Color BLACK = Color.rgb(0,0,0);
	private static Color NAVY = Color.rgb(3, 35, 87);
	private static Color FOREST_GREEN = Color.rgb(0, 70, 25);
	private static Color GRASS_GREEN = Color.rgb(68, 139, 65);

	private int health;
	
	public RockPaperScissorsCell(int st, int gradient, String shape) {
		super(st, shape);
		health = gradient;
	}

	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, BLACK);
		myStatesAndColors.put(1, NAVY);
		myStatesAndColors.put(2, FOREST_GREEN);
		myStatesAndColors.put(3, GRASS_GREEN);
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
		if(health>0) {
			health=0;
			myState=0;
		}
	}

	public int getHealth() {
		return health;
	}
}
