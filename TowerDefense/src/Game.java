
public class Game {
	private int levelNumber;
	private Player player;
	private Level level;
	
	public static Game instance = new Game();//can be called by any classes
	
	public Game() {
		player = new Player();
		levelNumber = 1;
		level = new Level(levelNumber);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level newLevel) {
		this.level = newLevel;
	}
	
	public boolean advanceLevel() {
		levelNumber++;
		level = new Level(levelNumber);
		return true;
		
	}
	
	public boolean isGameOver() {
		if(player.getLives() <= 0 ) {
			return true;
		}
		return false;
	}
	
	public Player getPlayer() {
		return player;
	}
}
