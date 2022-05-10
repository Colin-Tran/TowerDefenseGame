
public class Level {
	private int mode; //the game mode: easy, med, hard
	private int level; //1,2,3,etc. 
	private int maxNumberOfEnemies; //each level has a number of enemies that get created
	private int durationInSeconds; //time for the level before it runs out
	
	private long startTime; //when the level gets started
	private int enemyCounter; //number of enemies created
	private boolean isStarted; //if the playing at this level started yet
	
	private long lastSpawnTime; //time when the enemy was last spawned
	
	public Level(int mode, int level) {
		this.mode = mode;
		this.level = level;
		maxNumberOfEnemies = 25 * level;	
		durationInSeconds = 10020 + level * 3;
		startTime = 0;
		enemyCounter = 0;
		isStarted = false;
		lastSpawnTime = 0;
	}
	
	
	
	public void startEnemySpawning() {
		if(!isStarted) {
			startTime = System.currentTimeMillis();
			isStarted = true;
		}
	}
	public void setTime(int durationS) {
		durationInSeconds = durationS;
	}
	
	public int remainingTimeinSeconds() {
		int n = (int) (startTime/1000 + 
				durationInSeconds - System.currentTimeMillis()/1000);
		if(n > 0) {
			return n;
		}
		return 0;
	}
	
	public int getMaxNumEnemies() {
		return maxNumberOfEnemies;
	}
	
	private double secondsSinceLastSpawn() {
		return (System.currentTimeMillis()-lastSpawnTime)/1000;
	}
	
	public SlimeEnemy spawnEnemy(int x, int y, Object enemyPath)  {
		if (enemyCounter < maxNumberOfEnemies && remainingTimeinSeconds() > 0 &&
				secondsSinceLastSpawn() > 2) {//wait 2 seconds between spawning a new enemy
			
			enemyCounter ++;
			lastSpawnTime = System.currentTimeMillis();
			System.out.println("spawning" + enemyCounter);
			return new SlimeEnemy(0, 330);
		}
		//System.out.println("not spawning");
		
		return null; //no more enemies can be spawn at this point
	}
}
