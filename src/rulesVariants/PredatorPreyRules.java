package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;

import cellVariants.Cell;
import cellsociety_team10.FileProcessor;
import graphVariants.Graph;

public class PredatorPreyRules extends Rules{
	//revise to see if there are ways to simplify movement/creation of arrayLists/iterating through graph
		//runtime versus method organization?
	
	Double fishReproductionAge;
	Double sharkReproductionAge;
	Double sharkStarveTime;
	
	public PredatorPreyRules(FileProcessor fp) {
		fishReproductionAge=fp.getGlobalVars().get("fishBreedTime");
		sharkReproductionAge=fp.getGlobalVars().get("sharkBreedTime");
		sharkStarveTime=fp.getGlobalVars().get("sharkStarveTime");
	}
	
	
	//UPDATE GRAPH
	@Override
	protected Graph applyGraphRules(Graph g) {
		initialCellMovement(g);
		updateSharkEnergy(g);
		indicateReproduction(g);
		resetMovedThisTurn(g);
		return g;
	}

	private void initialCellMovement(Graph g) {
		for(Cell c:g.getCells()) {
			//FISH
			if(c.getState()==1) {
				moveFish(c, g.getNeighbors(c));
			}
			//SHARK
			else if(c.getState()==2) {
				moveShark(c, g.getNeighbors(c));
			}
		}
	}
	
	private void updateSharkEnergy(Graph g) {
		for(Cell c:g.getCells()) {
			if (c.getState()==2) {
				c.increaseSharkEnergy();
			} if(c.getSharkEnergy()>=sharkStarveTime) {
				c.setState(0);
			}
		}
		
	}

	private void indicateReproduction(Graph g) {
		for(Cell c: g.getCells()) {
			if((c.getState()==1 && (c.getReproductiveTime()>=fishReproductionAge)) ||
					(c.getState()==2 && (c.getReproductiveTime()>=sharkReproductionAge))){
				c.setReproduce(true);
			} else {
				c.setReproduce(false);
			}
		}
	}
	
	private void resetMovedThisTurn(Graph g) {
		for(Cell c : g.getCells()) {
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
