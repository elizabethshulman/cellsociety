package cellVariants;

public class PredatorPreyCell extends Cell {

	private int reproductiveTime;
	private int sharkEnergy;
	private boolean reproduce;
	private boolean movedThisTurn;
	
	public PredatorPreyCell(int st) {
		/*
		 * 0: Empty
		 * 1: Fish
		 * 2: Shark
		 */
		super(st);
		reproductiveTime=0;
		sharkEnergy=0; //determine where to set initial shark energy
		reproduce=false;
	}
	
	public void increaseSharkEnergy() {
		sharkEnergy+=1;
	}
	
	public void decreaseSharkEnergy() {
		sharkEnergy-=1;
	}
	
	public void increaseReproductiveTime() {
		reproductiveTime+=1;
	}
	
	public void decreaseReproductiveTime() {
		reproductiveTime-=1;
	}
	
	public void setReproduce(boolean b) {
		reproduce = b;
	}
	
	public boolean getReproduce() {
		return reproduce;
	}
	
	public void setReproductiveTime(int time) {
		reproductiveTime = time;
	}

	public int getReproductiveTime() {
		return reproductiveTime;
	}
	
	public void setSharkEnergy(int energy) {
		sharkEnergy = energy;
	}
	
	public int getSharkEnergy() {
		return sharkEnergy;
	}

	public boolean hasMovedThisTurn() {
		return movedThisTurn;
	}

	public void setMovedThisTurn(boolean movedThisTurn) {
		this.movedThisTurn = movedThisTurn;
	}
	

}
