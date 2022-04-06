import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class PelletTower extends Tower {
	
	public PelletTower(
			int x, int y, int width, int height) {
		
		super ("Pellet Tower", 10, 2, 1, 1,
		x,y,width, height, "/imgs/pellettower.png");
	}

	public void paint(Graphics g) {
		
		//draw the circle for the range
		g.setColor(Color.GRAY);
		Point center = super.getCenter();
		g.drawOval((int)(center.getX()-range*width), 
				(int)(center.getY()-range*height), 
				(range+2)*width, (range+2)*height);
		g.setColor(new Color(64,64,64,64));
		g.fillOval((int)(center.getX()-range*width), 
				(int)(center.getY()-range*height), 
				(range+2)*width, (range+2)*height);
		
		super.paint(g); //adds the image
	
	}
}