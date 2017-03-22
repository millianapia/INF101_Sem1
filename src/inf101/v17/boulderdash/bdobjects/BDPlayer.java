package inf101.v17.boulderdash.bdobjects;

import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.InputStream;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import static org.junit.Assert.assertTrue;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;

/**
 * An implementation of the player.
 *
 * @author larsjaffke
 *
 */
public class BDPlayer extends AbstractBDMovingObject implements IBDKillable {

	/**
	 * Is the player still alive?
	 */
	protected boolean alive = true;

	/**
	 * The direction indicated by keypresses.
	 */
	protected Direction askedToGo;

	/**
	 * Number of diamonds collected so far.
	 */
	protected int diamondCnt = 0;
	private ImagePattern image;

	// added texture to wall through ImagePattern
	public BDPlayer(BDMap owner) {
		super(owner);
		InputStream resourceAsStream = getClass().getResourceAsStream("../player.png");
		image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1, 1, true);
	}

	@Override
	public Paint getColor() {
		return image;
	}

	/**
	 * @return true if the player is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	// saves a button as a direction, then saves the direction in askedtogo
	public void keyPressed(KeyCode key) {
		if (key == KeyCode.LEFT)
			askedToGo = Direction.WEST;
		else if (key == KeyCode.RIGHT)
			askedToGo = Direction.EAST;
		else if (key == KeyCode.UP)
			askedToGo = Direction.NORTH;
		else if (key == KeyCode.DOWN)
			askedToGo = Direction.SOUTH;

	}

	@Override
	public void kill() {
		this.alive = false;
	}

	/**
	 * Returns the number of diamonds collected so far.
	 *
	 * @return
	 */
	public int numberOfDiamonds() {
		return diamondCnt;
	}

	/*Method to move player
	 * first checks if askedToGo is not null and if player can go there
	 * saves next position in next. Then different ifs and else ifs, to 
	 * check which element it is. Different elements have different
	 * behavior. diamond count should increase if its diamond etc.  
	 * sets askedToGo to null, and use super.step()
	 * */
	@Override
	public void step() {
		Position playerPos = this.getPosition();
		IBDObject player = owner.get(this.getX(), this.getY());
		if (askedToGo != null && owner.canGo(player, askedToGo)) {
			Position next = playerPos.moveDirection(askedToGo);

			if (owner.get(next) instanceof BDDiamond) {

				try {
					prepareMove(playerPos.moveDirection(askedToGo));
					diamondCnt++;
				} catch (IllegalMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (owner.get(next) instanceof BDRock) {
				try {
					BDRock rock = (BDRock) owner.get(next);
					if (rock.push(askedToGo))

						prepareMove(playerPos.moveDirection(askedToGo));

				} catch (IllegalMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (owner.get(next) instanceof BDBug) {
				try {
					prepareMove(playerPos.moveDirection(askedToGo));
					kill();
				} catch (IllegalMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					prepareMove(playerPos.moveDirection(askedToGo));
				} catch (IllegalMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		askedToGo = null;
		super.step();
	}

	@Override
	public boolean isKillable() {
		return true;
	}
}
