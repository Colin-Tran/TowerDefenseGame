
public class Player {
	private int lives;
	private int score;
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
