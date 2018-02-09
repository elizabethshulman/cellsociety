package visualComponents;
import java.util.Map;

import rulesVariants.FireRules;
import rulesVariants.GameOfLifeRules;
import rulesVariants.PredatorPreyRules;
import rulesVariants.Rules;
import rulesVariants.SegregationRules;

public class RulesFactory {
	public Rules createRules(String rule, Map<String, Double> map) {
		switch(rule) {
			case "GameOfLife": 
				return new GameOfLifeRules();
			case "Fire": 
				return new FireRules(map);
			case "Segregation": 
				return new SegregationRules(map);
			case "PredatorPrey": 
				return new PredatorPreyRules(map);
			default: 
				throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
