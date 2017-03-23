package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.bdobjects.AbstractBDFallingObject;
import inf101.v17.boulderdash.bdobjects.BDBug;
import inf101.v17.boulderdash.bdobjects.BDDiamond;
import inf101.v17.boulderdash.bdobjects.BDPlayer;
import inf101.v17.boulderdash.bdobjects.BDRock;
import inf101.v17.boulderdash.bdobjects.IBDObject;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;

public class FallingTest {

	private BDMap map;

	@Before
	public void setup() {
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 4, 'd');
		grid.set(0, 0, '*');
		map = new BDMap(grid);
	}

	@Test
	public void fallingTest2() {
		checkFall(new Position(0, 4));
	}

	@Test
	public void fallingKills1() {
		// diamond two tiles above kills player
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 4, 'd');
		grid.set(0, 2, 'p');
		grid.set(0, 0, '*');
		map = new BDMap(grid);

		checkFall(new Position(0, 4));
		checkFall(new Position(0, 3));
		checkFall(new Position(0, 2));
		assertFalse(map.getPlayer().isAlive());
	}

	@Test
	public void restingDoesntKill1() {
		// diamond on top of player doesn't kill player
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 3, 'd');
		grid.set(0, 2, 'p');
		grid.set(0, 0, '*');
		map = new BDMap(grid);
		Position diamondPos = new Position(0, 3);
		Position playerPos = new Position(0, 2);
		IBDObject diamond = map.get(diamondPos);
		IBDObject player = map.get(playerPos);
		assertTrue(diamond instanceof BDDiamond);
		assertTrue(player instanceof BDPlayer);

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(diamond, map.get(0, 3));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(diamond, map.get(0, 3));

		// wall reached, no more falling
		for (int i = 0; i < 10; i++)
			map.step();
		assertEquals(diamond, map.get(0, 3));

	}

	@Test
	public void fallingTest1() {
		IBDObject obj = map.get(0, 4);
		assertTrue(obj instanceof BDDiamond);

		// four steps later, we've fallen down one step
		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(obj, map.get(0, 3));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(obj, map.get(0, 2));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(obj, map.get(0, 1));

		// wall reached, no more falling
		for (int i = 0; i < 10; i++)
			map.step();
		assertEquals(obj, map.get(0, 1));
	}

	protected Position checkFall(Position pos) {
		IBDObject obj = map.get(pos);
		if (obj instanceof AbstractBDFallingObject) {
			Position next = pos.moveDirection(Direction.SOUTH);
			if (map.canGo(next)) {
				IBDObject target = map.get(next);
				if (target.isEmpty() || target.isKillable()) {
				} else {
					next = pos;
				}
			} else {
				next = pos;
			}

			// map.step(); System.out.println(map.getPosition(object));
			map.step();
			map.step();
			map.step();
			map.step();
			assertEquals(obj, map.get(next));
			return next;
		} else
			return pos;
	}

	@Test
	public void fallingTest3() {
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 4, 'r');
		grid.set(0, 0, '*');
		map = new BDMap(grid);
		Position rockPos = new Position(0, 4);
		IBDObject rock = map.get(rockPos);
		assertTrue(rock instanceof BDRock);

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(rock, map.get(0, 3));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(rock, map.get(0, 2));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(rock, map.get(0, 1));

	}

	@Test
	public void eastPushTest() {
		IGrid<Character> grid = new MyGrid<>(3, 3, ' ');
		grid.set(0, 0, 'p');
		// grid.set(0, 1, 'r');
		map = new BDMap(grid);
		// Position rockPos = new Position(0, 1);
		Position playerPos = new Position(0, 0);
		// IBDObject rock = map.get(rockPos);
		IBDObject player = map.get(playerPos);
		// assertTrue(rock instanceof BDRock);
		assertTrue(player instanceof BDPlayer);

		BDPlayer player2 = (BDPlayer) player;
		Position playerPos2 = map.getPosition(player2);
		// System.out.println("steinpos1"+rockPos);
		System.out.println("spillerpos1: " + playerPos2);

		try {
			player2.prepareMove(playerPos2.moveDirection(Direction.EAST));
		} catch (IllegalMoveException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < 100; i++)
			player2.step();

		System.out.println("spillerpos2: " + map.getPosition(player2));
		// System.out.println("steinpos2"+rockPos);

		// assertEquals(rock, map.get(0, 2));

	}

}
