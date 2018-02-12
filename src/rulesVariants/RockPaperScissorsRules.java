package rulesVariants;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cellVariants.Cell;
import cellVariants.RockPaperScissorsCell;

public class RockPaperScissorsRules extends Rules {

	HashMap<RockPaperScissorsCell, List<RockPaperScissorsCell>> tempGameField;
	Random myRandom = new Random();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		tempGameField = new HashMap(g);
		for(RockPaperScissorsCell c:tempGameField.keySet()) {
			Collections.shuffle(tempGameField.get(c));
			RockPaperScissorsCell neighbor = tempGameField.get(c).get(0);
			if(neighbor.getState()==0) {
				killCell(neighbor,c);
			}
			else if(c.beats(neighbor)) {
				victoryLossAct(c, neighbor);		
			}
			
		}
		Map<Cell, List<Cell>> returnGraph = new HashMap(tempGameField);
		updateDeath(returnGraph);
		return returnGraph;
	}

	private void victoryLossAct(RockPaperScissorsCell winner, RockPaperScissorsCell loser) {
		winner.increaseHealth();
		loser.decreaseHealth();
		if(loser.getHealth()<=0) {
			killCell(loser, winner);
		}
	}

	private void killCell(RockPaperScissorsCell nowDead, RockPaperScissorsCell winner) {
		if(winner.getState() == 0)
			return;
		nowDead.setState(winner.getState());
		nowDead.setHealth(winner.getHealth() - 1);
	}	
}
