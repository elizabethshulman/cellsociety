package rulesVariants;

import java.util.List;
import java.util.Map;

import cellVariants.Cell;
/***
 * 
 * @author elizabethshulman
 *
 * This class manages the logic of the Game Of Life simulation. Its primary purpose
 * is to take in a graph; move, kill and revive cell colonies; check if the
 * simulation has ended; and return an updated graph.
 */
public class GameOfLifeRules extends Rules{
	

	@Override
	/**
	 * This method checks if a cell dies or is revived.
	 * If a cell has 3 live neighbors, it is revived. If it has fewer than two
	 * or greater than 3, it dies, as if by starvation or overpopulation respectively.
	 * 
	 * @param 	state 		This is the current cell's state.
	 * @param	neighbors	This is a list of the cells surrounding the current cell.
	 * @return	true if a cell needs to change state; false if otherwise
	 */
	protected Boolean dissatisfied(int state, List<Cell> neighbors) {	
		//assuming 0 for dead, 1 for alive
		int livecount = 0;
		for(Cell c:neighbors) {
			if(c.getState()==1) {
				livecount+=1;
			}
		}
		if(state==0) {
			return (livecount==3);  //exactly 3, comes back to life
		} else {
			return (livecount<2 || livecount>3); //fewer than 2 or greater than 3 live neighbors dies
		}
	}

	@Override
	/**
	 * This is a method that changes a cell's state.
	 * 
	 * @param c 		This is the cell changing state.
	 */
	protected void act(Cell c) {
		if(c.getState()==0) {
			c.setState(1);
		} else {
			c.setState(0);
		}
	}
	
	@Override
	/**
	 * This is a method that checks if there will be any more movement in the simulation.
	 * 
	 * @param g		This is the current simulation graph.
	 */
	protected void updateDeath(Map<Cell, List<Cell>> g) {
		dead=true;
		for(Cell c:g.keySet()) {
			if(dissatisfied(c.getState(), g.get(c))) {
				dead=false;
				return;
			}
		}
	}
}