import java.awt.Image;
import java.awt.geom.AffineTransform;

public class SuperSlimeEnemy extends SlimeEnemy {
	public SuperSlimeEnemy(int x, int y) {
		super(x, y);
		img = getImage("/imgs/madslime.gif");
		health = 300;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x,y);
	}
}
