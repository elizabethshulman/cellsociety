package graphVariants;

import java.io.File;

import rulesVariants.RulesFactory;

public class GraphFactory {
	RulesFactory myRulesFactory;
	public GraphFactory(RulesFactory rules_factory) {
		myRulesFactory = rules_factory;
	}
	
	public Graph createGraph(File file) {
		String[] path_arr = file.getAbsolutePath().split("/");
		String folder_name = path_arr[path_arr.length - 2];
		
		// obviously not that useful for now...matters once other shapes integrated.
		// -> folder_name should become shape_type
		switch(folder_name) {
			case "life": 
				return new SquareGraph(file, myRulesFactory);
			case "fire": 
				return new SquareGraph(file, myRulesFactory);
			case "predator": 
				return new SquareGraph(file, myRulesFactory);
			case "segregation": 
				return new SquareGraph(file, myRulesFactory);
			default: 
				throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
