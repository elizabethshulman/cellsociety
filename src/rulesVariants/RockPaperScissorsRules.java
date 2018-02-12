package rulesVariants;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cellVariants.Cell;
import cellVariants.RockPaperScissorsCell;

/**
 * 
 * @author elizabethshulman
 *
 * This class manages the logic of the Rock Paper Scissors simulation. Its primary purpose
 * is to take in a graph, pair two cells together for combat (per the specifications of the
 * simulation), check if the simulation has run its course, and return the updated graph.
 */
public class RockPaperScissorsRules extends Rules {

	HashMap<RockPaperScissorsCell, List<RockPaperScissorsCell>> tempGameField;
	Random myRandom = new Random();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * This method applies the simulation-specific specifications to the graph. It
	 * pairs each cell with a neighbor and adjusts the cells' health valuse according
	 * to which defeats which.
	 * 
	 * @param g				This is the current simulation graph.
	 * @return returnGraph	This is the updated simulation graph.
	 */
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

	/**
	 * This method is responsible for adjusting the health values of winning and losing cells
	 * after they engage in combat. 
	 * @param winner		This is the cell with the superior state in the head-to-head match-up.
	 * @param loser		This is the cell with the inferior state.
	 */
	private void victoryLossAct(RockPaperScissorsCell winner, RockPaperScissorsCell loser) {
		winner.increaseHealth();
		loser.decreaseHealth();
		if(loser.getHealth()<=0) {
			killCell(loser, winner);
		}
	}
	
	/**
	 * This method is responsible for handling cell reproduction/spreading when a neighbor
	 * is empty or has lost its health.
	 * @param nowDead	This is the cell with no health.
	 * @param winner		This is the cell reproducing in the space occupied by nowDead.
	 */
	private void killCell(RockPaperScissorsCell nowDead, RockPaperScissorsCell winner) {
		if(winner.getState() == 0)
			return;
		nowDead.setState(winner.getState());
		nowDead.setHealth(winner.getHealth() - 1);
	}	
}
