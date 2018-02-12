package rulesVariants;
import java.util.Map;

/**
 * @author benhubsch
 * 
 * A factory for creating Rules objects.
 */
public class RulesFactory {
	
	/**
	 * Creates a new Rules object.
	 *
	 * @param rule
	 * @param map A map that holds the various constants that a given simulation may need
	 * to run (e.g., the satisfaction threshold for the Segregation simulation).
	 * @return Rules
	 */
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
