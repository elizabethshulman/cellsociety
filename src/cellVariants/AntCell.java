package cellVariants;

public class AntCell {

	private ForagingCell home;						
	private boolean holdingFood = false;
	
	public AntCell(ForagingCell homeCell) {
		home = homeCell;
		home.getAntsHere().add(this);
	}

	public void move(ForagingCell nextStop) {
		home.getAntsHere().remove(this);
		home = nextStop;
		home.getAntsHere().add(this);
		
		if(home.isNest()) {
			holdingFood = false;						
		} else if(home.isFoodSource()) {
			holdingFood = true;						
		}
	}
	
	public ForagingCell getHome() {
		return home;
	}

	public boolean hasFoodItem() {
		return holdingFood;
	}

}
