import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public abstract class Tower { //you can not instantiate Tower class
	
	//attributes
	private AffineTransform tx;
	
	protected String towerType; //name of the tower type
	
	protected double cost; //how much it costs
	protected int damage; //the damage it inflicts on enemy
	protected int range; //number of tiles it can attack enemy
	protected double speed; //interval between 2 consecutive attacks
	
	//drawing info
	private Image img; //the image of the tower
	protected int x, y; //x and y of the left top corner of the tower
	protected int width, height; //width and height of the tower
	protected int rotation; //angle for direction of the attack
	
	static protected int IMG_PIXELS = 25; //size of the tower pngs
	
	public Tower(String towerType, double cost, int damage, 
			int range, double speed, 
			int x, int y, int width, int height, 
			String imageFile) {
		
		this.towerType = towerType;
		this.cost = cost;
		this.damage = damage;
		this.range = range;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		rotation = (int)(360*Math.random()); //initial rotation
		
		img = getImage(imageFile); //load the image for Tower
		initAffineTransform(x, y); //initialize the location, size and rotation of the image								//use your variables
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g.drawRect(x, y, width, height); //shows the borders of the tower
		g2.drawImage(img, tx, null);
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
	
	protected Point getCenter() {
		int centerX = (int)(x + width/2.0);
		int centerY = (int)(y + height/2.0);	
		return new Point(centerX, centerY);
	}
	

}
