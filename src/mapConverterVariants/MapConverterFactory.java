package mapConverterVariants;
/**
 * @author Andrew Yeung
 * 
 * A factory for creating MapConverter objects.
 */

public class MapConverterFactory {
	/**
	 * Creates a new MapConverter object.
	 *
	 * @param shape general grid shape
	 * @param isToroidal true if the grid is a torus
	 * @param isDiagonal true if the grid considers adjacent locations
	 * @return MapConverter
	 */
	public MapConverter createMapConverter(String shape, boolean isToroidal, boolean isDiagonal) {
		switch(shape) {
			case "Hexagon": 
				return new HexagonMapConverter(isToroidal, isDiagonal);
			case "Triangle": 
				return new TriangleMapConverter(isToroidal, isDiagonal);
			case "Square": 
				return new SquareMapConverter(isToroidal, isDiagonal);
			default: 
				throw new IllegalArgumentException("Invalid MapConverter class creation");
		}
	}
}
