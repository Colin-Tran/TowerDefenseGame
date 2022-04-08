import java.util.ArrayList;
public class GameComponents {
	private Background background;
	private ArrayList<Tower> towerTypes;
	private ArrayList<Tower> towers;
	private ArrayList<SlimeEnemy> enemies;
	
	public GameComponents() {
		
	}
	
	public Background getBackground() {
		return background;
	}
	
	public ArrayList<Tower> getTowerTypes(int x, int y, int w, int h) {
		return towerTypes;
	}
	
	public ArrayList<Tower> getTowers() {
		return towers;
	}
	
	public void addTower(Tower tower) {
		
	}
	
	public ArrayList<SlimeEnemy> getEnemies() {
		return enemies;
	}
}
