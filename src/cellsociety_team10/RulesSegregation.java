package cellsociety_team10;

import java.util.ArrayList;

public class RulesSegregation extends Rules{

	private FileProcessor segregationFP;
	
	public RulesSegregation(FileProcessor fp) {
		segregationFP = fp;
	}
	
	@Override
	public Graph applyGraphRules(Graph g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean dissatisfied(int state, ArrayList<Cell> neighbors) {
		int similarCount=0;
		for(Cell c:neighbors) if(c.getState()==state) similarCount+=1;
		if(similarCount/neighbors.size()
				<segregationFP.getSatisfactionThreshold()) return true;
		return false;
	}

	@Override
	public void act(Cell c) {
		// TODO Auto-generated method stub
		
	}

}
