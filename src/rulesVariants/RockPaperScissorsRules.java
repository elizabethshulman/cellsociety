package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cellVariants.Cell;
import cellVariants.RockPaperScissorsCell;

public class RockPaperScissorsRules extends Rules {
	
	HashMap<RockPaperScissorsCell, List<RockPaperScissorsCell>> tempGameField;
	Random myRandom = new Random();
	
	@Override
	public Map<Cell, List<Cell>> applyGraphRules(Map<Cell, List<Cell>> g) {
		tempGameField = new HashMap(g);
		for(RockPaperScissorsCell c:tempGameField.keySet()) {
			if(c.getState()==0) {
				emptyAct(c, tempGameField.get(c));
			}
			else {
				for(RockPaperScissorsCell neighbor:tempGameField.get(c)) {
					if(neighbor.getState()==((c.getState()+1) % c.getStateCount())) {
						victoryLossAct(neighbor, c);
					} 
					else if (c.getState()==((neighbor.getState()+1) % c.getStateCount())) { //neighbor is prey
						victoryLossAct(c, neighbor);
					}
				}
			}
		}
		Map<Cell, List<Cell>> returnGraph = new HashMap(tempGameField);
		return returnGraph;
	}
	
	private void emptyAct(RockPaperScissorsCell c, List<RockPaperScissorsCell> list) {
		ArrayList<RockPaperScissorsCell> nonEmptyNeighbors = new ArrayList<RockPaperScissorsCell>();
		for(Cell neighbor : list) {
			if(neighbor.getState()!=0) {
				nonEmptyNeighbors.add(c);
			}
		}
		if(nonEmptyNeighbors.isEmpty()) return;
		Collections.shuffle(nonEmptyNeighbors);
		RockPaperScissorsCell neighborPredator = nonEmptyNeighbors.remove(nonEmptyNeighbors.size()-1);
		killCell(c, neighborPredator);
		neighborPredator.decreaseHealth();
	}
	
	
	
	private void victoryLossAct(RockPaperScissorsCell winner, RockPaperScissorsCell loser) {
		winner.increaseHealth();
		loser.decreaseHealth();
		if(loser.getHealth()<=0) {
			killCell(loser, winner);
		}
	}
	
	private void killCell(RockPaperScissorsCell nowDead, RockPaperScissorsCell winner) {
		nowDead.setState(winner.getState());
		nowDead.setHealth(myRandom.nextInt(10));
	}
}
