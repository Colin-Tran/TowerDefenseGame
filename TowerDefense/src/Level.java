
public class Level {
	private int mode; //the game mode: easy, med, hard
	private int level; //1,2,3,etc. 
	private int maxNumberOfEnemies; //each level has a number of enemies that get created
	private int durationInSeconds; //time for the level before it runs out
	
	private long startTime; //when the level gets started
	private int enemyCounter; //number of enemies created
	private boolean isStarted; //if the playing at this level started yet
	
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
