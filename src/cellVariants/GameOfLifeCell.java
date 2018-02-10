package cellVariants;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, buildCellImage("darkblue.png"));
		statesAndColors.put(1, buildCellImage("midblue.png"));
	}
}
