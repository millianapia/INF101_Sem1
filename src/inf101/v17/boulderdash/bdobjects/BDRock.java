package inf101.v17.boulderdash.bdobjects;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.*;

public class BDRock extends AbstractBDFallingObject {

	public BDRock(BDMap owner) {
		super(owner);

	}

	@Override
	public Paint getColor() {

		return Color.LIGHTGRAY;
	}

	public boolean push(Direction dir) {
		Position rockPos = this.getPosition();

		switch (dir) {
		case EAST:
			if (owner.canGo(rockPos, dir)) {
				if (owner.get(rockPos.getX() + 1, rockPos.getY()) instanceof BDEmpty) {
					try {
						prepareMove(rockPos.getX() + 1, rockPos.getY());
						step();
						return true;
					} catch (IllegalMoveException e) {
						return false;
					}
				}
			}
		case WEST:
			if (owner.canGo(rockPos, dir)) {
				if (owner.get(rockPos.getX() - 1, rockPos.getY()) instanceof BDEmpty) {
					try {
						prepareMove(rockPos.getX() - 1, rockPos.getY());
						step();
						return true;
					} catch (IllegalMoveException e) {
						return false;
					}
				}
			}
		default:
			return false;
		}
	}

}
