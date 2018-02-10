package graphVariants;

import cellsociety_team10.FileProcessor;
import rulesVariants.RulesFactory;

public class GraphFactory {
	RulesFactory myRulesFactory;
	public GraphFactory(RulesFactory rules_factory) {
		myRulesFactory = rules_factory;
	}
	
	public Graph createGraph(FileProcessor file_processor) {
		String type = file_processor.getCellShape();
		switch(type) {
			case "Square": 
				return new SquareGraph(file_processor, myRulesFactory);
			case "Hexagon": 
				return new HexagonGraph(file_processor, myRulesFactory);
			case "Triangle": 
				return new TriangleGraph(file_processor, myRulesFactory);
			default: 
				throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
