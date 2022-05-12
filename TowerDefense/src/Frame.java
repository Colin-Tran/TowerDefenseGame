import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	SlimeEnemy[] slimes = new SlimeEnemy[0]; 
	Level lvl2 = new Level(2, 2);
	
	GameComponents components = new GameComponents();
	Money wallet = new Money(100); //start out with 100 bucks
	
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		
	//	System.out.println(str);
		
		
		//get the components, based on the current Game mode and level
		Background bg = components.getBackground();
		
		ArrayList<SlimeEnemy> enemies = components.getEnemies(0, 330);
		slimes = convertToArray(enemies);
		bg.paint(g);
		g.setColor(Color.orange);
		//g.drawString("Lives =" + pl.getLives(), 50, 50);
		Font myFont = new Font("Serif", Font.BOLD, 30);
		g.setFont(myFont);
		for(int i = 0; i < slimes.length; i++) {
			if(slimes[i].getX() >= 930) { //if out of screen, don't paint enemies
				slimes[i].hasEscaped();
				Game.instance.getPlayer().loseLife();
				
			}else {
				slimes[i].paint(g);
			}
		}
		wallet.paint(g);
		
		g.drawString("Lives = " + Game.instance.getPlayer().getLives(), 50, 50);
		//game over
		if(Game.instance.getPlayer().getLives() < 0) {
			for(int i = 0; i <slimes.length; i++) {
				slimes[i].resetStop();
				components.getTowers().remove(i);
			}
		
					}
		
		int levelCounter = 1;
		
		//next level
				int numEnemy = Game.instance.getLevel().getMaxNumEnemies() -50;
				
				if(Game.instance.getPlayer().getLives() >= 1 && components.getEnemies(0, 330).size() < 1) {
					/*Game.instance.advanceLevel();
				//	for(int i = 0; i < 5; i++) {
					//int levelTime = i *1000;
				//}
				
				if(Game.instance.advanceLevel()) {*/
					
					Game.instance.setLevel(lvl2);
					Game.instance.getLevel().startEnemySpawning();
					Game.instance.getLevel().spawnEnemy(0, 330, null);
					levelCounter++;
					
				
					
				}
				
				g.drawString("LEVEL: " + levelCounter, 0, 450);
				int counter = 0;
				for(int i = 0; i < slimes.length; i++) {
					if(slimes[i].isAlive() == false) {
						counter++;
					}
				}
		
		//move onto next level if all slimes are dead
		if(counter >= slimes.length) {
			Game.instance.advanceLevel();
	
		}
	
		g.drawString("Money: " + wallet.getTotal(), 1100, 450);

		Color color = new Color(255, 153, 51); //Sets text to orange, also used to see hit boxes. Is movable to different parts of code to hide/show hit boxes.
		g.setColor(color);
		Font stringFont = new Font( "SansSerif", Font.BOLD, 40 );
		g.setFont(stringFont);
		
		//movement
		for(int i = 0; i < slimes.length; i++) { 
			if(slimes[i].getX() <= 100 && slimes[i].getY() < 500) { //move right at beginning
				slimes[i].moveRight();
			}
			
			//when passes first tile move up pathway
			if (slimes[i].getX() < 200 && slimes[i].getX() > 105 && slimes[i].getY() <500) { 
				slimes[i].moveUp();
			}
			
			//MOVE RIGHT PATHWAY
			if(slimes[i].getY() < 130 && slimes[i].getX() < 300) {
				slimes[i].moveRight();
			}
			
			//MOVE DOWN PATHWAY
			if(slimes[i].getX() < 500 && slimes[i].getX() > 405 && slimes[i].getY() < 300) {
				slimes[i].moveDown();
			}
				
			//MOVE RIGHT PATHWAY MIDPOINT OF PATH
			if(slimes[i].getX() < 500 && slimes[i].getX() > 400 && slimes[i].getY() >420) {
				slimes[i].moveRight();
			}
			
			//MOVE UP PATH
			if(slimes[i].getY() > 400 && slimes[i].getX() < 750 && slimes[i].getX() >610) {
				slimes[i].moveUp();
			}
			
			//MOVE RIGHT PATH
			if(slimes[i].getY() < 30 && slimes[i].getX() < 750 && slimes[i].getX() > 610) {
				slimes[i].moveRight();
			}

			//MOVE DOWN PATH
			
			if(slimes[i].getY() < 30 && slimes[i].getX() < 900 && slimes[i].getX() > 805) {
				slimes[i].moveDown();
			}
			
			//FINAL MOVE RIGHT
			
			if(slimes[i].getX() < 900 && slimes[i].getX() > 805 && slimes[i].getY() < 350 && slimes[i].getY() > 240) {
				slimes[i].moveRight();
			}
			
			
			
			//RESET 
			
			//if( slimes[i].getX()> 1000 && slimes[i].getY() <350 ) {
				//slimes[i].reset();
			//}
			
		} //close for loop
		
		for (Tower tower: components.getTowers()) {
			tower.fireEnemies(enemies);
			tower.paint(g);
		}
		
		
		
	}
		
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
		Game.instance.getLevel().startEnemySpawning(); //level
		components.addTower(new SquirtTower(730, 220, 75, 75));
		components.addTower(new PelletTower(230, 300, 75, 75));
	}
	
	int tank = 0;

	
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
		for(Tower tower: components.getTowers()) {
			if(arg0.getX() < 1500 && arg0.getY() < 1000 && wallet.getTotal() >= Money.pelTowerCost && tank == 0) {
				components.getTowers().add(new PelletTower(arg0.getX()-25, arg0.getY()-50, 75, 75));
				wallet.buyPelTower();
				repaint();
			}
			if(arg0.getX() < 1500 && arg0.getY() < 1000 && wallet.getTotal() >= Money.squTowerCost && tank == 1) {
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
		if(arg0.getKeyCode() == 8) {
			for(Tower tower: components.getTowers()) {
				for(int i = 0; i < components.getTowers().size()-1 ; i++) { //always leaves 1 tower so can place more
				//components.getTowers().get(i).setLocation(-1000, -1000);
				components.getTowers().remove(i);

				}
			}
			
		}
		if(arg0.getKeyCode() == 49) {
			tank = 0;
		}else if(arg0.getKeyCode() == 50) {
			tank = 1;
		}
		/*
		NEED TO FIND OUT HOW TO REMOVE PLACED TOWERS
		*/
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

