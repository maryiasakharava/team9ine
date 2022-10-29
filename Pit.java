import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;

public class Pit implements Icon{
	private int width;
	private int height;
	
	public Pit(int w, int h){
		width = w;
		height = h;
	}

	public int getIconWidth() {return width;}

	public int getIconHeight() {return height;}
	
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		RoundRectangle2D.Double p = new RoundRectangle2D.Double(x, y, width, height, width/1.5, width/1.5);
		Line2D.Double l1 = new Line2D.Double(x, y+(width/3)+4, x, y+height-(width/3)-5);
		Line2D.Double l2 = new Line2D.Double(x+width/3+4, y, x+(2*width/3)-5, y);
		Arc2D.Double cur1 = new Arc2D.Double(x, y, width/1.5, width/1.5, 90, 90,Arc2D.OPEN);
		Arc2D.Double cur2 = new Arc2D.Double(x, y+height-(width/1.5), width/1.5, width/1.5, 180, 45,Arc2D.OPEN);
		Arc2D.Double cur3 = new Arc2D.Double(x+(width/3), y, width/1.5, width/1.5, 45, 45,Arc2D.OPEN);
	
		g2.setColor(new Color(0,0,0,0.09f));
		g2.fill(p);
		g2.setColor(new Color(0,0,0,0.3f));
		g2.setStroke(new java.awt.BasicStroke(4));
		g2.draw(p);
		g2.draw(l1);
		g2.draw(l2);
		g2.draw(cur1);
		g2.draw(cur2);
		g2.draw(cur3);
	}

}
