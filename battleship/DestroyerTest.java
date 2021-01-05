package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetShipType() {
		Ship destroyer=new Destroyer();
		assertEquals("destroyer",destroyer.getShipType());
	}

	@Test
	void testDestroyer() {
		Ship destroyer=new Destroyer();
		assertEquals(2,destroyer.getLength());
	}

}
