package com.Group;

public class Enemy extends CharacterClass {

	double slope = 2000;
	int dx;
	int dy;
	int temp_x;
	int temp_y;
	double temp_slope;

	public Enemy(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {

		super(x, y, file, action, count, duration, grid);
		hp = 25;
		maxHp = hp;
		mp = 10;
		str = 10;
		def = 6;
		agl = 4;
		con = 9;
		intg = 1;
		wis = 2;
		exp = 0;
		lvl = 1;
		atkRng = 1;
		mvmRange = 5;
		ally = false;
	}

	public void CheckStates() {
		switch (currState) {

		case IDLE:
			if (this.onTurn() == false) {

			}
			break;
		case MOVING:
			moveRng();
			moveAI();
			break;
		case ATTACKING:
			atkRng();
			attackAi();
			break;
		case TURNEND:

		case DEAD:
			break;

		}
	}

	public void takeDamage(int dmg) {
		hp -= dmg;
		System.out.println("Health" + hp);
		if (hp <= 0) {
			grid[grid_x][grid_y].occupiedBy = null;
			Group.enemies.remove(this);
		}
	}

	public void moveAI() {
		slope = 5000;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].occupiedBy != null && grid[i][j].occupiedBy.ally) {
					CharacterClass temp = grid[i][j].occupiedBy;
					double distance = Math.sqrt(((this.grid_x - temp.grid_x) * (this.grid_x - temp.grid_x))
							+ ((this.grid_y - temp.grid_y) * (this.grid_y - temp.grid_y)));
					//
					if (slope >= distance) {
						slope = distance;
						temp_x = temp.grid_x;
						temp_y = temp.grid_y;
						System.out.println("slope change: " + slope);
						System.out.println("target loc: " + temp_x + ", " + temp_y);
						// dx = this.grid_x + Math.abs((this.grid_x - temp_x) + 1);
						// dy = this.grid_y + Math.abs((this.grid_y - temp_y) + 1);
					}
				}
			}
		}
		int midx = (this.grid_x - temp_x) + 1;
		System.out.println(this.grid_x + " - " + temp_x + " + 1 = " + midx);
		int tempgrid_x = (this.grid_x - midx);
		int tempgrid_y = (this.grid_y - ((this.grid_y - temp_y) + 1));
		dx = Math.abs(tempgrid_x);
		dy = Math.abs(tempgrid_y);

		if (dx > this.grid_x + mvmRange) {
			dx = this.grid_x + mvmRange;
		}
		if (dy > this.grid_y + mvmRange) {
			dy = this.grid_y + mvmRange;
		}
		if (dx < this.grid_x - mvmRange) {
			dx = this.grid_x - mvmRange;
		}
		if (dy < this.grid_y - mvmRange) {
			dy = this.grid_y - mvmRange;
		}

		if (grid[dx][dy].occupiedBy != null) {
			if (grid[dx + 1][dy].moveRange && grid[dx + 1][dy].occupiedBy == null && dx < grid.length - 1) {
				dx += 1;
			} else if (grid[dx - 1][dy].moveRange && grid[dx - 1][dy].occupiedBy == null && dx > 0) {
				dx -= 1;
			} else if (grid[dx][dy + 1].moveRange && grid[dx][dy + 1].occupiedBy == null && dy > 0) {
				dy += 1;
			} else if (grid[dx][dy - 1].moveRange && grid[dx][dy - 1].occupiedBy == null && dy < grid[dx].length - 1) {
				dy -= 1;
			}
		}

		System.out.println("desired x: " + dx + ", y:" + dy);

		move(dx, dy);
	}

	public void attackAi() {
		if (grid[temp_x][temp_y].inRange) {
			Attack(temp_x, temp_y);
		} else {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j].inRange = false;
				}
			}
			currState = States.TURNEND;
		}
	}

}