import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;
import javax.swing.JButton;

public class boardTiles{
	private int width;
	private int height;
	private String fileName; 
	private JButton[][] t = new JButton[5][10];
	//add location attributes
	
	private Image img; 	
	private AffineTransform tx;
	public int x, y;
	
	public boardTiles(int newX, int newY) {
		img = getImage(""); //load the image for Tree
	
		//initialize the location of the image
		x = newX;
		y = newY;
		
		height = 100;
		width = 100;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 
		//use your variables
		
		//find out how to initialize all vars to this
		for(int r = 0; r <t.length; r++) {
			for(int c = 0; c < t[r].length; c++) {
		t[r][c] = new JButton();
		t[r][c].setSize(width, height);
		}		
		}
		
	}
	
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actually picture location
		update();
		
		
		for(int row = 0; row < t.length; row++) {
			for(int col = 0; col <t[row].length; col++) {
				
			}
		}
		
		g2.drawImage(img, tx, null);
		
		

	}
	/* update the picture variable location */
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(0.8,0.8);
	
		
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
	
}