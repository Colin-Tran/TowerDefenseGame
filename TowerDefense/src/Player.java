
public class Player {
	private int lives;//how many lives a player has at the beginning and during the game. 
	private int score;//how many enemies are killed
	
	public Player() {
		lives = 20;
		score = 0;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void loseLife() {
		lives--;
	}
	
	public void increaseScore(int amount) {
		score += amount;
	}
}
