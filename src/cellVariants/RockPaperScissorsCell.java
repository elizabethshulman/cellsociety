package cellVariants;

import java.util.Random;

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
