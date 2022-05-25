import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	private int levelCounter = 1;
	
	//initialization of all object types
	private static SlimeEnemy[] slimes = new SlimeEnemy[0];  //enemies
	private GameComponents components = new GameComponents(); //background, enemies, towers
	private Money wallet = new Money(60); //start out with 60 bucks
	private TowerDisplay display = new TowerDisplay(1025,100); //info about cost of towers
	
	public void paint(Graphics g) {
		super.paintComponent(g);
	
		//paint background
		Background bg = components.getBackground();
		bg.paint(g);
		
		//retrieve enemies to display
		ArrayList<SlimeEnemy> enemies = new ArrayList<SlimeEnemy>();	
		if (!Game.instance.isGameOver()) {
			enemies = components.getEnemies(0, 330); //will spawn enemies
		}
		slimes = convertToArray(enemies);
		
		//set font for text shown on the board
		Font myFont = new Font("Serif", Font.BOLD, 30);
		g.setFont(myFont);
		
		//LOGIC FOR ESCAPING SLIMES AND REMOVING LIVES
		for(int i = 0; i < slimes.length; i++) {
			if(slimes[i].getX() >= 930) { //if out of screen, don't paint enemies
				slimes[i].hasEscaped();
				Game.instance.getPlayer().loseLife(); //if slime leaves border make player lose one life
				
			}else {
				slimes[i].paint(g);
			}
		}
		
		//paint wallet image
		wallet.paint(g);
		
		//displaying lives and the score
		g.setColor(Color.orange);
		g.drawString("Lives = " + Game.instance.getPlayer().getLives(), 50, 50);
		g.drawString("Score (enemies killed) = " + Game.instance.getPlayer().getScore(), 250, 50);
		
		//handle end game
		if(Game.instance.isGameOver()) {
			for(int i = 0; i <slimes.length; i++) {
				slimes[i].resetStop();
			}
			
			Color c = new Color(255, 255, 255);
			g.setColor(c);
			Font s = new Font("Serif", Font.BOLD, 30);
			g.setFont(s);
			g.drawString("GAME OVER, YOU HAVE ZERO LIVES LEFT", 200, 250);
			
			components.getTowers().clear();
		}
		
		//next level logic
		int counter = 0; //counts how many enemies are still alive
		for(int i = 0; i < slimes.length; i++) {
			if(slimes[i].isAlive() == false) {
				counter++;
			}
		}
		if(counter >= slimes.length && !Game.instance.isGameOver()) {
			Game.instance.advanceLevel(); //move onto next level if all slimes are dead
			int moneyCounter = 0 ;
			moneyCounter += 30;
			moneyCounter += wallet.getTotal();
			wallet.setMoney(moneyCounter);
		}
	
		//displaying level, enemies to kill for the current level, and current money
		levelCounter = Game.instance.getLevel().getLevelCounter();
		g.drawString("LEVEL: " + levelCounter, 0, 450);
		g.drawString("(enemies to kill: " + 
				Game.instance.getLevel().getMaxNumEnemies() + ")", 144, 450);
		g.drawString("Money: " + wallet.getTotal(), 1100, 450);

		//set text to orange
		Color color = new Color(255, 153, 51); //Sets text to orange, also used to see hit boxes. Is movable to different parts of code to hide/show hit boxes.
		g.setColor(color);
		Font stringFont = new Font( "SansSerif", Font.BOLD, 40 );
		g.setFont(stringFont);
		
		moveSlimeEnemies();
		
		//tower methods to fire at enemies and paint
		for (Tower tower: components.getTowers()) {
			tower.fireEnemies(enemies);
			tower.paint(g);
		}
		
		display.paint(g);
	}
		
	private void moveSlimeEnemies() {
		//movement pathway for all slime enemies
		for(int i = 0; i < slimes.length; i++) { 
				
			int j = 1;
			j += levelCounter; //Change the speed at which they are moving left/right/up/down dependent on level.
		
			if(slimes[i].getX() <= 100 && slimes[i].getY() < 500) { //move right at beginning
				slimes[i].moveRight(j, 0);
			}
			
			//when passes first tile move up pathway
			if (slimes[i].getX() < 200 && slimes[i].getX() > 105 && slimes[i].getY() <500) { 
				slimes[i].moveUp(0, -j);
			}
			
			//MOVE RIGHT PATHWAY
			if(slimes[i].getY() < 130 && slimes[i].getX() < 300) {
				slimes[i].moveRight(j, 0);
			}
			
			//MOVE DOWN PATHWAY
			if(slimes[i].getX() < 500 && slimes[i].getX() > 405 && slimes[i].getY() < 300) {
				slimes[i].moveDown(0, j);
			}
				
			//MOVE RIGHT PATHWAY MIDPOINT OF PATH
			if(slimes[i].getX() < 500 && slimes[i].getX() > 400 && slimes[i].getY() >420) {
				slimes[i].moveRight(j, 0);
			}
			
			//MOVE UP PATH
			if(slimes[i].getY() > 400 && slimes[i].getX() < 750 && slimes[i].getX() >610) {
				slimes[i].moveUp(0, -j);
			}
			
			//MOVE RIGHT PATH
			if(slimes[i].getY() < 30 && slimes[i].getX() < 750 && slimes[i].getX() > 610) {
				slimes[i].moveRight(j, 0);
			}

			//MOVE DOWN PATH
			
			if(slimes[i].getY() < 30 && slimes[i].getX() < 900 && slimes[i].getX() > 805) {
				slimes[i].moveDown(0, j);
			}
			
			//FINAL MOVE RIGHT
			
			if(slimes[i].getX() < 900 && slimes[i].getX() > 805 && slimes[i].getY() < 350 && slimes[i].getY() > 240) {
				slimes[i].moveRight(j, 0);
			}
		} //close for loop
		
	}
	
	//converting slime enemy array list into a array for ease of access
	private static SlimeEnemy[] convertToArray(ArrayList<SlimeEnemy> enemies) {
		SlimeEnemy[] array = new SlimeEnemy[enemies.size()];
		for(int i = 0; i < enemies.size(); i++) {
			array[i] = enemies.get(i);
		}
		return array;
	}
	

	public static void main(String[] arg) {
		Frame f = new Frame();
    }
		 
	
	public Frame() { 
		initializeGame();

		JFrame f = new JFrame("Tower Defense");
		f.setSize(new Dimension(1300, 500));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	
	}
	
	
	
	private void initializeGame() {
		//Starting up the game by initializing the defaults for everything needed. 
		//Initialized one of each tower type as well as their ranges and displayed it.
		
		Tower tower1 = new PelletTower(210, 300, 75, 75);
		Tower tower2 = new SquirtTower(710, 220, 75, 75);
				tower1.setDisplayRange(true);
		tower2.setDisplayRange(true);
		components.addTower(tower1);
		components.addTower(tower2);
	}
	
	//for key presses later on, helps us differentiate between buying pellet or squirt towers (the 2 different tower types).
	static int tank = 0;

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		//BUYING TOWERS LOGIC
		for(Tower tower: components.getTowers()) {
			if(arg0.getX() < 1000 && arg0.getY() < 1000 && wallet.getTotal() >= Money.pelTowerCost && tank == 0) {
				components.getTowers().add(new PelletTower(arg0.getX()-25, arg0.getY()-50, 75, 75));
				wallet.buyPelTower();
				repaint();
			}
			if(arg0.getX() < 1000 && arg0.getY() < 1000 && wallet.getTotal() >= Money.squTowerCost && tank == 1) {
				components.getTowers().add(new SquirtTower(arg0.getX()-25, arg0.getY()-50, 75, 75));
				wallet.buySquTower();
				repaint();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();	
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//DELETING TOWERS LOGIC
		if(arg0.getKeyCode() == 8) {
			for(Tower tower: components.getTowers()) {
				for(int i = 0; i < components.getTowers().size()-1 ; i++) { //always leaves 1 tower so can place more
					//components.getTowers().get(i).setLocation(-1000, -1000);
					components.getTowers().remove(i);
				}
			}
		}
		
		//USED CASE SWITCH FOR KEY PRESS CODE. THIS HELPS US DECIDE WHAT TOWER WE ARE BUYING
		switch(arg0.getKeyCode()) {
		case 49:
			tank = 0;
			break;
		case 50:
			tank = 1; 
			break;
		default:
			tank = 0;
		}
		System.out.println(arg0.getKeyCode());	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}

