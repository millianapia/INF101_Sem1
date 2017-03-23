package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.bdobjects.BDBug;
import inf101.v17.boulderdash.bdobjects.BDDiamond;
import inf101.v17.boulderdash.bdobjects.BDPlayer;
import inf101.v17.boulderdash.bdobjects.BDRock;
import inf101.v17.boulderdash.bdobjects.IBDObject;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;
import javafx.scene.input.KeyCode;

public class PlayerTest {

	private BDMap map;

	@Test
	public void diamondTest() {
		IGrid<Character> grid = new MyGrid<>(3, 3, ' ');
		grid.set(0, 0, 'p');
		grid.set(1, 0, 'd');
		map = new BDMap(grid);

		Position playerPos = new Position(0, 0);
		IBDObject player = map.get(playerPos);
		Position diamondPos = new Position(1, 0);
		IBDObject diamond = map.get(diamondPos);
		assertTrue(player instanceof BDPlayer);
		assertTrue(diamond instanceof BDDiamond);

		map.getPlayer().keyPressed(KeyCode.RIGHT);
		map.step();

		boolean gridContainsDiamond = false;
		for (int i = 0; i < grid.getWidth(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
				if (map.get(i, j).equals(diamond)) {
					gridContainsDiamond = true;
					break;
				} else {
					gridContainsDiamond = false;

				}
			}
		}

		assertFalse(gridContainsDiamond);

	}

	@Test
	public void pushTest() {
		IGrid<Character> grid = new MyGrid<>(3, 3, ' ');
		grid.set(0, 0, 'p');
		grid.set(1, 0, 'r');
		map = new BDMap(grid);

		Position playerPos = new Position(0, 0);
		IBDObject player = map.get(playerPos);
		Position rockPos = new Position(1, 0);
		IBDObject rock = map.get(rockPos);

		assertTrue(player instanceof BDPlayer);
		assertTrue(rock instanceof BDRock);
		BDRock rock2 = (BDRock) rock;
		BDPlayer player2 = (BDPlayer) player;

		map.getPlayer().keyPressed(KeyCode.RIGHT);
		map.step();

		rock2.push(Direction.EAST);

		assertEquals(player2, map.get(1, 0));
	}
}
