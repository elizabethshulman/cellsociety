package cellVariants;

/**
 * @author benhubsch
 * 
 * A factory for creating Cell objects.
 */
public class CellFactory {
	
	/**
	 * Creates a new Cell object.
	 *
	 * @param type
	 * @return Cell
	 */
	public Cell createCell(String type) {
		switch(type) {
		case "GameOfLife": 
			return new GameOfLifeCell(0);
		case "Fire": 
			return new FireCell(1);
		case "PredatorPrey": 
			return new PredatorPreyCell(0);
		case "Segregation":
			return new SegregationCell(0);
		case "RockPaperScissors":
			return new RockPaperScissorsCell(0, 5); 
		case "Foraging":
			return new ForagingCell(0);
		default: 
			throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
