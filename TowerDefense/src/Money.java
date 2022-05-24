import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Money {
int x = 925;
int y = -45;
int total; //initialize cost of money
int max = 300;
static int pelTowerCost = 30;
static int squTowerCost = 40;
private Image img; 	
private AffineTransform tx;

public Money(int ncost) {
	img = getImage("/imgs/wolverine bucks.png"); //load the image for money (wolverine buck)
total = ncost;


tx = AffineTransform.getTranslateInstance(x, y );
init(x, y); 
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
	//method to buy the Pellet Tower Variant as well as remove the specific cost from the total balance
	public void buyPelTower() {
		total -= pelTowerCost; //cost of tower is 50
	}
	
	//method to buy the Squirt Tower Variant as well as remove the specific cost from the total balance
	public void buySquTower() {
		total -= squTowerCost; //cost of tower is 50
	}
	
	//returning the total amount of money left 
	public int getTotal() {
		return total;
	}
	
	//Logic to help add money after a round ends
	public void setMoney(int setMoney) {
		total = setMoney;
	}
}
