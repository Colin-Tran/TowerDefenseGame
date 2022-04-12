
public class Game {
	private int mode;
	private boolean isStarted;
	private int levelNumber = 1;
	private Player player;
	private Level level = null;
	
	public static Game instance = new Game();
	
	public Game() {
		
	}
	
	public int getGameMode() {
		return mode;
	}
	
	public void setGameMode(int n) {
		mode = n;
	}
	
	public void startGame() {
		level = new Level(mode, levelNumber);
		isStarted = true;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public boolean advanceLevel() {
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
	public void enemyEscaped(SlimeEnemy enemy) {
		player.setLives(player.getLives()-1);//decremts the lives
	}
}
