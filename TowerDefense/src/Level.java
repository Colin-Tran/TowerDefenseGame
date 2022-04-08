
public class Level {
	private int mode;
	private int level;
	private int maxNumberOfEnemies;
	private int durationInSeconds;
	
	private long startTime;
	private int enemyCounter;
	private boolean isStarted;
	
	public Level(int mode, int level) {
		this.mode = mode;
		this.level = level;
		maxNumberOfEnemies = 5 * level;	
		durationInSeconds = 20 + level * 3;
		startTime = 0;
		enemyCounter = 0;
		isStarted = false;
	}
	
	public void startLevel() {
		if(!isStarted) {
			startTime = System.currentTimeMillis();
			isStarted = true;
		}
	}
	
	public int remainingTimeinSeconds() {
		int n = (int) (startTime/1000 + 
				durationInSeconds - System.currentTimeMillis()/1000);
		if(n > 0) {
			return n;
		}
		return 0;
	}
	
	public SlimeEnemy spawnEnemy(int x, int y, Object enemyPath)  {
		if (enemyCounter < maxNumberOfEnemies && remainingTimeinSeconds() > 0) {
			enemyCounter ++;
			return new SlimeEnemy(0, 330);
		}
		return null; //no more enemies can be spawn at this point
	}
}
