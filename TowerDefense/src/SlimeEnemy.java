import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class SlimeEnemy {
	private int width;
	private int height;
	private int health;
	private String fileName; 
	private int sx;//slime x movement
	private int sy;//slime y movement
	private boolean hasEscaped;
	//for movement


	//add location attributes
	private Image img; 	
	private AffineTransform tx;
	//scaling variables to easily change and try different scales later
	private double scaleW=1.0, scaleH = 1.0; 
	public int x, y;
	
	public SlimeEnemy(int newX, int newY) {
		img = getImage("/imgs/recoloredSlime.gif"); //load the image for Tree
	
		//initialize the location of the image
		x = newX;
		y = newY;
		
		
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 
		//use your variables
		width = 90;
		height = 80;
		health = 100;
		sx = 0;
		sy = 0;
		hasEscaped = false;
	}
	
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		if (isAlive()) {
			//call update to update the actually picture location
			update();
			g2.drawImage(img, tx, null);
		}
	}
	
	/* update the picture variable location */
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(0.8,0.8);
		x += sx;
		y += sy;
		
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
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void removeHealth() {
		health-= 10;
	}
	
	public void moveRight () {
		sx = 2;
		sy = 0;
	}
	public void moveLeft() {
		sx = -2;
		sy = 0;
	}
	
	public void moveUp() {
		sx = 0;
		sy = -2;
	}
	
	public void moveDown() {
		sx = 0;
		sy = 2;
	}
	
	public void reset() {
		x = -100;
		y = 350;
	}
	
	public void hasEscaped() {
		hasEscaped = true;
	}
	
	public boolean isAlive() {
		return !hasEscaped && health >0;
	}
}
	

	