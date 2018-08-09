package com.Group;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGround {

	Image image;
	int w;
	int h;
	int x;
	int y;

	public BackGround(String file,int x, int y, int w, int h) {

		image = Toolkit.getDefaultToolkit().getImage(file);
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;

	}

	public void draw(Graphics g) {

		g.drawImage(image, x, y, w, h, null);

	}

}
