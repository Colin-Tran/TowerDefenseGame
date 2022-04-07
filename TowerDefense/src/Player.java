
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
	public void setLives(int amount) {
		lives = amount;
	}
	public void setMoney(int amount) {
		money = amount;
	}
	public void setScore(int amount) {
		score = amount;
	}
}
