package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//test the empty sea
		assertFalse(ocean.isOccupied(9, 9));
		
		//place a battleship
		Battleship battleship=new Battleship();
		row=5;
		column=7;
		horizontal=true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(5, 7));
		assertTrue(ocean.isOccupied(5, 6));
		assertTrue(ocean.isOccupied(5, 5));
		assertFalse(ocean.isOccupied(4, 7));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//input out of boundry
		assertFalse(ocean.shootAt(10, 9));
		
		
		//test with a sunk submarine
		Submarine sub=new Submarine();
		sub.placeShipAt(0, 0, true, ocean);
		sub.shootAt(0, 0);
		//sunk the submarine
		assertTrue(sub.isSunk());
		assertFalse(ocean.shootAt(0, 0));
		
		//test with a floating submarine
		Submarine sub1=new Submarine();
		sub1.placeShipAt(1, 0, true, ocean);
		assertTrue(ocean.shootAt(1, 0));
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//multiple times shoots at the same point. Since it's still floating, still counts
		assertFalse(ocean.shootAt(0, 5));
		assertFalse(ocean.shootAt(0, 5));
		assertEquals(8, ocean.getShotsFired());
		
		//sunk the submarine and counts shotsfired
		assertTrue(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(10, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//shoot the same point again, hit count should increment
		
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));
		assertEquals(3, ocean.getHitCount());
		
		//miss the shot, that shouldn't count
		destroyer.placeShipAt(5, 2, true, ocean);
		assertFalse(ocean.shootAt(9, 9));
		assertFalse(ocean.shootAt(8, 8));
		assertEquals(3, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//sunk the destroyer in the first scenerio
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		//place a submarine and sunk it, the second shot doesnt count since it's sunk. So totally 3 hits and 2 sunk ships
		Ship sub=new Submarine();
		sub.placeShipAt(1, 1, true, ocean);
		assertFalse(ocean.shootAt(0, 0));
		assertTrue(ocean.shootAt(1, 1));
		assertFalse(ocean.shootAt(1, 1));
		assertEquals(3, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//place a battleship and check its type
		Ship battleship=new Battleship();
		battleship.placeShipAt(5, 5, true, ocean);
		Ship[][] shipArray2 = ocean.getShipArray();
		
		assertEquals("battleship",shipArray2[5][5].getShipType());
		assertEquals("battleship",shipArray2[5][4].getShipType());
		assertEquals("battleship",shipArray2[5][3].getShipType());
		assertEquals("empty", shipArray[5][1].getShipType());
		
		//place a submarine and sunk it then check the hitlist
		Ship sub =new Submarine();
		sub.placeShipAt(9, 9, true, ocean);
		ocean.shootAt(9, 9);
		Ship[][] shipArray3 = ocean.getShipArray();
		boolean[] hitList= {true,false,false,false};
		assertArrayEquals(hitList,shipArray3[9][9].getHit());
	}
	
	@Test
	void testPrintSolution() {
		//this is the output function which prints the solution after the whole game.It's same as print function 
		;
	}
	
	@Test
	void testOcean() {
		
		//test the ocean constructor
		Ocean ocean=new Ocean();
		assertEquals(0,ocean.getHitCount());
		assertEquals(0,ocean.getShotsFired());
		assertEquals(0,ocean.getShipsSunk());
		assertEquals("empty",ocean.getShipArray()[0][0].getShipType());
		assertEquals("empty",ocean.getShipArray()[9][9].getShipType());
	}

	
}
