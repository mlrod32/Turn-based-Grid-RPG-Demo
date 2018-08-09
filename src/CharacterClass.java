
public class CharacterClass extends Sprite {
	
 //MEMBER VARIABLES
	int hp;// amount of damage the character can take
	int mp;// mystical energy that allows specials
	int str;// physical strength
	int def;// toughness of body
	int agl;// readiness and grace in physical activity
	int con;// helps to determine health
	int intg;// intelligence of character
	int wis;// the characters experience in the realm of magic.
	int exp;// the experience points of the character.
	int lvl;// the level of the character which is determined by the amount of exp they have.
	int attckRangeX;
	int attckRangeY;
	int mvmSpd;// How far the character can move on the grid.
	
   //Grid map;
   
    
    //CONSTRUCTOR
    public CharacterClass(int x, int y, String file, String[] action, int count, int duration, Rect[][] grid){
    	super( x, y, dir+file, action,  count, duration, grid);
    }

}
