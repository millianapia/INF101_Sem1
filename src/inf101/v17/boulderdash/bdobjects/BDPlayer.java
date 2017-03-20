package inf101.v17.boulderdash.bdobjects;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

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

	public BDPlayer(BDMap owner) {
		super(owner);
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	/**
	 * @return true if the player is alive
	 */
	public boolean isAlive() {
		return alive;
	}

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

	@Override
	public void step() {
		Position playerPos = this.getPosition();
	
		/*if (askedToGo != null) {
			if (owner.canGo(playerPos, askedToGo)) {
			
				
		
				owner.getPosition(askedToGo);
			
		IBDObject obj = owner.get(askedToGo);
				
			}

		}*/
		
		
		
			Position next = this.getNextPosition();
		try {
			prepareMove(next);
		} catch (IllegalMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		askedToGo = null;
		super.step();	
	}

	@Override
	public boolean isKillable() {
		return true;
	}
}
