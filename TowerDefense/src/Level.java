import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

public class Level {
	
	private int mode; //the game mode: easy, med, hard
	private int level; //1,2,3,etc. 
	private int maxNumberOfEnemies; //each level has a number of enemies that get created
	private int enemyCounter; //number of enemies created
	private boolean isStarted; //if the playing at this level started yet
	private long lastSpawnTime; //time when the enemy was last spawned


	public Level(int mode, int level) {
		this.mode = mode;
		this.level = level;
		maxNumberOfEnemies = 10 * level;	
		enemyCounter = 0;
		isStarted = false;
		lastSpawnTime = 0;
	}
	
	public int getMaxNumEnemies() {
		return maxNumberOfEnemies;
	}
	
	private double secondsSinceLastSpawn() {
		return (System.currentTimeMillis()-lastSpawnTime)/1000;
	}
	
	public SlimeEnemy spawnEnemy(int x, int y, Object enemyPath)  {
		if (enemyCounter < maxNumberOfEnemies &&
				secondsSinceLastSpawn() > 2) {//wait 2 seconds between spawning a new enemy
			
			enemyCounter ++;
			lastSpawnTime = System.currentTimeMillis();
			System.out.println("spawning" + enemyCounter);
			
			File numberSpawned = new File("numEnemies.txt");
			{
			 try {
				 FileWriter writer = new FileWriter(numberSpawned);
				 writer.write(enemyCounter);
				 writer.close();
			    } catch(Exception e) {
			        System.out.println("something is wrong with file!");
			    }
			}
			return new SlimeEnemy(0, 330);
		}
		//System.out.println("not spawning");
	/*	int counter = 0;
		Scanner obj = new Scanner(System.in);
		if(obj.hasNext("spawning")) {
			counter++;
		}
		System.out.println(counter);*/
		
		return null; //no more enemies can be spawn at this point
	}

	public int getLevelCounter() {
		return level;
	}
	
	
	
}

