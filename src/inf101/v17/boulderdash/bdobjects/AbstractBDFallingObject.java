package inf101.v17.boulderdash.bdobjects;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;

/**
 * Contains most of the logic associated with objects that fall such as rocks
 * and diamonds.
 *
 * @author larsjaffke
 *
 */
public abstract class AbstractBDFallingObject extends AbstractBDKillingObject {

	/**
	 * A timeout between the moment when an object can fall (e.g. the tile
	 * underneath it becomes empty) and the moment it does. This is necessary to
	 * make sure that the player doesn't get killed immediately when walking
	 * under a rock.
	 */
	protected static final int WAIT = 3;

	protected boolean falling = false;

	/**
	 * A counter to keep track when the falling should be executed next, see the
	 * WAIT constant.
	 */
	protected int fallingTimeWaited = 0;

	public AbstractBDFallingObject(BDMap owner) {
		super(owner);
	}

	/**
	 * This method implements the logic of the object falling. It checks whether
	 * it can fall, depending on the object in the tile underneath it and if so,
	 * tries to prepare the move.
	 */
	public void fall() {
		// Wait until its time to fall
		if (falling && fallingTimeWaited < WAIT) {
			fallingTimeWaited++;
			return;
		}
		// The timeout is over, try and prepare the move
		fallingTimeWaited = 0;

		Position pos = owner.getPosition(this);
		// The object cannot fall if it is on the lowest row.
		if (pos.getY() > 0) {
			try {

				/** make position elements to make objects of them later
				 * 
				 * 
				 * */
				Position below = pos.moveDirection(Direction.SOUTH);
				Position east = pos.moveDirection(Direction.EAST);
				Position west = pos.moveDirection(Direction.WEST);
				Position belowEast = east.moveDirection(Direction.SOUTH);
				Position belowWest = west.moveDirection(Direction.SOUTH);

				IBDObject under = owner.get(below);
				IBDObject right = null;
				IBDObject left = null;
				IBDObject underRight = null;
				IBDObject underLeft = null;

				// To check if coordinates exist in grid
				if (pos.getX() + 1 < owner.getWidth()) {
					right = owner.get(east);
					underRight = owner.get(belowEast);
				}

				if (pos.getX() > 0) {
					left = owner.get(west);
					underLeft = owner.get(belowWest);

				}

				if (falling) {

					// fall one step if tile below is empty or killable
					if (under instanceof BDEmpty || under instanceof IBDKillable) {
						prepareMoveTo(Direction.SOUTH);
					}
					// checks if rock is under falling object, then checks if
					// the south east block is instanceof empty or killable,
					// then it prepares to move south then east
					else if (under instanceof BDRock) {
						if (underRight instanceof BDEmpty || underRight instanceof IBDKillable) {
							prepareMoveTo(Direction.SOUTH);
							prepareMoveTo(Direction.EAST);
						} else if (underLeft instanceof BDEmpty || underLeft instanceof IBDKillable) {
							prepareMoveTo(Direction.SOUTH);
							prepareMoveTo(Direction.WEST);
						} else
							falling = false;
					} else {
						falling = false;
					}
				} else {
					// start falling if tile below is empty
					falling = under instanceof BDEmpty;
					fallingTimeWaited = 1;
				}
			} catch (IllegalMoveException e) {
				// This should never happen.
				System.out.println(e);
				System.exit(1);
			}
		}
	}

	@Override
	public void step() {
		// (Try to) fall if possible
		fall();
		super.step();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
