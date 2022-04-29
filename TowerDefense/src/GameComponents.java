import java.util.ArrayList;
public class GameComponents {
	private Background background;
	private ArrayList<Tower> towerTypes;
	private ArrayList<Tower> towers;
	private ArrayList<SlimeEnemy> enemies;
	
	public GameComponents() {
		towers = new ArrayList<Tower>();
		towerTypes = new ArrayList<Tower>();
		enemies = new ArrayList<SlimeEnemy>();
	}
	
	public Background getBackground() {
		if(background == null) {
			background = new Background(0, 0);
		}
		return background;
	}
	
	public ArrayList<Tower> getTowerTypes(int x, int y, int w, int h) {
		if(towerTypes.size() == 0) {
			towerTypes.add(new PelletTower(x, y, w, h));
			//towerTypes.add(new PelletTower(x+w, y, w, h));
		}
		return towerTypes;
	}
	
	public ArrayList<Tower> getTowers() {
		return towers;
	}
	
	public void addTower(Tower tower) {
		towers.add(tower);
	}
	
	public ArrayList<SlimeEnemy> getEnemies(int x, int y) {
		SlimeEnemy temp = Game.instance.getLevel().spawnEnemy(x, y, null);
		if(temp != null) {
			enemies.add(temp);
		}
		
		//remove enemies that escaped or have no health left
		for(int i = 0; i < enemies.size(); i++) {
			if(!enemies.get(i).isAlive()) {
				enemies.remove(i);
				i--;
			}
		}
		return enemies;
	}
}
