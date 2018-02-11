package rulesVariants;
import java.util.Map;

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
			case "RockPaperScissors": 
				return new RockPaperScissorsRules();
			case "Foraging": 
				return new ForagingRules(map);
			default: 
				throw new IllegalArgumentException("Invalid rules class creation");
		}
	}
}
