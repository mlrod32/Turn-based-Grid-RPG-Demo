package com.Group;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import com.Group.CharacterClass.States;

public class Rect {
	int x;
	int y;

	int w;
	int h;

	int centerx;
	int centery;

	boolean hovered = false;
	boolean inRange = false;
	boolean occupied = false;
	boolean moveRange = false;
	boolean isEnOC = false;
        boolean healrange = false;
	CharacterClass occupiedBy;

	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		centerx = x + (w / 2);
		centery = y + (h / 2);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0f, 0f, 0f, 1f));
		g.drawRect(x, y, w, h);
		if (hovered) {
			g.setColor(Color.blue);
			g.fillRect(x, y, w, h);
		} else if (inRange == true) {
			g.setColor(new Color(1f, 0f, 0f, .4f));
			g.fillRect(x + 2, y + 2, w - 1, h - 1);
		}else if (healrange == true) {
			g.setColor(new Color(0f, 1f, 0f, .4f));
			g.fillRect(x + 2, y + 2, w - 1, h - 1);
		}else if (moveRange == true) {

			g.setColor(new Color(0f, 0f, 1f, .4f));
			g.fillRect(x + 2, y + 2, w - 1, h - 1);
		} else if ((inRange == true) && (this.occupiedBy.currState == this.occupiedBy.currState.ATTACKING)) {
			g.setColor(Color.green);
		}
		// else{
		// g.setColor(Color.black);
		// g.drawRect(x,y,w,h);
		// }
	}

	public void drawFill(Graphics g) {
		g.fillRect(x, y, w, h);
	}

	public void drawFill(Graphics g, int drawx, int drawy) {
		g.fillRect(drawx, drawy, w, h);
	}

	public boolean overlaps(Rect r) {
		return (x < r.x + r.w) && (x + w > r.x) && (y < r.y + r.h) && (y + h > r.y);
	}

	public boolean contains(int mx, int my) {
		return (mx > x) && (mx < x + w) && (my > y) && (my < y + h);
	}

	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}

}
