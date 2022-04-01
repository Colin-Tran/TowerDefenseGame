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
	SlimeEnemy slime = new SlimeEnemy(50,50);
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.drawRect(mainChar.x, mainChar.y, mainChar.getWidth(), mainChar.getHeight());
		//g.drawRect(asteroidL.x, asteroidL.y, asteroidL.getWidth(), asteroidL.getHeight());
		
		//p5 collision import link if needed = https://github.com/bmoren/p5.collide2D
		
		
		//setting correct Heights and Widths for the different hit box sizes for the asteroids and spaceships
		//Adjusted according to hit box shown on screen
		
		//paint objects
		bg.paint(g);
		slime.paint(g);
		
		Color color = new Color(255, 153, 51); //Sets text to orange, also used to see hit boxes. Is movable to different parts of code to hide/show hit boxes.

		//g.setColor(color); 
		
		//Draws Hit Boxes for All Objects
		
		
		g.setColor(color);
		//Writes End Game Message and Score
		Font stringFont = new Font( "SansSerif", Font.BOLD, 40 );
		g.setFont(stringFont);
		
		//Checks Score
	
		
	
		
		
	
		}
		
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
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
		
		//originally planned for rotation, but now removed feature
		
		/*if (mainChar.x < 250 && mainChar.x > 0) {
			System.out.println("left");
			mainChar.rotate();
			shipHitbox.rotate();
		}
		if (mainChar.x > 250 && mainChar.x < 500) {
			System.out.println("right");
			mainChar.leftRotate();
			shipHitbox.leftRotate();
			*/
		//}
		
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
		
		//CONTROLS SHIP MOVEMENT
			System.out.println(arg0.getKeyCode());
			
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//STOPS MOVEMENT AS SOON AS KEYS ARE RELEASED (smoother movement)
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

