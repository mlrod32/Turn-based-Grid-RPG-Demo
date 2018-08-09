
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Group extends Canvas implements Runnable, MouseListener,MouseMotionListener{

	static JFrame frame;
	Thread thread;
	
//	coordinate[][] grid = new coordinate[20][20];
	Rect[][] grid = new Rect[10][10];
	
	Image off_screen;
	Graphics off_g;
	
	int mx; 
	int my;
	String[] action = {"up","down","left","right"};
	Fighter test;
	
	public void init() {
		off_screen = this.createImage(2000,700);
		off_g = off_screen.getGraphics();

		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				grid[x][y] = new Rect(x*32, y*32, 32, 32);
			}
		}
		
		test = new Fighter(5 , 5, "assassinattack",action, 6, 5, grid);
		thread = new Thread(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			for(int x = 0; x < 10; x++) {
				for(int y = 0; y < 10; y++) {
					if (grid[x][y].contains(mx, my)) {
						grid[x-1][y].inRange = true;
						grid[x+1][y].inRange = true;
						grid[x][y-1].inRange = true;
						grid[x][y+1].inRange = true;
					}

				}
			}
			

			repaint(); // repaint the screen.
			try {
				thread.sleep(15);// gives the OS some time to do what it has to do to			
			} catch (ArrayIndexOutOfBoundsException x) {
				System.out.println("Error");
			} catch(Exception e){
				System.out.println("Error");
			}
		}
	}
	public void update(Graphics g) {
		off_g.clearRect(0, 0, 1000, 1000); //clears screen in the areas given by the dimensions
		paint(off_g);
		g.drawImage(off_screen, 0, 0, null);
	}

	public void paint(Graphics g) {
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				grid[x][y].draw(g);
			}
		}
		test.draw(g);
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				if (grid[x][y].contains(mx, my)) {
					grid[x][y].hovered = true;
				}
				
				else {
					grid[x][y].hovered = false;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
//		mx = e.getX();
//		my = e.getY();
		test.Attack();
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated met
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Group game = new Group();
		JFrame frame = new JFrame("Group");
		frame.setUndecorated(true);
		frame.requestFocus();
		frame.setSize(1000, 400);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setResizable(false);
		frame.setVisible(true);
		game.init();

	}
}
