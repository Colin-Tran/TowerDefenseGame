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
		Font font1 = new Font("sans serif", 0, 20);
		g.setFont(font1);
		g.setColor(Color.green);
		g.drawString("pellet tower", x+60, y+33);
		Font font2 = new Font("sans serif", 0, 10);
		g.setFont(font2);
		g.drawString("press 1 and mouse click to place it down", x+60, y+47);
		Font font3 = new Font("sans serif", 0, 20);
		g.setFont(font3);
		g.drawString("squirt tower",x+60, y+83);
		Font font4 = new Font("sans serif", 0, 10);
		g.setFont(font4);
		g.drawString("press 2 and mouse click to", x+60, y+95);
		g.drawString("place it on the screen", x+60, y+107);
	}
}
