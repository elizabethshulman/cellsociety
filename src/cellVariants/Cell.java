package cellVariants;

public abstract class Cell {

	private int state;
	//move
	private int reproductiveTime;
	private int sharkEnergy;
	private boolean reproduce;
	
	
	
	
	public Cell(int st) {
		state = st;
		
		//move
		reproductiveTime=0;
		sharkEnergy=3; //determine where to set initial shark energy
		reproduce=false;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int st) {
		state = st;
	}
	
	
	
	
	
	
	
	
	//MOVE TO PREDATOR PREY CELL
	
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
}