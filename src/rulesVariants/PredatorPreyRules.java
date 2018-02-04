package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;

public class PredatorPreyRules extends Rules{
	//revise to see if there are ways to simplify movement/creation of arrayLists/iterating through graph
		//runtime versus method organization?
	
	Double fishReproductionAge;
	Double sharkReproductionAge;
	Double sharkStarveTime;
	
	public PredatorPreyRules(HashMap<String,Double> globalVars) {
		fishReproductionAge = globalVars.get("fishBreedTime");
		sharkReproductionAge = globalVars.get("sharkBreedTime");
		sharkStarveTime=globalVars.get("sharkStarveTime");
	}
	
	
	@Override
	public HashMap<Cell, ArrayList<Cell>> applyGraphRules(HashMap<Cell, ArrayList<Cell>> g) {
		HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> temp = new HashMap(g);
		
		initialCellMovement(temp);
		updateSharkEnergy(temp);
		indicateReproduction(temp);
		resetMovedThisTurn(temp);
		
		HashMap<Cell, ArrayList<Cell>> returnGraph = new HashMap(temp);
		updateDeath(returnGraph);
		return returnGraph;
	}

	private void initialCellMovement(HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> temp) {
		for(PredatorPreyCell c:temp.keySet()) {
			if(!c.hasMovedThisTurn()) {
				if(c.getState()==1) {
					moveFish(c, temp.get(c));
				} else if(c.getState()==2) {
					moveShark(c, temp.get(c));
				}
			}
		}
	}
	
	private void updateSharkEnergy(HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> g) {
		for(PredatorPreyCell c:g.keySet()) {
			if (c.getState()==2) {
				c.increaseSharkEnergy();
			} if(c.getSharkEnergy()>=sharkStarveTime) {
				c.setState(0);
			}
		}
	}

	private void indicateReproduction(HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> g) {
		for(PredatorPreyCell c: g.keySet()) {
			if((c.getState()==1 && (c.getReproductiveTime()>=fishReproductionAge)) ||
					(c.getState()==2 && (c.getReproductiveTime()>=sharkReproductionAge))){
				c.setReproduce(true);
			} else {
				c.setReproduce(false);
			}
		}
	}
	
	private void resetMovedThisTurn(HashMap<PredatorPreyCell, ArrayList<PredatorPreyCell>> g) {
		for(PredatorPreyCell c : g.keySet()) {
			c.setMovedThisTurn(false);
		}
	}
	
	private void handleReproduction(PredatorPreyCell c) {
		if(c.getReproduce()==true) {
			c.setReproduce(false);
			c.setSharkEnergy(0);
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
	
	@Override protected void updateDeath(HashMap<Cell, ArrayList<Cell>> g) {
		Set<Cell> cellSet = g.keySet();
		Cell[] cellArray = cellSet.toArray(new Cell[0]);
		int sampleState = cellArray[0].getState();
		dead=true;
		for(Cell c:cellArray) {
			if(c.getState()!=sampleState) {
				dead=false;
				return;
			}
		}
	}
	
	
	
	
	//FISH MOVEMENT
	private void moveFish(PredatorPreyCell c, ArrayList<PredatorPreyCell> neighbors) {

		PredatorPreyCell cellToMoveTo = whereToMoveFish(c, neighbors);
		if(c.equals(cellToMoveTo)) return;
		cellToMoveTo.setState(1);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime()+1);
		handleReproduction(c);
	}
	
	private PredatorPreyCell whereToMoveFish(PredatorPreyCell c, ArrayList<PredatorPreyCell> neighbors) {
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<PredatorPreyCell>();
		for(PredatorPreyCell n:neighbors) {
			if(n.getState()==0 && (!n.hasMovedThisTurn())) {
				emptyOptions.add(n);
			}
		}
		
		if(emptyOptions.isEmpty()) return c;
//		Collections.shuffle(emptyOptions); //randomize fish movement
		PredatorPreyCell cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
		
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
	
	
	
	
	
	
	//SHARK MOVEMENT
	
	private void moveShark(PredatorPreyCell c, ArrayList<PredatorPreyCell> neighbors) {
		
		PredatorPreyCell cellToMoveTo = whereToMoveShark(c, neighbors);
		
		if(cellToMoveTo.equals(c)) {
			c.setReproductiveTime(c.getReproductiveTime()+1); //potentially happening twice?
			return; //indicates no possible movement options
		}
		boolean movetofish=false;
		if(cellToMoveTo.getState()==1) {
			movetofish=true;
		}
		cellToMoveTo.setState(2);
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime()+1);
		if(movetofish) {
			cellToMoveTo.setSharkEnergy(c.getSharkEnergy()-2);
		} else {
			cellToMoveTo.setSharkEnergy(c.getSharkEnergy());
		}
		handleReproduction(c);
	}
	
	private PredatorPreyCell whereToMoveShark(PredatorPreyCell c, ArrayList<PredatorPreyCell> neighbors){
		ArrayList<PredatorPreyCell> emptyOptions = new ArrayList<PredatorPreyCell>();
		ArrayList<PredatorPreyCell> fishOptions = new ArrayList<PredatorPreyCell>();

		for(PredatorPreyCell n:neighbors) {

			if(n.getState()==0 && (!n.hasMovedThisTurn())) {
				emptyOptions.add(n);
			} else if(n.getState()==1 && (!n.hasMovedThisTurn())) {
				fishOptions.add(n);
			}
		}
		
		PredatorPreyCell cellToMoveTo = c;
		if(fishOptions.isEmpty()) {
			if(emptyOptions.isEmpty()) {
				return c;
			} else {
				Collections.shuffle(emptyOptions); //randomize fish movement
				cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
			}
		} 
		else {
			Collections.shuffle(fishOptions);
			cellToMoveTo = fishOptions.remove(fishOptions.size()-1);
		}
		
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
	
	
}
