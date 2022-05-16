import java.awt.Color;
import java.awt.Graphics;

public class TowerDisplay {
	private int x;
	private int y;
	private Tower tower1;
	private Tower tower2;
	
	public TowerDisplay(int x, int y) {
		tower1 = new PelletTower(x+10, y+10, 50, 50);
		tower2 = new SquirtTower(x+10, y+70, 50, 50);
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x,y,240,310);
		tower1.paint(g);
		tower2.paint(g);
	}
}
