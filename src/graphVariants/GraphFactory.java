package graphVariants;

import cellsociety_team10.FileProcessor;
import rulesVariants.RulesFactory;

public class GraphFactory {
	RulesFactory myRulesFactory;
	public GraphFactory(RulesFactory rules_factory) {
		myRulesFactory = rules_factory;
	}
	
	public Graph createGraph(FileProcessor file_processor) {
		String type = file_processor.getType();
		// obviously not that useful for now...matters once other shapes integrated.
		// -> folder_name should become shape_type
		switch(type) {
			case "GameOfLife": 
				return new SquareGraph(file_processor, myRulesFactory);
			case "Segregation": 
				return new SquareGraph(file_processor, myRulesFactory);
			case "PredatorPrey": 
				return new SquareGraph(file_processor, myRulesFactory);
			case "Fire": 
				return new SquareGraph(file_processor, myRulesFactory);
			default: 
				throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
