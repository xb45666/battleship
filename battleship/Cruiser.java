package battleship;

public class Cruiser extends Ship {
	//static
	
	/**
	 * length of cruiser
	 */
	static int cruiserLength=3;
	
	/**
	 * name of cruiser
	 */
	static String cruiserName="cruiser";
	
	public Cruiser() {
		super(cruiserLength);
	}
	
	@Override
	/**
	 * get ship type for cruiser
	 */
	public String getShipType() {
		
		return cruiserName;
		
	}
}
