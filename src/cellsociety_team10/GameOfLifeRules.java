package cellsociety_team10;

import java.util.ArrayList;

public class GameOfLifeRules implements Rules{

	ArrayList<Cell> needChange;	
	
	@Override
	public Graph applyGridRules(Graph g) {
		for(Cell c : g.getCells()) {
			if(dissatisfied(c.getState(), g.getNeighbors(c))) {
				needChange.add(c);
			}
		} for(Cell toAct : needChange) {
			act(toAct);
		}
		return g;
	}
	
	@Override
	public void act(Cell c) {
		if(c.getState()==0) c.setState(1);
		else c.setState(0);
	}

	@Override
	public Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {	
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
}