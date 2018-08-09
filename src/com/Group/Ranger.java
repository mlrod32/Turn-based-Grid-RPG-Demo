package com.Group;

import java.awt.Toolkit;

public class Ranger extends CharacterClass{
	
	public Ranger(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
		super(x, y, file, action, count, duration, grid);
		
		hp = 20;
		maxHp = hp;
		mp = 10;
		str = 8;
		def = 6;
		agl = 8;
		con = 4;
		intg = 6;
		wis = 8;
		exp = 0;
		lvl = 1;
		atkRng = 5;
		mvmRange = 2;
                image =  Toolkit.getDefaultToolkit().getImage("archer.png");
		
	}

}
