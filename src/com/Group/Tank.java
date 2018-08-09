package com.Group;

import java.awt.Toolkit;

public class Tank extends CharacterClass {

	public Tank(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
		super(x, y, file, action, count, duration, grid);

		hp = 50;
		maxHp = hp;
		mp = 10;
		str = 5;
		def = 10;
		agl = 4;
		con = 15;
		intg = 1;
		wis = 2;
		exp = 0;
		lvl = 1;
		atkRng = 2;
		mvmRange = 2;
                image = Toolkit.getDefaultToolkit().getImage("knight.png");
        }

}
