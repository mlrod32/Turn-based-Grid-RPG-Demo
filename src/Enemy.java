public class Enemy extends CharacterClass {
    public Enemy(int x, int y, String file, String[] action, int count, int duration,  Rect[][] grid){

        super(x, y, file, action, count, duration, grid);
    }

    public void damaged(int damage){
    	this.hp -= damage;
    }
}