package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

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
		IGrid<Character> grid = new MyGrid<>(1, 2, ' ');
		grid.set(0, 0, 'p');
		grid.set(0, 1, 'd');
		map = new BDMap(grid);

		// find the bug
		Position playerPos = new Position(0, 0);
		IBDObject player = map.get(playerPos);
		Position diamondPos = new Position(0, 1);
		IBDObject diamond = map.get(diamondPos);

		assertTrue(player instanceof BDPlayer);
		assertTrue(diamond instanceof BDDiamond);

		System.out.println(playerPos);
		System.out.println(diamondPos);

		((BDPlayer) player).keyPressed(KeyCode.RIGHT);
		for (int i = 0; i < 10; i++)
			map.step();

		System.out.println(playerPos);
		System.out.println(diamondPos);
		assertEquals(playerPos, diamondPos);

	}

	@Test
	public void pushTest() {
		IGrid<Character> grid = new MyGrid<>(1, 3, ' ');
		grid.set(0, 0, 'p');
		grid.set(0, 1, 'r');
		map = new BDMap(grid);

		// find the bug
		Position playerPos = new Position(0, 0);
		IBDObject player = map.get(playerPos);
		Position rockPos = new Position(0, 1);
		Position origRockPos = new Position (0,1);
		Position nextRockPos = new Position (0,2);
		IBDObject rock = map.get(rockPos);

		assertTrue(player instanceof BDPlayer);
		assertTrue(rock instanceof BDRock);

		System.out.println(playerPos + "playpos1");
		System.out.println(rockPos + "rockpos1");

		((BDPlayer) player).keyPressed(KeyCode.RIGHT);
		player.step();
		for (int i = 0; i < 10; i++){

			map.step();
		}
		System.out.println(playerPos + "playpos2");
		System.out.println(rockPos + "rockpos2");

		assertEquals(playerPos, origRockPos);
		assertEquals(rockPos, nextRockPos);
	}
}
