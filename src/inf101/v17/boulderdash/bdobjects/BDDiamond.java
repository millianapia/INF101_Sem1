package inf101.v17.boulderdash.bdobjects;

import javafx.scene.paint.Color;

import inf101.v17.boulderdash.maps.BDMap;

/**
 * A diamond object. All its logic is implemented in the abstract superclass.
 *
 * @author larsjaffke
 *
 */
public class BDDiamond extends AbstractBDFallingObject {

	public BDDiamond(BDMap owner) {
		super(owner);
	}

	@Override
	public Color getColor() {
		return Color.LIGHTBLUE;
	}
	

}
