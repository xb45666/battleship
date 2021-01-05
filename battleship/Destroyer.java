package battleship;

public class Destroyer extends Ship {
	//static
	
	/**
	 * length of destroyer
	 */		
	static int destroyerLength=2;
	
	/**
	 * name of destroyer
	 */
	static String destroyerName="destroyer";
		
	public Destroyer() {
		super(destroyerLength);
	}
		
	@Override
	/**
	 * get ship type for destroyer	
	 */
	public String getShipType() {
			
		
		return destroyerName;
			
	}
}
