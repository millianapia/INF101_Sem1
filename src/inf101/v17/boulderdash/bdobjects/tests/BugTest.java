package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.bdobjects.AbstractBDFallingObject;
import inf101.v17.boulderdash.bdobjects.BDBug;
import inf101.v17.boulderdash.bdobjects.BDDiamond;
import inf101.v17.boulderdash.bdobjects.BDPlayer;
import inf101.v17.boulderdash.bdobjects.IBDMovingObject;
import inf101.v17.boulderdash.bdobjects.IBDObject;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;

public class BugTest {

	private BDMap map;

	@Before
	public void setup() {
	}

	@Test
	public void bugMoves() {
		IGrid<Character> grid = new MyGrid<>(4, 4, ' ');
		grid.set(2, 2, 'b');
		map = new BDMap(grid);

		// find the bug
		Position bugPos = new Position(2, 2);
		IBDObject bug = map.get(bugPos);
		assertTrue(bug instanceof BDBug);

		for (int i = 0; i < 100; i++) {
			map.step();
			if (map.get(bugPos) != bug) { // bug has moved
				// reported position should be different
				assertNotEquals(bugPos, map.getPosition(bug));
				// bug moved –  we're done
				return;
			}

		}

		fail("Bug should have moved in 100 steps!");
	}

	@Test
	public void bugEnclosed() {
		IGrid<Character> grid = new MyGrid<>(3, 3, ' ');
		grid.set(0, 0, '*');
		grid.set(0, 1, '*');
		grid.set(0, 2, '*');
		grid.set(1, 0, '*');
		grid.set(1, 2, '*');
		grid.set(2, 0, '*');
		grid.set(2, 1, '*');
		grid.set(2, 2, '*');
		grid.set(1, 1, 'b');
		map = new BDMap(grid);

		// find the bug
		Position bugPos = new Position(1, 1);
		IBDObject bug = map.get(bugPos);

		assertTrue(bug instanceof BDBug);

		for (int i = 0; i < 10; i++) {
			map.step();
			if (map.get(bugPos) != bug) {

				fail("Bug is not enclosed");

			}

		}

		assertEquals(bugPos, map.getPosition(bug));
	}

	@Test
	public void bugNewPosition() {

		IGrid<Character> grid = new MyGrid<>(3, 3, ' ');
		grid.set(1, 1, 'b');
		map = new BDMap(grid);
		Position bugPos = new Position(1, 1);
		Position origPos = new Position(1, 1);
		IBDObject bug = map.get(bugPos);
		assertTrue(bug instanceof BDBug);

		Position westPos = origPos.moveDirection(Direction.WEST);
		Position northPos = westPos.moveDirection(Direction.NORTH);
		Position eastPos = northPos.moveDirection(Direction.EAST);
		Position southPos = eastPos.moveDirection(Direction.SOUTH);

		for (int i = 0; i <= 10; i++) {
			map.step();
		}

		Position bugPos1 = bug.getPosition();

		for (int i = 0; i <= 10; i++) {
			map.step();
		}

		Position bugPos2 = bug.getPosition();

		for (int i = 0; i <= 10; i++) {
			map.step();
		}

		Position bugPos3 = bug.getPosition();

		for (int i = 0; i <= 10; i++) {
			map.step();
		}

		Position bugPos4 = bug.getPosition();

		assertEquals(westPos, bugPos1);
		assertEquals(northPos, bugPos2);
		assertEquals(eastPos, bugPos3);
		assertEquals(southPos, bugPos4);

	}

	@Test
	public void bugVsPlayer() {

		IGrid<Character> grid = new MyGrid<>(3, 3, ' ');
		grid.set(0, 1, 'p');
		grid.set(1, 1, 'b');
		map = new BDMap(grid);
		Position bugPos = new Position(1, 1);
		Position playerPos = new Position(0, 1);
		IBDObject bug = map.get(bugPos);
		IBDObject player = map.get(playerPos);
		assertTrue(bug instanceof BDBug);
		assertTrue(player instanceof BDPlayer);

		for (int i = 0; i <= 10; i++) {
			map.step();

		}
		if ((map.getPlayer().isAlive()) == false) {
			assertTrue(true);
		} else {
			fail("player is alive!");
		}

	}

}
