package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cellVariants.Cell;

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
	protected HashMap<Cell, ArrayList<Cell>> applyGraphRules(HashMap<Cell, ArrayList<Cell>> g) {
		initialCellMovement(g);
		updateSharkEnergy(g);
		indicateReproduction(g);
		resetMovedThisTurn(g);
		return g;
	}

	private void initialCellMovement(HashMap<Cell, ArrayList<Cell>> g) {
		for(Cell c:g.keySet()) {
			
			if(c.getState()==1) {
				moveFish(c, g.get(c));
			}
			
			else if(c.getState()==2) {
				moveShark(c, g.get(c));
			}
		}
	}
	
	private void updateSharkEnergy(HashMap<Cell, ArrayList<Cell>> g) {
		for(Cell c:g.keySet()) {
			if (c.getState()==2) {
				c.increaseSharkEnergy();
			} if(c.getSharkEnergy()>=sharkStarveTime) {
				c.setState(0);
			}
		}
		
	}

	private void indicateReproduction(HashMap<Cell, ArrayList<Cell>> g) {
		for(Cell c: g.keySet()) {
			if((c.getState()==1 && (c.getReproductiveTime()>=fishReproductionAge)) ||
					(c.getState()==2 && (c.getReproductiveTime()>=sharkReproductionAge))){
				c.setReproduce(true);
			} else {
				c.setReproduce(false);
			}
		}
	}
	
	private void resetMovedThisTurn(HashMap<Cell, ArrayList<Cell>> g) {
		for(Cell c : g.keySet()) {
			c.setMovedThisTurn(false);
		}
	}
	
	
	//FISH MOVEMENT
	private void moveFish(Cell c, ArrayList<Cell> neighbors) {

		Cell cellToMoveTo = whereToMoveFish(c, neighbors);
		if(c.equals(cellToMoveTo)) return;
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime());
		handleFishReproduction(c);
	}
	
	private Cell whereToMoveFish(Cell c, ArrayList<Cell> neighbors) {
		ArrayList<Cell> emptyOptions = new ArrayList<Cell>();
		for(Cell n:neighbors) {
			if(n.getState()==0 && (!n.hasMovedThisTurn())) {
				emptyOptions.add(n);
			}
		}
		
		if(emptyOptions.isEmpty()) return c;
		Collections.shuffle(emptyOptions); //randomize fish movement
		Cell cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
		
		cellToMoveTo.setMovedThisTurn(true);
		c.setMovedThisTurn(true);
		return cellToMoveTo;
	}
	
	private void handleFishReproduction(Cell c) {
		if(c.getReproduce()==true) {
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
	
	
	//SHARK MOVEMENT
	private void moveShark(Cell c, ArrayList<Cell> neighbors) {
		
		Cell cellToMoveTo = whereToMoveShark(c, neighbors);
		
		if(cellToMoveTo.equals(c)) {
			return; //indicates no possible movement options
		}
		
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime());
		cellToMoveTo.setSharkEnergy(c.getSharkEnergy());
		
		handleSharkReproduction(c);
	}
	
	private Cell whereToMoveShark(Cell c, ArrayList<Cell> neighbors){
		ArrayList<Cell> emptyOptions = new ArrayList<Cell>();
		ArrayList<Cell> fishOptions = new ArrayList<Cell>();
		
		for(Cell n:neighbors) {
			if(n.getState()==0 && (!n.hasMovedThisTurn())) {
				emptyOptions.add(n);
			} else if(n.getState()==1 && (!n.hasMovedThisTurn())) {
				fishOptions.add(n);
			}
		}
		
		Cell cellToMoveTo = c;
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
	
	private void handleSharkReproduction(Cell c) {
		if(c.getReproduce()==true) {
			c.setReproductiveTime(0);
			c.setSharkEnergy(3);
		} else {
			c.setState(0);
		}
	}
}
