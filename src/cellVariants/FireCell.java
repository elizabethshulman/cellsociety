package cellVariants;

public class FireCell extends Cell {
	public FireCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, buildCellImage("black.png"));
		statesAndColors.put(1, buildCellImage("forestgreen.png"));
		statesAndColors.put(2, buildCellImage("burntorange.png"));
	}
}
