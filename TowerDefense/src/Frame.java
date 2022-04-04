import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	public int finalScore = 0;
	
	//CREATE THE OBJECT (STEP 1)
	Background 	bg 	= new Background(0, 0);
//	SlimeEnemy slime = new SlimeEnemy(100,250);
	SlimeEnemy[] slimes = new SlimeEnemy[50];
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.drawRect(mainChar.x, mainChar.y, mainChar.getWidth(), mainChar.getHeight());
		//g.drawRect(asteroidL.x, asteroidL.y, asteroidL.getWidth(), asteroidL.getHeight());
		
		//p5 collision import link if needed = https://github.com/bmoren/p5.collide2D
		
		
	//10X5 BACKGROUND SQUARES
		//paint objects
		bg.paint(g);
	//	slime.paint(g);
		for(int i = 0; i < slimes.length-1; i++) {
			slimes[i].paint(g);
		}
		Color color = new Color(255, 153, 51); //Sets text to orange, also used to see hit boxes. Is movable to different parts of code to hide/show hit boxes.

		

		
		g.setColor(color);

		
		Font stringFont = new Font( "SansSerif", Font.BOLD, 40 );
		g.setFont(stringFont);
		
		for(int i = 0; i < slimes.length; i++) {
		if(slimes[i].getX() <= 100) {
			slimes[i].moveRight();
		}else if (slimes[i].getX() <= 200 && slimes[i].getX() >= 101) {
			slimes[i].moveUp();
		}else if(slimes[i].getY() < 200 && slimes[i].getX() >= 101 ) {
			slimes[i].moveRight();
		}
		}
		}
		
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		for(int i = 0; i < slimes.length; i++) {
			slimes[i] = new SlimeEnemy(0, 330);
		}
		
		JFrame f = new JFrame("Plants vs Zombies");
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

