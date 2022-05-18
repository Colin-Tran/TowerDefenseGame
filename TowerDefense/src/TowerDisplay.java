import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TowerDisplay {
	private int x;
	private int y;
	private Tower tower1;
	private Tower tower2;
	
	public TowerDisplay(int x, int y) {
		tower1 = new PelletTower(x+10, y+10, 50, 50);
		tower2 = new SquirtTower(x+10, y+70, 50, 50);
		tower1.setDisplayRange(false);
		tower2.setDisplayRange(false);
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x,y,240,310);
		tower1.paint(g);
		tower2.paint(g);
		
		g.setColor(Color.green);
		Font stringFont = new Font( "SansSerif", Font.BOLD, 12);
		g.setFont(stringFont);
		g.drawString(tower1.toString1(), x+60, y+20);
		g.drawString(tower1.toString2(), x+60, y+30);
		g.drawString(tower2.toString1(), x+60, y+80);
		g.drawString(tower2.toString2(), x+60, y+90);
		g.drawString("press 1 + left click to place it down", x+60, y+45);
		g.drawString("press 2 and click to put down", x+60, y+105);
		
		//Color color = new Color(255, 153, 51); //Sets text to orange, also used to see hit boxes. Is movable to different parts of code to hide/show hit boxes.
		//g.setColor(color);
	}
}
