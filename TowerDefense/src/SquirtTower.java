import java.awt.Color;
import java.awt.Graphics;

public class SquirtTower extends Tower {
	//extending the original tower class and reusing the logic there
	
	public SquirtTower(
			int x, int y, int width, int height) {
		
		super ("Squirt Tower", 40, 1.5, 1.5,
		x,y,width, height, "/imgs/squirttower.png");
	}

	public void paint(Graphics g) {
		//draw the circle for the range when the display range is true
		//and changing the display range for this specific tower 
		if(displayRange) {
			g.setColor(Color.GREEN);
			g.drawOval((int) (x-range*width), (int) (y-range*height), 
				(int) (width*(2*range+1)), (int) (height*(2*range+1)));
			g.setColor(new Color(64,64,64,64));
			g.fillOval((int) (x-range*width), (int) (y-range*height), 
				(int) (width*(2*range+1)), (int) (height*(2*range+1)));
		
		}
		super.paint(g); //adds the image
	}
}
