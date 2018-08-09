
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Rect{
	int x;
	int y;

	int w;
	int h;
	
	int centerx;
	int centery;

	boolean hovered = false;
	boolean inRange = false;
	boolean occupied = false;

	public Rect (int x, int y, int w, int h){
		this.x = x;
		this.y = y; 
		this.w = w;
		this.h = h;
		
		centerx = x + (w/2);
		centery = y + (h/2);
	}

	public void draw(Graphics g){		
		if (hovered) { 
			g.setColor(Color.blue);
			g.fillRect(x,y,w,h);
		}
		else if (inRange) {
			g.setColor(Color.red);
			g.fillRect(x,y,w,h);
		}
		else{
			g.setColor(Color.black);
			g.drawRect(x,y,w,h);
		}

		g.drawRect(centerx-5, centery-5, 10, 10);
		
	}

	public void drawFill(Graphics g){
		g.fillRect(x,y,w,h);
	}
	public void drawFill(Graphics g, int drawx, int drawy){
		g.fillRect(drawx,drawy,w,h);
	}

	public boolean overlaps(Rect r){
		return (x < r.x+r.w) && (x+w > r.x) && (y < r.y+r.h) && (y+h > r.y);
	}

	public boolean contains(int mx, int my){
		return (mx > x) && (mx < x+w) && (my > y) && (my < y+h);
	}

	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	
}
