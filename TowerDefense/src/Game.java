
public class Game {
	private int mode;
	private boolean isStarted;
	private int level = 1;
	private Player player;
	private Level theLevel = null;
	public static Game instance;
	
	public Game() {
		instance = new Game();
	}
	
	public int getGameMode() {
		return mode;
	}
	
	public void setGamemode(int n) {
		mode = n;
	}
	
	public void startGame() {
		isStarted = true;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public Level getlevel() {
		return theLevel;
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
