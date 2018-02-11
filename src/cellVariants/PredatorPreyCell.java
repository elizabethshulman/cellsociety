package cellVariants;

import javafx.scene.paint.Color;

public class PredatorPreyCell extends Cell {
	private static Color NAVY = Color.rgb(3, 35, 87);
	private static Color MIDBLUE = Color.rgb(68, 139, 165);
	private static Color MAROON = Color.rgb(128, 0, 0);

	private int reproductiveTime;
	private int sharkEnergy;
	private boolean reproduce;
	private boolean movedThisTurn;
	
	public PredatorPreyCell(int st, String shape) {
		/*
		 * 0: Empty
		 * 1: Fish
		 * 2: Shark
		 */
		super(st, shape);
		reproductiveTime=0;
		sharkEnergy=0;
		reproduce=false;
	}
	
	@Override
	public void setState(int state) {
		myState = state;
		
		if(myState == 0) {
			reproductiveTime=0;
			reproduce=false;
			sharkEnergy=0;
		}
	}
	
	@Override
	protected void buildHashMap() {
		myStatesAndColors.put(0, NAVY);
		myStatesAndColors.put(1, MIDBLUE);
		myStatesAndColors.put(2, MAROON);
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