
public class Player {
	private int lives;
	private int money;
	private int score;
	public Player() {
		lives = 20;
		money = 75;
		score = 0;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getScore() {
		return score;
	}
	
	public void loseLife() {
		lives--;
	}
	
	public void payMoney(int amount) {
		money -= amount;
	}
	
	public void increaseScore(int amount) {
		score += amount;
	}
}
