import java.awt.Image;
import java.awt.geom.AffineTransform;

public class SuperSlimeEnemy extends SlimeEnemy {
	
	//same code as the original slime enemy, but its health is a bit larger.
	//extends the slime enemy class so it can use the same logic
	public SuperSlimeEnemy(int x, int y) {
		super(x, y);
		img = getImage("/imgs/madslime.gif");
		
		//setting health to a higher value so that it increases difficulty with this slime variant
		setHealth(250);
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x,y);
	}
}
