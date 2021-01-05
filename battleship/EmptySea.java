package battleship;

public class EmptySea extends Ship {
	//static variable
	static int emptyLength=1;
	
	//Constructor
	
	public EmptySea() {
		super(emptyLength);
		
	}
	
	
	@Override
	
	boolean shootAt(int row,int column) {
		this.getHit()[0]=true;
		return false;
	}
	
	@Override
	
	boolean isSunk() {
		return false;
	}
	
	@Override
	
	public String toString() {
		return "-";
	}
	
	@Override
	public String getShipType() {
		return "empty";
		
		
	}
	
	
}
