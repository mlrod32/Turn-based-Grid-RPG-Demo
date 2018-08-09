package com.Group;

import java.awt.Graphics;

public class Sprite {
	static String dir = System.getProperty("user.dir") + "\\Images\\assassin\\";

	int x;
	int y;
	
	//Grid location ie (1,4)
	int grid_x = 0;
	int grid_y = 0;
	
	static final int WALK = 0;
	static final int ATTACK = 1;
	static final int DIE = 2;

    static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3;

     int facing = DOWN;

	Animation[] animation;
	Rect[][] grid;
	boolean moving = false;

	int pose = LEFT;
	public boolean attack;
	public boolean die;
	public boolean jump;
	
	//Enemy enemy;
	
	public Sprite(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
//		this.x = x;
//		this.y = y;
		this.grid = grid;
		this.grid_x = x;
		this.grid_y = y;
		
                System.out.println(file);
		getCoordinates(x,y);
		
		animation = new Animation[action.length];

		for (int i = 0; i < action.length; i++) {
			animation[i] = new Animation(file + action[i], count, duration);
		}
	}
	
	public void getCoordinates (int x, int y) {
		System.out.println(x+ " " + y);
		this.x = grid[x][y].x;
		this.y = grid[x][y].y;
		//grid[x][y].inRange = true;
	}

	public void moveLeftBy(int dx) {

		x -= dx;
		grid_x -= dx;
		moving = true;
		pose = WALK;
	}

	public void moveRightBy(int dx) {

		x += dx;
		grid_x += dx;
		moving = true;
		pose = WALK;
	}

	public void moveUpBy(int dy) {

		y += dy;
		grid_y += dy;
		moving = true;
		pose = WALK;
	}
	
	public void moveDownBy(int dy) {
	
		y -= dy;
		grid_y -= dy;
		moving = true;
		pose = WALK;
	}
	
	

	public void draw(Graphics g) {
		if ((moving) || (moving && attack && jump)) 
			g.drawImage(animation[pose].nextImage(), x, y, 32, 32, null);
		else 
			g.drawImage(animation[pose].stillImage(), x, y, 32, 32, null);
		//moving = false;
	}
/* Commented it out because I don�t know if we want our players to be marching in place or not.//*/

}
