package com.Group;
import java.awt.*;

public class Fighter extends CharacterClass {
	
	public Fighter(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
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
		image = Toolkit.getDefaultToolkit().getImage("fighter.png");
	}
	

}

