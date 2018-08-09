package com.Group;

import java.awt.Graphics;
import java.awt.Image;

public class CharacterClass extends Sprite {

	// MEMBER VARIABLES
	int maxHp;
	int hp;// amount of damage the character can take
	int mp;// mystical energy that allows specials
	int str;// physical strength
	int def;// toughness of body
	int agl;// readiness and grace in physical activity
	int con;// helps to determine health
	int intg;// intelligence of character
	int wis;// the characters experience in the realm of magic.
	int exp;// the experience points of the character.
	int lvl;// the level of the character which is determined by the amount of exp they
			// have.
	private int q;
	int atkRng;
	int mvmRange;// How far the character can move on the grid.
	int maxXRngRight;
	int maxYRngDown;
	int maxXRngLeft;
	int maxYRngUp;
	int maxNE;
	int maxNW;
	int maxSE;
	int maxSW;

	// Grid map;

	boolean moving = false;;
	boolean moved = false;;
	boolean attacking = false;
	boolean attacked = false;
	boolean idle = false;
	int active = 0;
	boolean ally = true;
        Image image;

	enum States {
		IDLE, MOVING, ATTACKING, TURNEND, DEAD;
	}

	States currState = States.MOVING;

	public void CheckStates() {
		switch (currState) {

		case IDLE:
			if (this.onTurn() == false) {

			}
			break;
		case MOVING:
			moveRng();
			break;
		case ATTACKING:
			atkRng();
			break;
		case TURNEND:

		case DEAD:
			break;

		}
	}

	// CONSTRUCTOR
	public CharacterClass(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
		super(x, y, dir + file, action, count, duration, grid);
                grid[x][y].occupiedBy = this;
	}

	public void Attack(int x, int y) {
		if ((grid[x][y].inRange) && (grid[x][y].occupiedBy != this) && grid[x][y].occupiedBy != null) {
			grid[x][y].occupiedBy.takeDamage(str);
			//System.out.println(grid[x][y].occupiedBy.hp);
		}
		active++;
		System.out.println("Active = " + active);
		System.out.println("Attacked");
		// onTurn();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].inRange = false;
			}
		}

		currState = States.TURNEND;
	}

	public void move(int dx, int dy) {

		if (grid[dx][dy].moveRange && grid[dx][dy].occupiedBy == null) {
			grid[grid_x][grid_y].occupiedBy = null;
			grid[grid_x][grid_y].occupied = false;

			this.x = grid[dx][dy].x;
			this.y = grid[dx][dy].y;

			grid_x = dx;
			grid_y = dy;

			grid[dx][dy].occupiedBy = this;

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j].moveRange = false;
				}
			}

			currState = States.ATTACKING;
		} else if (dx == grid_x && dy == grid_y) {
			grid[grid_x][grid_y].occupiedBy = null;
			grid[grid_x][grid_y].occupied = false;

			this.x = grid[dx][dy].x;
			this.y = grid[dx][dy].y;

			grid_x = dx;
			grid_y = dy;

			grid[dx][dy].occupiedBy = this;

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j].moveRange = false;
				}
			}

			currState = States.ATTACKING;
		}
	}

	public void takeDamage(int dmg) {
		hp -= dmg;
		System.out.println("Health" + hp);
		if (hp <= 0) {
			grid[grid_x][grid_y].occupiedBy = null;
			Group.allies.remove(this);
		}
	}
        public void Heal(int dmg) {
		hp += dmg;
		System.out.println("Health" + hp);
		if (hp <= 0) {
			grid[grid_x][grid_y].occupiedBy = null;
			Group.allies.remove(this);
		}
	}

	public boolean onTurn() {

		System.out.println("Character's turn");
		if (active < 1) {
			atkRng();
			return true;
		} else {
			return false;

		}

	}

	// METHODS
	public void atkRng() {

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
						grid[i][j].inRange = true;
					}
				}

			}
		}

		for (int i = grid_x; i >= maxXRngLeft; i--) {
			for (int j = grid_y; j >= maxYRngUp; j--) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].inRange = true;
					}
				}

			}
		}

		for (int i = grid_x; i <= maxXRngRight; i++) {
			for (int j = grid_y; j >= maxYRngUp; j--) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].inRange = true;
					}
				}

			}
		}

		for (int i = grid_x; i >= maxXRngLeft; i--) {
			for (int j = grid_y; j <= maxYRngDown; j++) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].inRange = true;
					}
				}

			}
		}

		// try {
		// for (int i = maxXRngLeft; i <= maxXRngRight; i++) {
		// for (int j = maxYRngUp; j <= maxYRngDown; j++) {
		//
		// if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
		// if (!grid[i][j].isEnOC) {
		// grid[i][j].inRange = true;
		// }
		// } else {
		// break;
		// }
		//
		// }
		// }
		// } catch (ArrayIndexOutOfBoundsException e) {
		// System.out.println("Out of Range");
		// }

		// for (int i = grid_x; i >= maxXRngLeft; i--) {
		// if (!grid[i][grid_y].isEnOC) {
		// grid[i][grid_y].inRange = true;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i <= maxXRngRight; i++) {
		// if (!grid[i][grid_y].isEnOC) {
		// grid[i][grid_y].inRange = true;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_y; i >= maxYRngUp; i--) {
		// if (!grid[grid_x][i].isEnOC) {
		// grid[grid_x][i].inRange = true;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_y; i <= maxYRngDown; i++) {
		// if (!grid[grid_x][i].isEnOC) {
		// grid[grid_x][i].inRange = true;
		// } else {
		// break;
		// }
		// }
		//
		// for (int i = grid_x; i <= maxNE; i++) {
		// q = grid_y + atkRng;
		// if (!grid[i + 1][q].isEnOC) {
		// grid[i][q].inRange = true;
		// q--;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i >= maxNW; i--) {
		// q = grid_y;
		// if (!grid[i - 1][q].isEnOC) {
		// grid[i][q-1].inRange = true;
		// q--;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i >= maxSW; i--) {
		// q = grid_y;
		// if (!grid[i - 1][q + 1].isEnOC) {
		// grid[i][q + 1].inRange = true;
		// q++;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i <= maxSE; i++) {
		// q = grid_y;
		// if (!grid[i + 1][q + 1].isEnOC) {
		// grid[i][q-1].inRange = true;
		// q--;
		// } else {
		// break;
		// }
		// }

	}

	public void moveRng() {
		// System.out.println("Range");

		maxXRngRight = grid_x + mvmRange;
		maxXRngLeft = grid_x - mvmRange;
		maxYRngDown = grid_y + mvmRange;
		maxYRngUp = grid_y - mvmRange;

		maxNE = grid_x + mvmRange;
		maxNW = grid_x - mvmRange;
		maxSW = grid_x - mvmRange;
		maxSE = grid_x + mvmRange;

		// System.out.println("Right range = " + maxXRngRight);
		// System.out.println("up range = " + maxYRngUp);
		// System.out.println("leftt range = " + maxXRngLeft);
		// System.out.println("down range = " + maxYRngDown);

		for (int i = grid_x; i <= maxXRngRight; i++) {
			for (int j = grid_y; j <= maxYRngDown; j++) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].moveRange = true;
					}
				}

			}
		}

		for (int i = grid_x; i >= maxXRngLeft; i--) {
			for (int j = grid_y; j >= maxYRngUp; j--) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].moveRange = true;
					}
				}

			}
		}

		for (int i = grid_x; i <= maxXRngRight; i++) {
			for (int j = grid_y; j >= maxYRngUp; j--) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].moveRange = true;
					}
				}

			}
		}

		for (int i = grid_x; i >= maxXRngLeft; i--) {
			for (int j = grid_y; j <= maxYRngDown; j++) {
				if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
					if (!grid[i][j].isEnOC) {
						grid[i][j].moveRange = true;
					}
				}

			}
		}

		// try {
		// for (int i = maxXRngLeft; i <= maxXRngRight; i++) {
		// for (int j = maxYRngUp; j <= maxYRngDown; j++) {
		//
		// if ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length)) {
		// if (!grid[i][j].isEnOC) {
		// grid[i][j].inRange = true;
		// }
		// } else {
		// break;
		// }
		//
		// }
		// }
		// } catch (ArrayIndexOutOfBoundsException e) {
		// System.out.println("Out of Range");
		// }

		// for (int i = grid_x; i >= maxXRngLeft; i--) {
		// if (!grid[i][grid_y].isEnOC) {
		// grid[i][grid_y].inRange = true;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i <= maxXRngRight; i++) {
		// if (!grid[i][grid_y].isEnOC) {
		// grid[i][grid_y].inRange = true;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_y; i >= maxYRngUp; i--) {
		// if (!grid[grid_x][i].isEnOC) {
		// grid[grid_x][i].inRange = true;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_y; i <= maxYRngDown; i++) {
		// if (!grid[grid_x][i].isEnOC) {
		// grid[grid_x][i].inRange = true;
		// } else {
		// break;
		// }
		// }
		//
		// for (int i = grid_x; i <= maxNE; i++) {
		// q = grid_y + atkRng;
		// if (!grid[i + 1][q].isEnOC) {
		// grid[i][q].inRange = true;
		// q--;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i >= maxNW; i--) {
		// q = grid_y;
		// if (!grid[i - 1][q].isEnOC) {
		// grid[i][q-1].inRange = true;
		// q--;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i >= maxSW; i--) {
		// q = grid_y;
		// if (!grid[i - 1][q + 1].isEnOC) {
		// grid[i][q + 1].inRange = true;
		// q++;
		// } else {
		// break;
		// }
		// }
		// for (int i = grid_x; i <= maxSE; i++) {
		// q = grid_y;
		// if (!grid[i + 1][q + 1].isEnOC) {
		// grid[i][q-1].inRange = true;
		// q--;
		// } else {
		// break;
		// }
		// }

	}

	public void draw(Graphics g) {
		// atkRng();
		super.draw(g);
	}
	// public void dialate() {
	// for (int i = 0; i < grid.length; i++) {
	// for (int j = 0; j < grid[i].length; j++) {
	// if (grid[i][j].inRange == 1) {
	// if (i > 0 && grid[i - 1][j].inRange == 0) {
	// grid[i - 1][j].inRange = 2;
	// }
	// if (j > 0 && grid[i][j - 1].inRange == 0) {
	// grid[i][j - 1].inRange = 2;
	// }
	// if (i + 1 < grid.length && grid[i + 1][j].inRange == 0) {
	// grid[i + 1][j].inRange = 2;
	//
	// }
	// if (j + 1 < grid[i].length && grid[i][j + 1].inRange == 0) {
	// grid[i][j + 1].inRange = 2;
	// System.out.println(j + 1);
	//
	// }
	// }
	// }
	// }
	//
	// for (int i = 0; i < grid.length; i++) {
	// for (int j = 0; j < grid[i].length; j++) {
	// if (grid[i][j].inRange == 2) {
	// grid[i][j].inRange = 1;
	// }
	// }
	// }
	// }

}
