package cellVariants;

public class FireCell extends Cell {
//	hashmap of states to rgb values 
	public FireCell(int st) {
		super(st);
	}
	
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage("black.png"));
		statesAndImages.put(1, buildCellImage("forestgreen.png"));
		statesAndImages.put(2, buildCellImage("burntorange.png"));
		
		statesAndColors.put(2, "rgb(0,0,0)");
		statesAndColors.put(0, "rgb(0, 70, 25)");
		statesAndColors.put(1, "rgb(165, 66, 2)");
	}
}
