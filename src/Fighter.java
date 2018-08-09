public class Fighter extends CharacterClass {
	
	public Fighter(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid) {
		super(x, y, file, action, count, duration, grid);
		// TODO Auto-generated constructor stub
		hp = 25;
		mp = 10;
		str = 10;
		def = 6;
		agl = 4;
		con = 9;
		intg = 1;
		wis = 2;
		exp = 0;
		lvl = 1;
		attckRangeX = 1;
		attckRangeY = 1;
	}
	
	public void Attack() {
		System.out.println("attacK");
		dialate();
	}
	

    public void dialate () {
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                if (grid[i][j].inRange){
                    if (i>0 && !grid[i-1][j].inRange) { 
                        grid[i-1][j].inRange = true;
                    }
                    if (j>0 && !grid[i][j-1].inRange) {
                        grid[i][j-1].inRange = true;
                    }
                    if (!grid[i+1][j].inRange) {
                        grid[i+1][j].inRange = true;
                        System.out.println(i+1);
                    }
                    if (!grid[i][j+1].inRange) {
                        grid[i][j+1].inRange = true;
                    }
                }
            }
        }
    }
	
	
}

