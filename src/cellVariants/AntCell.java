package cellVariants;

public class AntCell extends Cell {

	private ForagingCell home;						//when to set home cell? ask andrew about constructor
	private boolean holdingFood = false;
	
	public AntCell(int st, ForagingCell homeCell) {
		super(st);
		home = homeCell;
		home.getAntsHere().add(this);
	}

	@Override
	protected void buildHashMap() {
		// TODO Auto-generated method stub
		
	}

	public void move(ForagingCell nextStop) {
		home.getAntsHere().remove(this);
		home = nextStop;
		home.getAntsHere().add(this);
		
		if(home.isNest()) {
			holdingFood = false;						//no reason to track amount of food at home?
		} else if(home.isFoodSource()) {
			holdingFood = true;						//need a hasFoodItem()?
		}
	}
	
	public ForagingCell getHome() {
		return home;
	}

	public boolean hasFoodItem() {
		return holdingFood;
	}

}
