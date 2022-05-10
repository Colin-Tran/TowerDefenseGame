import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Money {
private int start = 100;
private int roundend = 30;
int x = 925;
int y = -45;
int cost;

private Image img; 	
private AffineTransform tx;

public Money(int ncost) {
	img = getImage("/imgs/wolverine bucks.png"); //load the image for Tree
cost = ncost;


tx = AffineTransform.getTranslateInstance(x, y );
init(x, y); 
}

	
	public void roundEnd() {
		start += roundend;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);

	}
	
	/* update the picture variable location */
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(0.3,0.3);

		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	
	
	
}
}
