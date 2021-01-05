package battleship;


/**
 * represents a battleship and extends the ship
 * @author xb
 *
 */
public class Battleship extends Ship {
	
	//static variables
	
	/**
	 * length of battleship
	 */
	static int battleshipLength=4;
	
	/**
	 * battleship name 
	 */
	static String battleshipName = "battleship";
	
	//zero-argument public constructor
	public Battleship() {
		super(battleshipLength);
	}
	
	@Override
	/**
	 * get ship type for battleship
	 */
	public String getShipType() {
		return battleshipName;
	}
	
	
	
}
