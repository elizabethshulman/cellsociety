package cellVariants;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage("darkblue.png"));
		statesAndImages.put(1, buildCellImage("midblue.png"));
	}
}
