package battleship;

public abstract class Ship {
	
	
	//instance variables
	/**
	 * the row that contains the bow(front part)
	 */
	private int bowRow;
	
	/**
	 * the column that contains the bow
	 */
	private int bowColumn;
	
	/**
	 * the length of ship
	 */
	private int length;
	
	/**
	 * represents wheter the ship is placed horizontally or vertically
	 */
	private boolean horizontal;
	
	/**
	 * an array of 4 booleans that indicate whether that part has been shotted
	 * 
	 */
	private boolean[] hit;
	
	
	//static variable
	/**
	 * sign for sunk ship
	 */
	static char sunk ='s';
	/**
	 * sign for floating ship
	 */
	static char notSunk='x';
	
	//default constructor
	
	public Ship(int length) {
		this.length=length;
		this.hit=new boolean [4];
	};
	

	
	
	// getter method
	
	
	/**
	 * get length 
	 * @return length of ship
	 */
	public int getLength() {
		return this.length;
		
	}
	
	/**
	 * get bow Row
	 * @return bow's row cordinate
	 */
	public int getBowRow() {
		
		return this.bowRow;
		
	}
	
	/**
	 * get bow column
	 * @return bow's column
	 */
	public int getBowColumn() {
		
	   return this.bowColumn;
	}
	
	/**
	 * get the hit list
	 * @return hit list
	 */
	public boolean[] getHit() {
		
		return this.hit;
		
	}
	
	/**
	 * ship is horizontal or not
	 * @return bow is horizontal or not
	 */
	public boolean isHorizontal() {
		
		return this.horizontal;
	}
	
	
	// setter method
	/**
	 * set the row of the bow
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow=row;
	}
	
	/**
	 * set the column of the bow
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn=column;
	}
	
	/**
	 * set the horizontal status of bow
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal=horizontal;
	}
	
	/**
	 * an abstract method in abstract class ship
	 * @return
	 */
	public abstract String getShipType();


	//other methods
	/**
	 * based on param, is it ok to place the ship
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row,int column,boolean horizontal,Ocean ocean) {
		
		//get the length
		int shipLength=this.getLength();
		
		//load the ship array
		Ship[][] shipArray=ocean.getShipArray();
		
		
		//if its horizontal
		if (horizontal) {
			
			//calculate the stern
			int stern = column-shipLength+1;
			
			//check if stern is out of boundary
			if (stern<0) {
				return false;
			}
			
			//check if any ships on the position
			for (int i =stern;i<=column;i++) {
				
			
				//check if any ships on the position
				if(!shipArray[row][i].isEmpty()) {
					return false;
				}
			
			
				//check the bow now
				if (i == column) {
					
					//check right
					if (column<=8) {
						//adjacent right
						if(!shipArray[row][column+1].isEmpty()) {
							return false;
						}
						//right upper corner
						if (row>=1) { 
							if(!shipArray[row-1][column+1].isEmpty()) {
								return false;
							}
						}
						//right lower corner
						if (row<=8) {
							if(!shipArray[row+1][column+1].isEmpty()) {
								return false;
							}
						}
					//check top
					if (row>=1) {
						if (!shipArray[row-1][column].isEmpty()) {
							return false;
						}
					}
					
					//check bot
					if (row<=8) {
						if (!shipArray[row+1][column].isEmpty()) {
							return false;
						}
					}	
				}
			}
				
			//check all the parts other than bow and stern	
			if(i<=column&&i>=stern) {
				//check top
				if (row>=1) {	
					if (!shipArray[row-1][i].isEmpty()) {
						return false;
					}
				}
					
				//check bot
				if (row<=8) {
					if (!shipArray[row+1][i].isEmpty()) {
						return false;
					}
				}
			}
			
			//check the stern
			if(i==stern) {
				
				//check left
				if(stern>=1) {
					//adjacent left
					if(!shipArray[row][stern-1].isEmpty()) {
						return false;
					}
					
					//left upper
					if(row>=1) {
						if(!shipArray[row-1][stern-1].isEmpty()) {
							return false;
						}
					}
					
					//left lower
					if(row<=8) {
						if (!shipArray[row+1][stern-1].isEmpty()) {
							return false;
						}
					}
				}
				//check top
				if (row>=1) {
					if (!shipArray[row-1][stern].isEmpty()) {
						return false;
					}
				}
				//check bot
				if (row<=8) {
					if (!shipArray[row+1][stern].isEmpty()) {
						return false;
					}
				}
			}
		}
	}else if(!horizontal) {
		
		int stern = row-shipLength+1;
		
		//check if stern is out of boundary
		if (stern<0) {
			return false;
		}
		
		//check if any ships on the position
		for (int i =stern;i<=row;i++) {
			
		
			//check if any ships on the position
			if(!shipArray[i][column].isEmpty()) {
				return false;
			}
		
		
			//check the bow now
			if (i == row) {
				
				//check bot
				if (row<=8) {
					//adjacent bot
					if(!shipArray[row+1][column].isEmpty()) {
						return false;
					}
					//left lower corner
					if (column>=1) {
						if(!shipArray[row+1][column-1].isEmpty()) {
							return false;
						}
					}
					//right lower corner
					if (column<=8) {
						if(!shipArray[row+1][column+1].isEmpty()) {
							return false;
						}
					}
				//check left
				if (column>=1) {
					if (!shipArray[row][column-1].isEmpty()) {
						return false;
					}
				}
				
				//check right
				if (column<=8) {
					if (!shipArray[row][column+1].isEmpty()) {
						return false;
					}
				}	
			}
		}
			
		//check all the parts other than bow and stern	
		if(i<=row&&i>=stern) {
			//check left
			if (column>=1) {	
				if (!shipArray[i][column-1].isEmpty()) {
					return false;
				}
			}
				
			//check right
			if (column<=8) {
				if (!shipArray[i][column+1].isEmpty()) {
					return false;
				}
			}
		}
		
		//check the stern
		if(i==stern) {
			
			//check top
			if(stern>=1) {
				//adjacent top
				if(!shipArray[stern-1][column].isEmpty()) {
					return false;
				}
				
				//left upper
				if (column>=1) {
					if(!shipArray[stern-1][column-1].isEmpty()) {
						return false;
					}
				}	
				
				//right upper
				if (column<=8) {
					if(!shipArray[stern-1][column+1].isEmpty()) {
						return false;
					}
				}
			}
			//check left
			if (column>=1) {
				if (!shipArray[stern][column-1].isEmpty()) {
					return false;
				}
			}
			//check right
			if (column<=8) {
				if (!shipArray[stern][column+1].isEmpty()) {
					return false;
				}
			}
		}
	}
	}
		return true;
	}
	
	/**
	 * check if the ship is an empty sea
	 * @return
	 */
	boolean isEmpty() {
		
		return "empty".equals(this.getShipType());
	}
	
	/**
	 * place the ship and put reference to the ship array
	 * @param row of Bow
	 * @param column of bow
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row,int column,boolean horizontal,Ocean ocean) {
		
		//set the Bow
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		
		if (horizontal) {
			//find the stern
			int stern= column-this.getLength()+1;
			
			//set the reference
			for (int i=stern;i<=column;i++) {
				ocean.getShipArray()[row][i]=this;
			}
		}else if(!horizontal) {
			
			//find the stern
			int stern =row-this.getLength()+1;
			
			//set the reference
			for (int i=stern;i<=row;i++) {
				
				ocean.getShipArray()[i][column]=this;
			}
			
		}
	}
	
	/**
	 * shoot and make sure it's not sunk
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row,int column) {
		if (!this.isSunk()) {
			//counter for the position
			int shipPositionCount=0;
			
			//if it's horizontal
			if (this.isHorizontal()) {
				int stern =this.getBowColumn()-this.getLength()+1;
				
				//if the same row
				if (row==this.getBowRow()) {
					
					//iterate the row to look for the hit position
					for (int i =stern;i<=this.getBowColumn();i++) {
						if (column==i) {
							
							//get the position in hit array
							shipPositionCount=this.getBowColumn()-i;
							
							//update the hit list 
							this.getHit()[shipPositionCount]=true;
							//used to debug
							//System.out.println(this.getHit()[shipPositionCount]);
							return true;
						}
					}
				}
			}
			if (!this.isHorizontal()) {
				int stern =this.getBowRow()-this.getLength()+1;
				//if same col
				if (column==this.getBowColumn()) {
					for (int i =stern;i<=this.getBowRow();i++) {
						if (row==i) {
							//update the hit list
							shipPositionCount=this.getBowRow()-i;
							this.getHit()[shipPositionCount]=true;
							//System.out.println(this.getHit()[shipPositionCount]);
							return true;
						}
					}
				}
			}	
		}
		return false;
	}
	
	/**
	 * tell whether a ship is sunk
	 * @return it's sunk or not
	 */
	boolean isSunk() {
		
		//get the length and hitlist
		int shipLength =this.getLength();
		boolean[] hitList=this.getHit();
		
		//check hit list
		for (int i=0;i<=shipLength-1;i++) {
			if (!hitList[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * the given postion of the ship is hit or not
	 * @param row
	 * @param column
	 * @return status of given position
	 */
	boolean isHit(int row,int column) {
		
		//get all the bow data
		int bowRow=this.getBowRow();
		int bowCol=this.getBowColumn();
		boolean horizontal=this.isHorizontal();
		int shipLength=this.getLength();
		boolean hit[]=this.getHit();
		
		if (horizontal) {
			//locate the hit point
			int checkCount= bowCol-column;
			//check the hitpoint
			if (checkCount>=0 && checkCount<=shipLength-1) {
				if(hit[checkCount]==true) {
					return true;
				}
			}
		}
		
		if (!horizontal) {
			//locate the hit point
			int checkCount= bowRow-row;
			//check the hit point
			if (checkCount>=0 && checkCount<=shipLength-1) {
				if (hit[checkCount]==true) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	/**
	 * for printting the ship
	 */
	public String toString() {
		
		//if its sunk,return s
		if (this.isSunk()) {
			return "s";
		}
		
		return "x";
	}
}