package cellVariants;

public class AntCell {

	private ForagingCell home;
	private ForagingCell previoushome = null;
	private boolean holdingFood = false;
	private boolean movedThisTurn = false;
	
	public AntCell(ForagingCell homeCell) {	
		home = homeCell;
		home.getAntsHere().add(this);
	}

	public void move(ForagingCell nextStop) {
		previoushome = home;
		home = nextStop;
		movedThisTurn = true;
		
		if(home.isNest()) {
			holdingFood = false;						
		} else if(home.isFoodSource()) {
			holdingFood = true;						
		}
	}
	
	public ForagingCell getHome() {
		return home;
	}

	public ForagingCell getPreviousHome() {
		return previoushome;
	}
	
	public boolean hasFoodItem() {
		return holdingFood;
	}
	
	public void resetMovedThisTurn() {
		movedThisTurn=false;
	}
	
	public boolean hasMovedThisTurn() {
		return movedThisTurn;
	}
}
