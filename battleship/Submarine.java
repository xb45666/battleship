package battleship;

public class Submarine extends Ship {
	//static
	/**
	 * length of submarine
	 */
	static int submarineLength =1;
	
	/**
	 * name of submarine
	 */
	static String submarineName="submarine";
	
	public Submarine(){
		super(submarineLength);
	}
	
	
	
	@Override
	/**
	 * get ship type for submarine
	 */
	public String getShipType() {
		
		return submarineName;
	}
}
