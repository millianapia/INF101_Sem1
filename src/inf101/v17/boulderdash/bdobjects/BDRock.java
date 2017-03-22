package inf101.v17.boulderdash.bdobjects;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.InputStream;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.InputStream;

public class BDRock extends AbstractBDFallingObject {
	private ImagePattern image;

	// added texture to wall through ImagePattern
	public BDRock(BDMap owner) {
		super(owner);
		InputStream resourceAsStream = getClass().getResourceAsStream("../rock.png");
		image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1, 1, true);
	}

	// returns image instead of color
	@Override
	public Paint getColor() {

		return image;
	}

	/*
	 * method to push the stone in given direction Checks if direction is east
	 * or west, then check if the next position is empty, then sets the next
	 * position in preparemove. Takes a step to avoid rock eating player. 
	 * returns true so it get pushed
	 */
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
