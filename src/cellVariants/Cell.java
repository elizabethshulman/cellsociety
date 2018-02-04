package cellVariants;

import java.util.HashMap;

import cellsociety_team10.Helper;
import javafx.scene.image.ImageView;

public abstract class Cell {

	private int state;
	private HashMap<Integer, ImageView> statesAndColors;
	//move
	private int reproductiveTime=0;
	private int sharkEnergy=3; //determine where to set initial shark energy
	private boolean reproduce=false;
	private boolean movedThisTurn=false;
	
	private int row;
	private int col;
	
	public Cell(int st) {
		state = st;
		statesAndColors = new HashMap<Integer, ImageView>();
		buildHashMap();
	}

	private void buildHashMap() {
		statesAndColors.put(0, Helper.generateImageView("lightblue.png"));
		statesAndColors.put(1, Helper.generateImageView("burgundy.png"));
		statesAndColors.put(2, Helper.generateImageView("navy.png"));
	}

	public int getRow() {
		return row;
	}

	public void setRow(int r) {
		row = r;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int c) {
		col = c;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int st) {
		state = st;
	}
	
	public ImageView getImageView() {
		return statesAndColors.get(state);
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
	

	public boolean hasMovedThisTurn() {
		return movedThisTurn;
	}

	public void setMovedThisTurn(boolean movedThisTurn) {
		this.movedThisTurn = movedThisTurn;
	}
	

}