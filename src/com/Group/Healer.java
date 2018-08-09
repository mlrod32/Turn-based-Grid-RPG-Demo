package com.Group;
import java.awt.*;

public class Healer extends CharacterClass{
    public Healer(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
		super(x, y, file, action, count, duration, grid);
		hp = 10;
		maxHp = hp;
		mp = 20;
		str = 1;
		def = 3;
		agl = 3;
		con = 2;
		intg = 9;
		wis = 8;
		exp = 0;
		lvl = 1;
		atkRng = 3;
		mvmRange = 3;
                image = Toolkit.getDefaultToolkit().getImage("healer.png");
                System.out.println("Healer");
	}
    
        public void CheckStates() {
		switch (currState) {

		case IDLE:
			break;
		case MOVING:
			moveRng();
			break;
		case ATTACKING:
			healRng();
			break;
		case TURNEND:

		case DEAD:
			break;

		}
	}
        
        public void healRng() {

		// System.out.println("Range");

		maxXRngRight = grid_x + atkRng;
		maxXRngLeft = grid_x - atkRng;
		maxYRngDown = grid_y + atkRng;
		maxYRngUp = grid_y - atkRng;

		maxNE = grid_x + atkRng;
		maxNW = grid_x - atkRng;
		maxSW = grid_x - atkRng;
		maxSE = grid_x + atkRng;

		// System.out.println("Right range = " + maxXRngRight);
		// System.out.println("up range = " + maxYRngUp);
		// System.out.println("leftt range = " + maxXRngLeft);
		// System.out.println("down range = " + maxYRngDown);

		for (int i = grid_x; i <= maxXRngRight; i++) {
			for (int j = grid_y; j <= maxYRngDown; j++) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].healrange = true;
					}
				}

			}
		}

		for (int i = grid_x; i >= maxXRngLeft; i--) {
			for (int j = grid_y; j >= maxYRngUp; j--) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].healrange = true;
					}
				}

			}
		}

		for (int i = grid_x; i <= maxXRngRight; i++) {
			for (int j = grid_y; j >= maxYRngUp; j--) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].healrange = true;
					}
				}

			}
		}

		for (int i = grid_x; i >= maxXRngLeft; i--) {
			for (int j = grid_y; j <= maxYRngDown; j++) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].healrange = true;
					}
				}

			}
		}
        }

        public void Attack(int x, int y) {
		if ((grid[x][y].healrange) && (grid[x][y].occupiedBy != this) && grid[x][y].occupiedBy != null) {
			grid[x][y].occupiedBy.Heal(wis);
                        System.out.println("in if statement");
			//System.out.println(grid[x][y].occupiedBy.hp);
		}
		active++;
		System.out.println("Active = " + active);
		System.out.println("Healed");
		// onTurn();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].healrange = false;
			}
		}

		currState = States.TURNEND;
	}
}
