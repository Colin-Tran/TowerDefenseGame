import java.util.ArrayList;
public class GameComponents {
	private Background background;
	private ArrayList<Tower> towers;
	private ArrayList<SlimeEnemy> enemies;
	
	public GameComponents() {
		towers = new ArrayList<Tower>();
		enemies = new ArrayList<SlimeEnemy>();
	}
	
	//used for getting the background of the game
	public Background getBackground() {
		if(background == null) {
			background = new Background(0, 0);
		}
		return background;
	}
	
	//returns a list of towers
	public ArrayList<Tower> getTowers() {
		return towers;
	}
	
	public void addTower(Tower tower) {
		towers.add(tower);
	}
	
	//gets a list of enemies
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
