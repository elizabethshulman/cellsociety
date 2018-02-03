package rulesVariants;

import java.util.ArrayList;
import java.util.Collections;

import cellVariants.Cell;
import cellVariants.PredatorPreyCell;
import cellsociety_team10.FileProcessor;
import graphVariants.Graph;
import graphVariants.PredatorPreyGraph;

public class PredatorPreyRules extends Rules{
	//revise to see if there are ways to simplify movement/creation of arrayLists/iterating through graph
	//simplify g.getCells() call?
	
	Double ageToReproduce;
	
	public PredatorPreyRules(FileProcessor fp) {
		ageToReproduce=fp.getGlobalVars().get("reproductionAge");
	}
	
	@Override
	protected Graph applyGraphRules(Graph g) {
		
		initialCellMovement(g);
		reduceSharkEnergy(g);
		indicateReproduction(g);
		return g;
	}

	private void reduceSharkEnergy(Graph g) {
		for(Cell c:g.getCells()) {
			if (c.getState()==2) {
				c.decreaseSharkEnergy();
			} if(c.getSharkEnergy()<=0) {
				c.setState(0);
			}
		}
		
	}

	private void indicateReproduction(Graph g) {
		for(Cell c: g.getCells()) {
			if(c.getReproductiveTime()>=ageToReproduce) {
				c.setReproduce(true);
			} else {
				c.setReproduce(false);
			}
		}
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
	
	private void moveFish(Cell c, ArrayList<Cell> neighbors) {
		//Determine where to move to
		ArrayList<Cell> emptyOptions = new ArrayList<Cell>();
		for(Cell n:neighbors) {
			if(n.getState()==0) {
				emptyOptions.add(n);
			}
		}
		if(emptyOptions.isEmpty()) return;
		Collections.shuffle(emptyOptions); //randomize fish movement
		Cell cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
		
		//Update new cell values
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime());
		
		//Empty or new fish??
		if(c.getReproduce()==true) {
			c.setReproductiveTime(0);
		} else {
			c.setState(0);
		}
	}
	
	private void moveShark(Cell c, ArrayList<Cell> neighbors) {
		// Shark move to fish; else random square
		
		//Determine where to move to
		ArrayList<Cell> emptyOptions = new ArrayList<Cell>();
		ArrayList<Cell> fishOptions = new ArrayList<Cell>();
		for(Cell n:neighbors) {
			if(n.getState()==0) {
				emptyOptions.add(n);
			} else if(n.getState()==1) {
				fishOptions.add(n);
			}
		}
		Cell cellToMoveTo = c;
		if(fishOptions.isEmpty()) {
			if(emptyOptions.isEmpty()) return;
			else {
				Collections.shuffle(emptyOptions); //randomize fish movement
				cellToMoveTo = emptyOptions.remove(emptyOptions.size()-1);
			}
		} else {
			Collections.shuffle(fishOptions);
			cellToMoveTo = fishOptions.remove(fishOptions.size()-1);
		}
		
		
		//Update new cell values
		cellToMoveTo.setReproductiveTime(c.getReproductiveTime());
		cellToMoveTo.setSharkEnergy(c.getSharkEnergy());
		
		//Empty or new shark??
		if(c.getReproduce()==true) {
			c.setReproductiveTime(0);
			c.setSharkEnergy(3);
		} else {
			c.setState(0);
		}
	}
}
