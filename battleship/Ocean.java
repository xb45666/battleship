package battleship;

import java.util.Random;

public class Ocean {

	//instance variable
	
	/**
	 * determine which ship is in any given location
	 */
	private Ship[][] ships= new Ship[10][10];
	
	
	/**
	 * the total number fired by the user
	 */
	private int shotsFired;
	
	/**
	 * times that a shot hits ships
	 */
	private int hitCount;
	
	/**
	 * the number of sunk ship
	 */
	private int shipsSunk;

	
	//Constructor
	public Ocean() {
		
		//reset the game variables
		this.shotsFired=0;
		
		this.hitCount=0;
		
		this.shipsSunk=0;
		
		
		//interate over rows from 1 to 9
		for (int i=0;i<10;i++) {
			
			//iterate over columns from 1 to 9
			
			for (int j=0;j<10;j++) {
				
				//give each location an new empty sea
				
				Ship ship = new EmptySea();
				ship.placeShipAt(i,j,true,this);
			}
		
		}
	}
	
	//method
	
	/**
	 * place all the ships randomly
	 */
	void placeAllShipsRandomly() {
		
		//a random generator
		Random rand = new Random();
		
		//parameter for the random ships
		int row=0;
		int column;
		int horizontalCount=0;
		boolean horizontal=false;
		
		//place the battleship
		for (int i=0;i<1;i++) {
			
			//generate a battleship and its position constant
			Ship battleship=new Battleship();
			row=rand.nextInt(10);
			column=rand.nextInt(10);
			horizontalCount=rand.nextInt(2);
			
			//we can only generate 0 or 1 and then we cast them into true or false
			if (horizontalCount==0) {
				horizontal=true;
			}else if(horizontalCount==1) {
				horizontal=false;
			}
			
			//whether it¡®s ok to place a battleship or not
			while(!battleship.okToPlaceShipAt(row, column, horizontal, this)) {
				
				row=rand.nextInt(10);
				column=rand.nextInt(10);
				horizontalCount=rand.nextInt(2);
				
				//we can only genrate 0 or 1 and then we cast them into true or false
				if (horizontalCount==0) {
					horizontal=true;
				}else if(horizontalCount==1) {
					horizontal=false;
				}
			}
			
			//place the battle ship
			battleship.placeShipAt(row, column, horizontal, this);
		}
		
		//place 2 cruisers
		for (int i=0;i<2;i++) {
					
			//generate a cruiser and its position constant
			Ship cruiser=new Cruiser();
			row=rand.nextInt(10);
			column=rand.nextInt(10);
			horizontalCount=rand.nextInt(2);
					
			//we can only generate 0 or 1 and then we cast them into true or false
			if (horizontalCount==0) {
				horizontal=true;
			}else if(horizontalCount==1) {
				horizontal=false;
			}
					
			//whether it;s ok to place a battleship or not
			while(!cruiser.okToPlaceShipAt(row, column, horizontal, this)) {
						
				row=rand.nextInt(10);
				column=rand.nextInt(10);
				horizontalCount=rand.nextInt(2);
						
				//we can only genrate 0 or 1 and then we cast them into true or false
				if (horizontalCount==0) {
					horizontal=true;
				}else if(horizontalCount==1) {
					horizontal=false;
				
				}
			}		
			cruiser.placeShipAt(row, column, horizontal, this);
		}
		//place 3 destroyers
		for (int i=0;i<3;i++) {
							
			//generate a destroyer and its position constant
			Ship destroyer=new Destroyer();
			row=rand.nextInt(10);
			column=rand.nextInt(10);
			horizontalCount=rand.nextInt(2);
							
			//we can only generate 0 or 1 and then we cast them into true or false
			if (horizontalCount==0) {
				horizontal=true;
			}else if(horizontalCount==1) {
				horizontal=false;
			}
							
			//whether it;s ok to place a battleship or not
			while(!destroyer.okToPlaceShipAt(row, column, horizontal, this)) {
								
				row=rand.nextInt(10);
				column=rand.nextInt(10);
				horizontalCount=rand.nextInt(2);
								
			//we can only genrate 0 or 1 and then we cast them into true or false
				if (horizontalCount==0) {
					horizontal=true;
				}else if(horizontalCount==1) {
					horizontal=false;
						
				}
			}		
			destroyer.placeShipAt(row, column, horizontal, this);
		}
		
		//place 4 submarines
		for (int i=0;i<4;i++) {
									
			//generate a destroyer and its position constant
			Ship submarine=new Submarine();
			row=rand.nextInt(10);
			column=rand.nextInt(10);
			horizontalCount=rand.nextInt(2);
									
			//we can only generate 0 or 1 and then we cast them into true or false
			if (horizontalCount==0) {
				horizontal=true;
			}else if(horizontalCount==1) {
				horizontal=false;
			}
									
			//whether it;s ok to place a battleship or not
			while(!submarine.okToPlaceShipAt(row, column, horizontal, this)) {
										
				row=rand.nextInt(10);
				column=rand.nextInt(10);
				horizontalCount=rand.nextInt(2);
										
			//we can only genrate 0 or 1 and then we cast them into true or false
				if (horizontalCount==0) {
					horizontal=true;
				}else if(horizontalCount==1) {
					horizontal=false;
								
				}
			}		
			submarine.placeShipAt(row, column, horizontal, this);
		}
	}
	
	/**
	 * check if the given location is a ship
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row,int column) {
		//load the ship array and get its type
		if(this.getShipArray()[row][column].getShipType()=="empty") {
			return false;
		}
		
		return true;
	}
	
	/**
	 * returns true when the location has a floating ship
	 * also updates the number of hits and fireshotted
	 */
	boolean shootAt(int row,int column) {
		
		//make sure that all the input data are inside the boundry
		if ((row<0 ||row>9)||(column<0 || column>9)){
			return false;
		}
		
		//load the ship information
		Ship[][] shipArray=this.getShipArray();
		Ship ship=shipArray[row][column];
		//used to debug
		//System.out.println(ship.getShipType());
		//check if it's sunk
		if(!ship.isSunk()) {
			//used to debug
			//System.out.println("kkk");
			if(ship.shootAt(row, column)) {
				
				//if hitted,increment the hit count
				this.hitCount++;
				
				//if sunk the ship,increment the sunk ship count
				if (ship.isSunk()) {
					this.shipsSunk++;
					
				}
			}
			

			//if some ships hitted,increment the shots fired return true
			if (this.isOccupied(row, column)){
				this.shotsFired++;
				return true;
			}
		}
		
		//anyway increment the shots fired
		this.shotsFired++;
		return false;
	}
	
	
	/**
	 * if all sunk,return true
	 * @return
	 */
	boolean isGameOver() {
		if (shipsSunk>=10) {
			return true;
		}
		return false;
	}
	
	//getter/setter
	/**
	 * return the 10 by 10 arrays
	 * @return
	 */
	Ship[][] getShipArray() {
		return ships;
	}

	
	/**
	 * the number of shots fired
	 * @return
	 */
    int getShotsFired() {
		return shotsFired;
	}

	
    /**
     * all the hits counted
     * @return
     */
	int getHitCount() {
		return hitCount;
	}

	/**
	 * the number of ships sunk
	 * @return
	 */
	int getShipsSunk() {
		return shipsSunk;
	}

	
	/**
	 * print the solution
	 */
	void printSolution() {
		
		//show hte column number
		String s="  0 1 2 3 4 5 6 7 8 9\n";
				
		for (int i=0;i<10;i++) {
			s+=i+" ";
			for(int j=0;j<10;j++) {
				s+=this.ships[i][j]+" ";
			}
			s+="\n";
		}
		
		System.out.println(s);
	}
	
	/**
	 * print the map
	 */
	void print() {
		
		//the column number
		String s="  0 1 2 3 4 5 6 7 8 9\n";
		
		//iterate rows
		for (int i=0;i<10;i++) {
			
			//print the row number
			s+=i+" ";
			
			//iterate columns
			for(int j=0;j<10;j++) {
				
				//check if the square is not hit
				if (!this.ships[i][j].isHit(i, j)) {
					s+="."+" ";
				}
				//if it's not hit
				if (this.ships[i][j].isHit(i,j)) {
					s+=this.ships[i][j]+" ";
				}
			}		
		s+="\n";
		}
		System.out.println(s);
	}
}
