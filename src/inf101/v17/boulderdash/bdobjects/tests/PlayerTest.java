package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.bdobjects.BDBug;
import inf101.v17.boulderdash.bdobjects.IBDObject;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;

public class PlayerTest {
	
	private BDMap map;
	
	@Test
	public void diamondTest() {
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
	
	public void pushTest() {
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
}
