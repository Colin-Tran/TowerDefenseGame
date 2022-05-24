
public class Game {
	private int mode;
	private int levelNumber;
	private Player player;
	private Level level;
	
	public static Game instance = new Game();
	
	public Game() {
		player = new Player();
		levelNumber = 1;
		mode = 0;
		level = new Level(mode, levelNumber);
	}
	
	public int getGameMode() {
		return mode;
	}
	
	public void setGameMode(int n) {
		mode = n;
	}
	
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level newLevel) {
		this.level = newLevel;
	}
	
	public boolean advanceLevel() {
		levelNumber++;
		level = new Level(mode, levelNumber);
		return true;
		
	}
	
	
	public boolean isGameOver() {
		if(player.getLives() <= 0 ) {
			return true;
		}
		return false;
	}
	
	public void hitEnemy(SlimeEnemy enemy) {
		
	}
	
	public Player getPlayer() {
		return player;
	}
}
