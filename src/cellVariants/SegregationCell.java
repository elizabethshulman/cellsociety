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
		
		statesAndColors.put(0, "rgb(195, 224, 229)");
		statesAndColors.put(1, "rgb(3, 35, 37)");
		statesAndColors.put(2, "rgb(1, 59, 57)");
	}
}
