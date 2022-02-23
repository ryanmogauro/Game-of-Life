/**
 * Project: Game of Life
 * File: Landscape.java
 * Author: Ryan Mogauro
 * Date: 02/18/2022
 * Course: CS231A
 */
import java.awt.Graphics;
import java.util.ArrayList; 
public class Landscape {
	private int rows; 
	private int cols; 
	private Cell[][] grid;  
	
	//sets the number of rows and columns to the specified values and allocates the grid of Cell references. Then it should allocate a Cell for each location in the Grid.
	public Landscape( int rows, int cols ) {
		this.rows = rows; 
		this.cols = cols; 
		this.grid = new Cell[this.rows][this.cols];
		for(int r = 0; r < this.rows; r++) {
			for(int c = 0; c < this.cols; c++) {
				this.grid[r][c] = new Cell(); 
			}
		}
	}
	
	// calls the reset method for each cell.
	public void reset() {
		for(int r = 0; r < this.rows; r++) {
			for(int c = 0; c < this.cols; c++) {
				grid[r][c] = new Cell(); 
			}
		}
	}
	
	//returns the number of rows in the Landscape.
	public int getRows() {
		return this.rows; 
	}
	
	//returns the number of columns in the Landscape.
	public int getCols() {
		return this.cols; 
	}
	
	//returns a reference to the Cell located at position (row, col).
	public Cell getCell(int row, int col) {
		return grid[row][col]; 
	}
	
	//converts the Landscape into a text-based string representation. At the end of each row, put a carriage return ("\n").
	public String toString() {
		String toString = ""; 
		for(int r = 0; r < this.rows; r++) {
			for(int c = 0; c < this.cols; c++) {
				toString+=grid[r][c]; 
			}
			toString+="\n";
		}
		return toString; 
	}
	
	//returns a list of references to the neighbors of the Cell at location (row, col). Pay attention to the boundaries of the Landscape when writing this function. The returned list should not contain the Cell at (row, col).
	public ArrayList<Cell> getNeighbors( int row, int col ) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>(); 
		for(int r = row - 1; r < row+2; r++) {
			for(int c = col - 1; c < col+2; c++) {
				if(r > -1 && r < this.getRows() && c > -1 && c < this.getCols()) {
					if(r != row || c != col) {
						neighbors.add(this.grid[r][c]); 
					}
				}
			} 
		}
		return neighbors;
	}
	
	//visualizes the grid by calling draw methods on each cell in the grid
	public void draw( Graphics g, int gridScale ) {
		for(int r = 0; r < this.rows; r++) {
			for(int c = 0; c < this.cols; c++) {
				this.grid[r][c].draw(g, c*gridScale, r*gridScale, gridScale);
			}
		}
	}
	
	//advances the game based on game rules
	//used a temporary board to store the new cell states while traversing the grid
	public void advance() {
		Cell [][] holder = new Cell[this.rows][this.cols];
		for(int r = 0; r< this.rows; r++) {
			for(int c = 0; c< this.cols; c++) {
				Cell newCell = new Cell(this.getCell(r, c).getAlive());
				holder[r][c] = newCell;  
			}
		}
		
		for(int r = 0; r<grid.length; r++) {
			for(int c = 0; c<grid[0].length; c++) {
				holder[r][c].updateState(this.getNeighbors(r,c));
			}
		}
		
		this.grid = holder; 
	}

	//tests methods to ensure they're working as intended
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Landscape newLandscape = new Landscape(3,3);  
		System.out.println(newLandscape.toString()); 
		System.out.println(newLandscape.getRows());
		System.out.println(newLandscape.getCols());
		System.out.println(newLandscape.getCell(0,0));
		System.out.println("neighbors: " + newLandscape.getNeighbors(0,0));
		newLandscape.advance();
		System.out.println(newLandscape);
		newLandscape.getCell(0, 0).setAlive(true);
		newLandscape.getCell(1, 0).setAlive(true);
		newLandscape.getCell(2, 0).setAlive(true);
		System.out.println(newLandscape); 
		newLandscape.reset();
		System.out.println(newLandscape); 
	}

}
