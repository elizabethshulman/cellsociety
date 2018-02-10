package cellVariants;

public class SegregationCell extends Cell {
	public SegregationCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage("lightblue.png"));
		statesAndImages.put(1, buildCellImage("darkblue.png"));
		statesAndImages.put(2, buildCellImage("midblue.png"));
	}
}
