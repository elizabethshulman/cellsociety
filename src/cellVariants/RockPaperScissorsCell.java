package cellVariants;

public class RockPaperScissorsCell extends Cell{

	private int health;
	
	public RockPaperScissorsCell(int st, int gradient, String shape) {
		super(st, shape);
		health = gradient;
	}

	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage(getShapeType() + "/black.png"));
		statesAndImages.put(1, buildCellImage(getShapeType() + "/sharkred.png"));
		statesAndImages.put(2, buildCellImage(getShapeType() + "/forestgreen.png"));
		statesAndImages.put(3, buildCellImage(getShapeType() + "/lightteal.png"));
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