package rulesVariants;

import java.util.ArrayList;

import cellVariants.Cell;

public class GameOfLifeRules extends Rules{

	@Override
	protected Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {	
		//assuming 0 for dead, 1 for alive
		int livecount = 0;
		for(Cell c:neighbors) if(c.getState()==1) livecount+=1;
		
		if(state==0) {
			if(livecount==3) return true;  //exactly 3, comes back to life
		} if(state==1) {
			if(livecount<2) return true; //fewer than 2 live neighbors dies
			if(livecount>3) return true; //more than 3 live neighbors dies
		}
		return false;
	}
	
	@Override
	public void act(Cell c) {
		if(c.getState()==0) c.setState(1);
		else c.setState(0);
	}
}