package cellVariants;

public class CellFactory {
	public Cell createCell(String type, String shape) {
		switch(type) {
		case "GameOfLife": 
			return new GameOfLifeCell(0, shape);
		case "Fire": 
			return new FireCell(1, shape);
		case "PredatorPrey": 
			return new PredatorPreyCell(0, shape);
		case "RockPaperScissors":
			return new RockPaperScissorsCell(0, 5, shape); 
		case "Segregation":
			return new SegregationCell(0, shape);
		default: 
			throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
