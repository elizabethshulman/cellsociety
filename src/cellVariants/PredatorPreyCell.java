package cellVariants;

public class PredatorPreyCell extends Cell {

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
	public void setState(int st) {
		state=st;
		imageView.setImage(statesAndImages.get(st));
		
		if(st==0) {
			reproductiveTime=0;
			reproduce=false;
			sharkEnergy=0;
		}
	}
	
	@Override
	protected void buildHashMap() {
		statesAndImages.put(0, buildCellImage(getShapeType() + "/navy.png"));
		statesAndImages.put(1, buildCellImage(getShapeType() + "/lightteal.png"));
		statesAndImages.put(2, buildCellImage(getShapeType() + "/sharkred.png"));
		
		statesAndColors.put(0, "rgb(14, 25, 54)");
		statesAndColors.put(1, "rgb(68, 139, 165)");
		statesAndColors.put(2, "rgb(137, 0, 0)");
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