package cellVariants;

import cellsociety_team10.Helper;

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
	
	@Override
	public void setState(int st) {
		state=st;
		if(st==0) {
			reproductiveTime=0;
			reproduce=false;
			sharkEnergy=0;
		}
	}
	
	@Override
	protected void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("lightblue.png"));
		statesAndColors.put(1, Helper.generateImageView("midblue.png"));
		statesAndColors.put(2, Helper.generateImageView("burgundy.png"));
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
