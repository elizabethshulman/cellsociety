package cellVariants;

/**
 * 
 * @author elizabethshulman
 *
 * This extension of cell contains the colors and values relevant to an ant in
 * the Foraging Ants simulation.
 */
public class AntCell {

	private ForagingCell home;
	private ForagingCell previoushome = null;
	private boolean holdingFood = false;
	private boolean movedThisTurn = false;
	
	public AntCell(ForagingCell homeCell) {	
		home = homeCell;
		home.getAntsHere().add(this);
	}

	/**
	 * Updates an ants current home value and indicates whether ant is holding food.
	 * @param nextStop	This ant's new current location
	 */
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
	
	/**
	 * Gets the current location of ant
	 * @return current location of ant
	 */
	public ForagingCell getHome() {
		return home;
	}

	/**
	 * Gets cell from which ant moved during the most recent step
	 * @return most recent location of ant
	 */
	public ForagingCell getPreviousHome() {
		return previoushome;
	}
	
	/**
	 * Indicates whether ant is holding food or not, a value which determines
	 * whether ant is en route to the food source or nest
	 * @return boolean indicating whether ant is holding food
	 */
	public boolean hasFoodItem() {
		return holdingFood;
	}
	
	/**
	 * At the beginning of each step, resets value indicating whether an ant has moved
	 */
	public void resetMovedThisTurn() {
		movedThisTurn=false;
	}
	
	/**
	 * Indicates whether an ant has moved during this turn
	 * @return value indicating ant movement
	 */
	public boolean hasMovedThisTurn() {
		return movedThisTurn;
	}
}
