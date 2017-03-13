package inf101.v17.boulderdash.bdobjects;

import javafx.scene.paint.Color;

import inf101.v17.boulderdash.maps.BDMap;

/**
 * Implementation of a piece of a wall.
 *
 * @author larsjaffke
 *
 */
public class BDWall extends AbstractBDObject {

	public BDWall(BDMap owner) {
		super(owner);
	}

	@Override
	public Color getColor() {
		return Color.BLACK;
	}

	@Override
	public void step() {
		// DO NOTHING, IT'S A WALL
	}
}
