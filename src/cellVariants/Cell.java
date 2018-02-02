package cellVariants;

public abstract class Cell {

	private int state;
	
	public Cell(int st) {
		state = st;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int st) {
		state = st;
	}
}
