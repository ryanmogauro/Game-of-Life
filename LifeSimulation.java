/**
 * Project: Game of Life
 * File: LifeSimulation.java
 * Author: Ryan Mogauro
 * Date: 02/18/2022
 * Course: CS231A
 */

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random; 

public class LifeSimulation {

	public static void main(String[] args) throws InterruptedException{
		// creates a Landscape object called scape with 100 rows and 100 columns
		Landscape scape = new Landscape(100,100);
		// creates a LandscapeDisplay object called display with a scale of 9
		// it also takes scape as a parameter
		LandscapeDisplay display = new LandscapeDisplay(scape, 9); 
		
		// randomly assigns to true or false based on a random number
		// in this form, there is a 75% chance that each cell is assigned dead at the start
		//however, the chance of being dead or alive can be set to any value between 0 and 100
		Random random = new Random();
		for(int i = 0; i<scape.getRows(); i++) {
			for(int x = 0; x<scape.getCols(); x++) {
				int rand = random.nextInt(100); 
				if (rand > 40) {
					scape.getCell(i, x).setAlive(true);
				} else {
					scape.getCell(i, x).setAlive(false); 
				}
			}
		}
		
		//runs the simulation of the program
		while(true) {
			scape.advance(); 
			//can call scape.advanceNewRules() to simulate with altered rules
			display.repaint(); 
			Thread.sleep(250);
		}
		
		
		// TODO Auto-generated method stub

	}

}
