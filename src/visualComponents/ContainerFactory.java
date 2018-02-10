package visualComponents;

public class ContainerFactory {
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
