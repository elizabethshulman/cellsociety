package cellVariants;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int st) {
		super(st);
	}
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage("darkblue.png"));
		statesAndImages.put(1, buildCellImage("midblue.png"));
		
		statesAndColors.put(0, "rgb(3, 35, 87)");
		statesAndColors.put(1, "rgb(1, 159, 157)");
	}
}
