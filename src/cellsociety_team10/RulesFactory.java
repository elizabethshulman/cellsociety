package cellsociety_team10;
import java.util.HashMap;

import rulesVariants.FireRules;
import rulesVariants.GameOfLifeRules;
import rulesVariants.PredatorPreyRules;
import rulesVariants.Rules;
import rulesVariants.SegregationRules;

public class RulesFactory {
	public Rules createRules(String rule, HashMap<String, Double> global_vars) {
		if (rule.equals("Fire")) {
			return new FireRules(global_vars);
		} else if (rule.equals("GameOfLife")) {
			return new GameOfLifeRules(global_vars);
		} else if (rule.equals("PredatorPrey")) {
			return new PredatorPreyRules(global_vars);
		} else if (rule.equals("Segregation")) {
			return new SegregationRules(global_vars);
		} 
		
		return null;
	}
}
