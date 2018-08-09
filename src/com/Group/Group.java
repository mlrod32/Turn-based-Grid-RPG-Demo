package com.Group;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Group extends Canvas implements Runnable, MouseListener, MouseMotionListener {
	static JFrame frame;
	Thread thread;

	// coordinate[][] grid = new coordinate[20][20];
	Rect[][] grid = new Rect[30][20];

	static String dir = System.getProperty("user.dir") + "\\Images\\assassin\\";
	Image off_screen;
	Graphics off_g;
	Image image = Toolkit.getDefaultToolkit().getImage(dir + "assassinattackleft_0.png");
	Image fighter = Toolkit.getDefaultToolkit().getImage("fighter.png");
	Image archer = Toolkit.getDefaultToolkit().getImage("archer.png");

	int mx;
	int my;
	String[] action = { "left", "down", "up", "right" };
	Fighter test;
	Ranger test5;
	Tank test3;
        Healer test9;
        
	Enemy test2;
	Enemy test4;
	Enemy test6;
	LinkedList<CharacterClass> queue = new LinkedList<CharacterClass>();
	int currentChar = 0;
	boolean playerturn = true;
	static LinkedList<CharacterClass> allies = new LinkedList<CharacterClass>();
	static LinkedList<CharacterClass> enemies = new LinkedList<CharacterClass>();

	GameOver go;
	BackGround bg;
	BackGround ui;
	Font font = new Font("Monospaced", Font.BOLD, 15);
	String player = "PLAYER";
	String Enemies = "OPPONENT";
        Music music;

	public void init() {
		off_screen = this.createImage(2000, 700);
		off_g = off_screen.getGraphics();
                
                music = new Music(dir+"epic-loop.wav");

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Rect(i * 32, j * 32, 32, 32);
			}
		}

		bg = new BackGround("RtsBackGround.png", 0, 0, 960, 640);
		ui = new BackGround("split.png", 960, 0, 142, 640);
		test = new Fighter(20, 15,"eliwoodattack", action, 3, 5, grid);
		test2 = new Enemy(6, 1, "assassinattack", action, 6, 5, grid);

		test5 = new Ranger(20, 14, "rebeccaattack", action, 3, 5, grid);
		test4 = new Enemy(0, 0, "assassinattack", action, 6, 5, grid);

		test3 = new Tank(20, 13, "knight", action, 6, 5, grid);
		test6 = new Enemy(1, 1, "assassinattack", action, 6, 5, grid);
                
                test9 = new Healer(21, 15, "serraattack", action, 6, 5, grid);
                
                allies.add(test);
		allies.add(test3);
                allies.add(test9);
		allies.add(test5);
                
		enemies.add(test2);
		enemies.add(test4);
		enemies.add(test6);

		queue = allies;

		thread = new Thread(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true && enemies.size() > 0) {
			// the turn order.
			if (queue.size() > 0) {
				queue.get(currentChar).CheckStates();
			}
			checkTurnEnd();

			// test.onTurn();

			// test.atkRng();
			repaint(); // repaint the screen.
			try {
				thread.sleep(15);// gives the OS some time to do what it has to do to
			} catch (ArrayIndexOutOfBoundsException x) {
				System.out.println("Error");
			} catch (Exception e) {
				System.out.println("Error");
			}
		}

		if (enemies.size() == 0) {
			go = new GameOver();
		}

	}

	public void update(Graphics g) {
		off_g.clearRect(0, 0, 2000, 2000); // clears screen in the areas given by the dimensions
		paint(off_g);
		g.drawImage(off_screen, 0, 0, null);
	}

	public void paint(Graphics g) {
            
                bg.draw(g);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].draw(g);
			}
		}
		for (CharacterClass ally : allies) {
			ally.draw(g);
		}
		for (CharacterClass enemy : enemies) {
			enemy.draw(g);
		}
                ui.draw(g);
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString(player, 965, 50);
		g.setColor(Color.BLACK);
		g.drawString(Enemies, 1030, 50);

		try {
                    for (int i = 0; i < allies.size(); i++) {
                        g.setColor(Color.BLACK);
                        g.drawImage(allies.get(i).image, 960, 75 + (i * 50), 30, 30, this);
                        g.drawString("" + allies.get(i).hp + "/" + allies.get(i).maxHp, 985, 100 + (i * 50));
                    }
                    for (int i = 0; i < enemies.size(); i++) {
                        g.setColor(Color.BLACK);
                        g.drawImage(image, 1030, 75 + (i*50), this);
                        g.drawString("" + enemies.get(i).hp + "/" + enemies.get(i).maxHp, 1055, 100 + (i * 50));
                    }
//			if (allies.get(0) != null) {
//				g.setColor(Color.BLACK);
//				g.drawImage(fighter, 960, 70, 32, 32, this);
//				g.drawString("" + allies.get(0).hp + "/" + allies.get(0).maxHp, 985, 100);
//			}
//			if (allies.get(1) != null) {
//				g.setColor(Color.BLACK);
//				g.drawImage(image, 960, 120, this);
//				g.drawString("" + allies.get(1).hp + "/" + allies.get(1).maxHp, 985, 150);
//			}
//			if (allies.get(2) != null) {
//				g.setColor(Color.BLACK);
//				g.drawImage(archer, 960, 170, 32, 32, this);
//				g.drawString("" + allies.get(2).hp + "/" + allies.get(2).maxHp, 985, 200);
//			}
//			if (enemies.get(0) != null) {
//				g.setColor(Color.WHITE);
//				g.drawImage(image, 1030, 70, this);
//				g.drawString("" + enemies.get(0).hp + "/" + enemies.get(0).maxHp, 1055, 100);
//			}
//			if (enemies.get(1) != null) {
//				g.setColor(Color.WHITE);
//				g.drawImage(image, 1030, 120, this);
//				g.drawString("" + enemies.get(1).hp + "/" + enemies.get(1).maxHp, 1055, 150);
//			}
//			if (enemies.get(2) != null) {
//				g.setColor(Color.WHITE);
//				g.drawImage(image, 1030, 170, this);
//				g.drawString("" + enemies.get(2).hp + "/" + enemies.get(2).maxHp, 1055, 200);
//			}

                
		} catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
		} catch (NullPointerException e){
                    System.out.println("ui error");
                }
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

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
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
		if (playerturn) {
			if (queue.get(currentChar).currState == CharacterClass.States.MOVING) {
				for (int x = 0; x < grid.length; x++) {
					for (int y = 0; y < grid[x].length; y++) {
						if (grid[x][y].contains(e.getX(), e.getY())) {
							queue.get(currentChar).move(x, y);
						}

					}
				}
			} else if (queue.get(currentChar).currState == CharacterClass.States.ATTACKING) {
				for (int x = 0; x < grid.length; x++) {
					for (int y = 0; y < grid[x].length; y++) {
						if (grid[x][y].contains(e.getX(), e.getY())) {
							queue.get(currentChar).Attack(x, y);
						}

					}
				}
			}
		}
	}

	public void checkTurnEnd() {
		try {
			if (queue.get(currentChar).currState == CharacterClass.States.TURNEND && queue.size() > 0) {
				queue.get(currentChar).currState = CharacterClass.States.IDLE;

				if (currentChar < queue.size() - 1) {
					currentChar++;
				} else {
					currentChar = 0;
					if (playerturn) {
						queue = enemies;
					} else {
						queue = allies;
					}

					playerturn = !playerturn;
				}

				queue.get(currentChar).currState = CharacterClass.States.MOVING;

			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Group game = new Group();
		JFrame frame = new JFrame("Group");
		// frame.setUndecorated(true);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.requestFocus();
		frame.setSize(1118, 670);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.init();

	}
}
