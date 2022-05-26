import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public abstract class Tower { //you can not instantiate Tower class
	
	//attributes
	private AffineTransform tx;
	
	protected String towerType; //name of the tower type
	
	protected double cost; //how much it costs
	protected double range; //number of tiles it can attack enemy
	protected double speed; //interval between 2 consecutive attacks
	
	//drawing info
	private Image img; //the image of the tower
	protected int x, y; //x and y of the left top corner of the tower
	protected int width, height; //width and height of the tower
	protected int rotation; //angle for direction of the attack
	
	//shooting at enemies
	protected SlimeEnemy enemy;//the enemy to shoot at
	protected int cannonPosition;//the position of the cannon
	
	static protected int IMG_PIXELS = 25; //size of the tower pngs
	private int cannonPositionsMax; //how many intermediate steps a cannon ball takes to hit
	
	protected boolean displayRange = true; //if you want the tower to display the range
	
	public Tower(String towerType, double cost,
			double range, double speed, 
			int x, int y, int width, int height, 
			String imageFile) {
		
		this.towerType = towerType;
		this.cost = cost;
		this.range = range;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cannonPositionsMax = (int) (25/speed);
		
		rotation = (int)(360*Math.random()); //initial rotation
		
		img = getImage(imageFile); //load the image for Tower
		initAffineTransform(x, y); //initialize the location, size and rotation of the image								//use your variables
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g.drawRect(x, y, width, height); //shows the borders of the tower
		g2.drawImage(img, tx, null);
		
		if(cannonPosition > 0) { //used for drawing the cannon ball
			g.setColor(Color.black);
			Point enemyCenter = new Point(
					enemy.getX()+width/2, enemy.getY() + height/2);
			double xDistance = enemyCenter.getX()-getCenter().getX();
			double yDistance = enemyCenter.getY()-getCenter().getY();
			g.fillOval((int) (getCenter().getX()+xDistance*cannonPosition/cannonPositionsMax),
					(int) (getCenter().getY()+ yDistance*cannonPosition/cannonPositionsMax), 10, 10);
		}
		
	}
	
	private void initAffineTransform(double a, double b) {	
		tx = new AffineTransform();
		
		//the transformations we need for the image are
		//1. rotate around its center
		//2. scale up to the desired width and height
		//3. move/translate to the x,y location 
		//Note: the transformations are in the last-in, first-out order
		//as mentioned in this article: https://stackoverflow.com/questions/3843105/scaling-translating-a-shape-to-a-given-rectangle-using-affinetransform
		
		tx.translate(a, b); //step 3 above
		
		double scaleX = (double)width/IMG_PIXELS; //pngs are 25 x 25 pixels
		double scaleY = (double)height/IMG_PIXELS;
		tx.scale(scaleX, scaleY); //step 2 above
		
		tx.rotate(Math.toRadians(rotation), IMG_PIXELS/2, IMG_PIXELS/2);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Tower.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	//gets the center of the tower
	protected Point getCenter() {
		int centerX = (int)(x + width/2.0);
		int centerY = (int)(y + height/2.0);	
		return new Point(centerX, centerY);
	}
	
	public void fireEnemies(ArrayList<SlimeEnemy> enemies) {
		
		if(enemy != null) {//if the enemy exists continue shooting
			if (cannonPosition == 0) {
			//	Music sound = new Music("/imgs/towernoises.wav", false);//play the sound when it shoots
			//	sound.play();
				cannonPosition = cannonPositionsMax/5; //start a bit further than the middle of tower
			} else if(cannonPosition < cannonPositionsMax) {
				cannonPosition++; //move cannon ball closer to the enemy
			} else if (cannonPosition == cannonPositionsMax) { //hit 
				enemy.removeHealth();
				cannonPosition = 0;
				enemy = null;
			}
		} else {//find another enemy to shoot at
			enemy = enemyToShootAt(enemies);
			cannonPosition = 0;
			rotateCannon();
		}
		
	}
	
	//rotates the cannon to the picked enemy
	private void rotateCannon() {
		if(enemy != null) {//if the enemy exists
			Point towerCenter = getCenter(); //the point of the center of the tower
			Point enemyCenter = new Point(
					enemy.getX()+width/2, enemy.getY() + height/2);//the point of the center of the enemy
			int newRotation = (int) angle(towerCenter, enemyCenter);
			if (rotation != newRotation) {//if the rotation is not the same as the new rotation
				rotation = newRotation;
				initAffineTransform(x, y);
				//System.out.println("rotation = " + rotation + 
			     //		"; enemy Center = " + enemyCenter);
			}
		}
	}
	
	//returns an enemy with an input of a list of enemies the cannon should shoot at
	private SlimeEnemy enemyToShootAt(ArrayList<SlimeEnemy> enemies) {
		double rangeDistance = (range+1)*width;//range in pixels
		Point towerCenter = getCenter();
		
		for(int i = 0; i < enemies.size(); i++) {//iterate through the list to find the best enemy
			Point enemyCenter = new Point(
					enemies.get(i).getX()+width/2, enemies.get(i).getY() + height/2);
			
			if(distance(towerCenter,enemyCenter) < rangeDistance) {//if the distance is less than the range
				//System.out.println("enemy " + i + " in range");
				return enemies.get(i);
			}
		}
		return null;
	}
	
	//calculate the distance between 2 points
	private static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2) +
				Math.pow(p1.getY()-p2.getY(), 2));
	}
	
	//calculate the angle between the 2 points
	private static double angle(Point p1, Point p2) {
		double deltaX = p1.getX()-p2.getX();
		double deltaY = p1.getY()-p2.getY();
		
		double degrees = Math.toDegrees(Math.atan(Math.abs(deltaX/deltaY)));
		if (deltaX >=0) {
			if (deltaY >= 0) {
				degrees = degrees * -1;//point 2 is left top
			}else {
				degrees = degrees + 180;//point 2 is left bottom
			}
		}else {
			if (deltaY >= 0) { //point 2 is top right
				//nothing to adjust
			}else {
				degrees = 180 - degrees;//point 2 is bottom right
			}
		}
		
		return degrees;
	}
	
	//to sety the new location of the tower
	public void setLocation(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	//if you want to see the range of the tower
	public void setDisplayRange(boolean c) {
		displayRange = c;
	}
	
	public String toString1() {
		String type = towerType;
		return "type = " + type + ", cost = " + cost;
	}
	
	public String toString2() {
		String type = towerType;
		return "range = " + range +
				", speed = " + speed;
	}
}
