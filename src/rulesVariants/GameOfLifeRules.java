package rulesVariants;

import java.util.List;
import java.util.Map;

import cellVariants.Cell;

public class GameOfLifeRules extends Rules{
	
	@Override
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
	protected void act(Cell c) {
		if(c.getState()==0) {
			c.setState(1);
		} else {
			c.setState(0);
		}
	}
	
	@Override
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