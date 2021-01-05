package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//test cruiser length
		Ship ship2 = new Cruiser();
		assertEquals(3, ship2.getLength());
		
		//desroyer length
		Ship ship3 = new Destroyer();
		assertEquals(2, ship3.getLength());
		
		//test submarine length
		Ship ship4 = new Submarine();
		assertEquals(1, ship4.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//get horizontal cruiser bow row
		Ship cruiser = new Cruiser();
		row = 1;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//get verticle destroyer bow row 
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//get horizontal cruiser bow column
		Ship cruiser = new Cruiser();
		row = 1;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, battleship.getBowColumn());
		
		//get verticle destroyer bow column
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, battleship.getBowColumn());
		
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//test cruiser's hit list
		Ship ship2=new Cruiser();
		assertArrayEquals(hits, ship2.getHit());
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		//test destroyer's hit list
		Ship ship3=new Destroyer();
		assertArrayEquals(hits, ship3.getHit());
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		//test emptySea's hit list
		Ship ship4 =new Destroyer();
		assertArrayEquals(hits, ship4.getHit());
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
	}

	@Test
	void testIsHorizontal() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//test non-horizontal cruiser
		Ship cruiser = new Cruiser();
		row = 8;
		column = 8;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		
		//test empty sea
		Ship emptySea = new EmptySea();
		row = 2;
		column = 4;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		assertFalse(emptySea.isHorizontal());		
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//test empty sea
		Ship emptySea = new EmptySea();
		assertEquals("empty", emptySea.getShipType());
		
		//test submarine
		Ship submarine = new Submarine();
		assertEquals("submarine", submarine.getShipType());
	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//cruiser
		Ship cruiser = new Cruiser();
		row = 1;
		column = 4;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row,cruiser.getBowRow());
		
		//non-horizontal destroyer
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal =false;
		destroyer.setBowRow(row);
		assertEquals(row,destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//cruiser
		Ship cruiser = new Cruiser();
		row = 1;
		column = 4;
		horizontal = true;
		cruiser.setBowColumn(column);
		assertEquals(column,cruiser.getBowColumn());
				
		//non-horizontal destroyer
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal =false;
		destroyer.setBowColumn(column);
		assertEquals(column,destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//cruiser
		Ship cruiser = new Cruiser();
		row = 1;
		column = 4;
		horizontal = true;
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());
				
		//non-horizontal destroyer
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal =false;
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//test with non-horizontal battleship
		Ship battleship2 = new Battleship();
		row = 8;
		column = 4;
		horizontal = false;
		ok = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "Not OK to place ship here.");
		
		
		//test with emptysea
		Ship ship3 = new EmptySea();
		row = 7;
		column = 5;
		horizontal = true;
		ok = ship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "Not OK to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		

		//test third ship on the left-lower
		Ship submarine = new Submarine();
		row = 1;
		column = 3;
		horizontal = true;
		boolean ok3 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship vertically adjacent below.");
		
		//test forth ship on the right-lower
		Ship submarine2 = new Submarine();
		row = 1;
		column = 5;
		horizontal = true;
		boolean ok4 = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship vertically adjacent below.");
		
		
		//test fifth ship (7,7)
		Ship submarine3 = new Submarine();
		row = 7;
		column = 7;
		horizontal = true;
		boolean ok5 = submarine3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok5, "OK to place ship vertically adjacent below.");
		submarine3.placeShipAt(row, column, horizontal, ocean);
		
		//test sixth ship on the top
		Ship submarine4 = new Submarine();
		row = 6;
		column = 7;
		horizontal = true;
		boolean ok6 = submarine4.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6, "Not OK to place ship vertically adjacent below.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		
		//test destroyer at (5,5)
		Ship destroyer = new Destroyer();
		row = 5;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());
		
		assertEquals(destroyer, ocean.getShipArray()[5][5]);
		
		//test cruiser at 3,4
		Ship cruiser = new Cruiser();
		row = 3;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column,cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals(cruiser, ocean.getShipArray()[3][4]);
		assertEquals(cruiser, ocean.getShipArray()[3][3]);
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//test non-horizontal at 9,9
		Ship battleship2 = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(battleship2.shootAt(8, 9));
		boolean[] hitArray1 = {false, true, false, false};
		assertArrayEquals(hitArray1, battleship2.getHit());
		
		//test horizontal cruiser at 7,7
		Ship cruiser = new Cruiser();
		row = 7;
		column = 7;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.shootAt(8, 9));
		assertArrayEquals(hitArray0, cruiser.getHit());
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//place submarine at 5,2 and hit it
		Ship submarine2 = new Submarine();
		row = 5;
		column = 2;
		horizontal = true;
		submarine2.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine2.isSunk());
		assertTrue(submarine2.shootAt(5, 2));
		assertTrue(submarine2.isSunk());
		
		//place cruiser at 7,8 and sunk it
		Ship cruiser = new Cruiser();
		row = 7;
		column = 8;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(7, 8));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(7, 7));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(7, 6));
		assertTrue(cruiser.isSunk());
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//test cruiser
		Ship cruiser = new Cruiser();
		assertEquals("x", cruiser.toString());
		
		//test sunk submarine
		Ship submarine = new Submarine();
		row=7;
		column=7;
		horizontal=true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(7, 7);
		assertEquals("s", submarine.toString());
	}
	
	@Test
	
	void testIsEmpty() {
		
		//test a battle ship
		Ship battleship = new Battleship();
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isEmpty());
		
		//test an empty sea
		assertTrue(ocean.getShipArray()[0][0].isEmpty());
		
		//test a horizontal cruiser
		Ship cruiser = new Cruiser();
		row = 9;
		column = 8;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isEmpty());
	}
	
	@Test
	void testIsHit() {
		
		//test a battleship at 9,1and only shoot at bow
		Ship battleship = new Battleship();
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isHit(7,7));
		assertFalse(battleship.isHit(9,1));
		battleship.shootAt(9, 1);
		assertTrue(battleship.isHit(9,1));
		assertFalse(battleship.isHit(7,1));
		
		//test an empty sea and shoot it
		assertFalse(ocean.getShipArray()[1][1].isHit(1,1));
		ocean.getShipArray()[1][1].shootAt(1, 1);
		assertTrue(ocean.getShipArray()[1][1].isHit(1,1));
		
		//test a battleship at 9,1 and only shoot twice at bow
		battleship = new Battleship();
		row = 7;
		column = 6;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isHit(7,6));
		assertFalse(battleship.isHit(9,1));
		battleship.shootAt(7, 6);
		assertTrue(battleship.isHit(7,6));
		battleship.shootAt(7, 6);
		assertTrue(battleship.isHit(7,6));
	}
}
