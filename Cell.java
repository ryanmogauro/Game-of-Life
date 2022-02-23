/**
 * Project: Game of Life
 * File: Cell.java
 * Author: Ryan Mogauro
 * Date: 02/18/2022
 * Course: CS231A
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cell {
	private boolean alive; 
	//constructor method. (By default, the Cell is dead.)
	public Cell() {
		this.alive = false; 
	}
	
	//constructor method that specifies the Cell's state. A True argument means the Cell is alive, a False argument means the Cell is dead.
	public Cell( boolean alive ) {
		this.alive = alive; 
	}
	
	//returns whether the Cell is alive.
	public boolean getAlive() {
		return this.alive; 
	}
	
	// sets the Cell state to its default value (not alive).
	public void reset() {
		this.alive = false; 
	}
			
	//sets the Cell's state.
	public void setAlive( boolean alive ) {
		this.alive = alive; 
	}
	//returns a string that indicates the alive state of the Cell as a one-character string. For example, you could use a "0" for alive and " " for dead. This will override the default toString method in the Object class.
	public String toString() {
		if(this.alive) {
			return "0"; 
		}
		return "1"; 
	}
	
	//draws a single cell object
	public void draw( Graphics g, int x, int y, int scale ) {
			if (this.getAlive()) {
				g.setColor(Color.BLUE);
				g.fillRect(x, y, scale, scale);
			}else {
				g.setColor(Color.white);
				g.fillRect(x, y, scale, scale);
			}
		}
	
	//updates whether or not the cell is alive in the next time step, given its neighbors in the current time step.
	public void updateState(ArrayList<Cell> neighbors) {
		int aliveNeighbors = 0; 
		for(int i = 0; i < neighbors.size(); i++) {
			if(neighbors.get(i).getAlive()) {
				aliveNeighbors++;
			}
		}
		if((this.alive) && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
			this.alive = true; 
		} else if(!(this.alive) && (aliveNeighbors == 3)) {
			this.alive = true;
		} else {
			this.alive = false;
		}
	}
		
	
	//tests methods to ensure they're working as intended
	public static void main(String[] args) {
		Cell test = new Cell(); 
		System.out.println("should be nothing: " + test); 
		test.setAlive(true);
		System.out.println("should be 0: " + test);
		System.out.println("should be true: " + test.getAlive());
		test.reset();
		System.out.println("should be false: " + test.getAlive());
		
	}
		
}
