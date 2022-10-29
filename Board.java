import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;

public class Board implements Icon{
	private int width;
	private int height;
	int shadowSize = 6;
	float shadowOpacity = 0.5f;
	Color shadowColor = Color.black;
	Color color = new Color(0,0,0,0.1f);
	
	public Board(int w, int h){
		width = w;
		height = h;
	}
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		RoundRectangle2D.Double p = new RoundRectangle2D.Double(x, y, width, height, width/15, width/15);
		Line2D.Double l1 = new Line2D.Double(x, y+(width/30), x, y+height-(width/30));
		Line2D.Double l2 = new Line2D.Double(x+width/30, y, x+(29*width/30)-4, y);
		Line2D.Double l3 = new Line2D.Double(x+width, y+(width/30)+2, x+width, y+height-(width/30)-2);
		Line2D.Double l4 = new Line2D.Double(x+width/30+2, y+height, x+(29*width/30)-2, y+height);
		Arc2D.Double cur1 = new Arc2D.Double(x, y, width/15, width/15, 90, 90,Arc2D.OPEN);
		Arc2D.Double cur2 = new Arc2D.Double(x, y+height-(width/15), width/15, width/15, 180, 45,Arc2D.OPEN);
		Arc2D.Double cur3 = new Arc2D.Double(x+(28*width/30)-2, y, width/15, width/15, 45, 45,Arc2D.OPEN);
		Arc2D.Double cur4 = new Arc2D.Double(x, y+height-(width/15), width/15, width/15, 225, 45,Arc2D.OPEN);
		Arc2D.Double cur5 = new Arc2D.Double(x+(28*width/30), y+height-(width/15), width/15, width/15, 270, 90,Arc2D.OPEN);
		Arc2D.Double cur6 = new Arc2D.Double(x+(28*width/30), y, width/15, width/15, 0, 45,Arc2D.OPEN);
		
		g2.setColor(new Color(0,0,0,0.05f));
		g2.draw(p);
		g2.setColor(new Color(1, 1, 1, 0.5f));
		g2.fill(p);
		g2.setColor(new Color(1, 1, 1,0.7f));
		g2.setStroke(new java.awt.BasicStroke((float) 2));
		g2.draw(l1);
		g2.draw(l2);
		g2.draw(cur1);
		g2.draw(cur2);
		g2.draw(cur3);

		g2.setColor(new Color(0,0,0,0.2f));
		g2.draw(cur4);
		g2.draw(cur6);
		
		g2.setColor(new Color(0,0,0,0.3f));
		g2.draw(l3);
		g2.draw(l4);
		g2.draw(cur5);

	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public int getIconHeight() {
		return height;
	}

}
