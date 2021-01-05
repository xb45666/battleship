package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetShipType() {
		Ship battleship=new Battleship();
		assertEquals("battleship",battleship.getShipType());
	}

	@Test
	void testBattleship() {
		//test constructor and test with length
		Ship battleship=new Battleship();
		assertEquals(4,battleship.getLength());
	}

}
