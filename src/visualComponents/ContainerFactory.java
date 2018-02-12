package visualComponents;

/**
 * A factory for creating Container objects.
 */
public class ContainerFactory {
	
	/**
	 * Creates the Container object.
	 *
	 * @param shape
	 * @return Container
	 */
	public Container createContainer(String shape) {
		switch(shape) {
			case "Triangle": 
				return new TriangleContainer();
			case "Square": 
				return new SquareContainer();
			case "Hexagon": 
				return new HexContainer();
			default: 
				throw new IllegalArgumentException("Invalid rules class creation");
		}
	}

}
