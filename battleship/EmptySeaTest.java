package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmptySeaTest {
	
	Ocean ocean;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetShipType() {
		Ship empty=new EmptySea();
		assertEquals("empty",empty.getShipType());
	}

	@Test
	void testShootAt() {
		
		//test shoot on the empty sea
		Ship empty=new EmptySea();
		int row =2;
		int col =1;
		boolean horizontal= true;
		empty.placeShipAt(row, col, horizontal, ocean);
		assertFalse(empty.shootAt(2, 2));
		boolean [] hit= {true,false,false,false};
		assertArrayEquals(hit,empty.getHit());
		
		//test shoot on the empty sea
		Ship empty1=new EmptySea();
		row =2;
		col =2;
		horizontal= true;
		empty1.placeShipAt(row, col, horizontal, ocean);
		assertFalse(empty1.shootAt(2, 1));
		boolean [] hit1= {true,false,false,false};
		assertArrayEquals(hit1,empty1.getHit());
		
		
		
	}

	@Test
	void testIsSunk() {
		
		//test always false
		Ship empty=new EmptySea();
		assertFalse(empty.isSunk());
	}

	@Test
	void testToString() {
		
		Ship empty=new EmptySea();
		assertEquals("-",empty.toString());
	}

	@Test
	void testEmptySea() {
		Ship empty=new EmptySea();
		assertEquals(1,empty.getLength());
	}

}
