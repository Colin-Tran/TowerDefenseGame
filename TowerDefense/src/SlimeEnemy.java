import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class SlimeEnemy {
	private int width; // the width is used for the health bar
	private int health;
	private int initialHealth;
	private String fileName; 
	private int sx;//slime x movement
	private int sy;//slime y movement
	private boolean hasEscaped;
	//for movement
	
	//add location attributes
	protected Image img; 	
	protected AffineTransform tx;
	//scaling variables to easily change and try different scales later
		private double scaleW=1.0, scaleH = 1.0; 
	public int x, y;
	
	public SlimeEnemy(int newX, int newY) {
		img = getImage("/imgs/recoloredSlime.gif"); //load the image for Tree
	
		//initialize the location of the image
		x = newX;
		y = newY;
			
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		//use your variables
		width = 90;
	
		health = 180;
		initialHealth = 180;
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
			g.setColor(Color.green);
			g.fillRect(x, y+30, (int)(width*((double)health/initialHealth)), 10);
		}
	}
	
	/* update the picture variable location */
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(0.8,0.8);
		x += sx;
		y += sy;	
	}
	
	protected void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	protected Image getImage(String path) {
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
	
	//if tower hits slime this method is called to remove health from the slime
	public void removeHealth() {
		health-= 10;
		if(health == 0) {
			Music sound = new Music("/imgs/slimedies.wav", false);
			sound.play();
			Game.instance.getPlayer().increaseScore(1);
		}
	}
	
	//ALL MOVEMENT METHODS FOR EACH SLIME
	public void moveRight (int speedX, int speedY) {
		sx = speedX;
		sy = speedY;
		
	
	}
	public void moveLeft(int speedX, int speedY) {
		sx = speedX;
		sy = speedY;
		
	}
	
	public void moveUp(int speedX, int speedY) {
		sx = speedX;
		sy = speedY;
	}
	
	public void moveDown(int speedX, int speedY) {
		sx = speedX;
		sy = speedY;
	}
	
	
	//RESET THE SLIME TO THE STARTING POSITION
	public void reset() {
		x = -100;
		y = 350;
	}
	
	//setting the boolean hasEscaped to true so that we can use this var for logic
	public void hasEscaped() {
		Music sound = new Music("/imgs/slimeescapes.wav", false);
		sound.play();
		hasEscaped = true;
	}
	
	//checking if the slime is alive and isn't outside the borders of the game
	public boolean isAlive() {
		return !hasEscaped && health >0;
	}
	
	public void setLocation(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public void resetStop() {
		sx = 0;
		sy = 0;
		x = -100;
		y = 350;
	}
	
	//setter for slime enemy health
	public void setHealth(int amount) {
		health = amount;
		initialHealth = amount;
	}
	
	
}
	

	