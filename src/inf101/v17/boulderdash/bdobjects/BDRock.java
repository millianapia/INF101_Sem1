package inf101.v17.boulderdash.bdobjects;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.*;

public class BDRock extends AbstractBDFallingObject {

	public BDRock(BDMap owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Paint getColor() {
		// TODO Auto-generated method stub
		return Color.LIGHTGRAY;
	}

	public boolean push(Direction dir) {
		try {
			if (dir == Direction.WEST || dir == Direction.EAST) {
				Position rockPos = this.getPosition();
				Position next = (Position) owner.get(nextPos);
				if (owner.canGo(nextPos) && owner.get(nextPos) instanceof BDEmpty) {

					prepareMove(nextPos);
					if (!(rockPos == next)) {
						return true;
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}

}
