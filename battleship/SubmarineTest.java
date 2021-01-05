package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubmarineTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetShipType() {
		Ship submarine=new Submarine();
		assertEquals("submarine",submarine.getShipType());
	}

	@Test
	void testSubmarine() {
		Ship submarine=new Submarine();
		assertEquals(1,submarine.getLength());
	}

}
