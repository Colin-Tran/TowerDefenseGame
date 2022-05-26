import java.io.File;  // Import the File class
import java.io.FileWriter;

//holds information about the level and special behavior per level
public class Level {
	
	private int level; //1,2,3,etc. 
	private int maxNumberOfEnemies; //each level has a number of enemies that get created
	private int enemyCounter; //number of enemies created
	private long lastSpawnTime; //time when the enemy was last spawned


	public Level(int level) {
		this.level = level;
		maxNumberOfEnemies = 5 + 5 * level;	
		enemyCounter = 0;
		lastSpawnTime = 0;
	}
	
	//gets the maxiumum number of enemies
	public int getMaxNumEnemies() {
		return maxNumberOfEnemies;
	}
	
	//returns the seconds from the last spawn time
	private double secondsSinceLastSpawn() {
		return (System.currentTimeMillis()-lastSpawnTime)/1000;
	}
	
	private void writeFile(File f) {
		 try {
			 FileWriter writer = new FileWriter(f);
			 writer.write(enemyCounter);
			 writer.close();
		 } catch(Exception e) {
		     System.out.println("something is wrong with file!");
		 }
	}
	//spawn an an enemy and it will return a slime enemy at an x and y
	public SlimeEnemy spawnEnemy(int x, int y, Object enemyPath)  {
		if (enemyCounter < maxNumberOfEnemies &&
				secondsSinceLastSpawn() > 1.5) {//wait 2 seconds between spawning a new enemy
			
			enemyCounter ++;
			lastSpawnTime = System.currentTimeMillis();
			//System.out.println("spawning" + enemyCounter);
			
			File numberSpawned = new File("numEnemies.txt");
			writeFile(numberSpawned);
			
			if(level == 1) {
				return new SlimeEnemy(0, 330);
			}else if(level == 2) {
				if(enemyCounter%2 == 0)	{
					return new SuperSlimeEnemy(0, 330);
				}else {
					return new SlimeEnemy(0, 330);
				}
			}else {
				return new SuperSlimeEnemy(0, 330);
			}
		
		}
		return null; //no more enemies can be spawn at this point
	}

	public int getLevelCounter() {
		return level;
	}
}

