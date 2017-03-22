package inf101.v17.boulderdash.bdobjects;

import javafx.scene.paint.Color;

import inf101.v17.boulderdash.maps.BDMap;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.InputStream;

/**
 * An empty tile.
 *
 * @author larsjaffke
 *
 */
public class BDEmpty extends AbstractBDObject {
	private ImagePattern image;

	// added texture to wall through ImagePattern
	public BDEmpty(BDMap owner) {
		super(owner);
		InputStream resourceAsStream = getClass().getResourceAsStream("../sandDown.png");
		image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1, 1, true);
	}

	@Override
	public Paint getColor() {
		return image;
	}

	@Override
	public void step() {
		// DO NOTHING
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
}
