import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 * The type Board.
 */
public class Board implements Icon {
	private int width;
	private int height;
	private String imageName = "";
	BufferedImage img;

	/**
	 * Instantiates a new Board.
	 *
	 * @param w    the width
	 * @param h    the height
	 * @param name the name
	 */
	public Board(int w, int h, String name){
		width = w;
		height = h;
		imageName = name;
	}

	/**
	 * Return Icon Width
	 */
	public int getIconWidth() {
		return width;
	}

	/**
	 * Return Icon Height
	 */
	public int getIconHeight() {
		return height;
	}

	/**
	 * Instantiates a new Board.
	 *
	 * @param Component    the Component
	 * @param Graphics    the Graphics
	 * @param x1 x-position
	 * @param y1 y-position
	 */
	public void paintIcon(Component c, Graphics g, int x1, int y1) {
		if (imageName.equals("")) {
			Graphics2D g2 = (Graphics2D) g;
			RoundRectangle2D.Double p = new RoundRectangle2D.Double(x1, y1, width, height, width/15, width/15);
			Line2D.Double l1 = new Line2D.Double(x1, y1+(width/30), x1, y1+height-(width/30));
			Line2D.Double l2 = new Line2D.Double(x1+width/30, y1, x1+(29*width/30)-4, y1);
			Line2D.Double l3 = new Line2D.Double(x1+width, y1+(width/30)+2, x1+width, y1+height-(width/30)-2);
			Line2D.Double l4 = new Line2D.Double(x1+width/30+2, y1+height, x1+(29*width/30)-2, y1+height);
			Arc2D.Double cur1 = new Arc2D.Double(x1, y1, width/15, width/15, 90, 90,Arc2D.OPEN);
			Arc2D.Double cur2 = new Arc2D.Double(x1, y1+height-(width/15), width/15, width/15, 180, 45,Arc2D.OPEN);
			Arc2D.Double cur3 = new Arc2D.Double(x1+(28*width/30)-2, y1, width/15, width/15, 45, 45,Arc2D.OPEN);
			Arc2D.Double cur4 = new Arc2D.Double(x1, y1+height-(width/15), width/15, width/15, 225, 45,Arc2D.OPEN);
			Arc2D.Double cur5 = new Arc2D.Double(x1+(28*width/30), y1+height-(width/15), width/15, width/15, 270, 90,Arc2D.OPEN);
			Arc2D.Double cur6 = new Arc2D.Double(x1+(28*width/30), y1, width/15, width/15, 0, 45,Arc2D.OPEN);

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
		else {				
			InputStream is = getClass().getResourceAsStream(imageName);
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = img.createGraphics();
			
			int x = img.getMinX();
			int y = img.getMinY();
			int width1 = img.getWidth();
			int height1 = img.getHeight();
				
			RoundRectangle2D.Double p = new RoundRectangle2D.Double(img.getMinX(), img.getMinY(), img.getWidth(), img.getHeight(), img.getWidth()/15, img.getHeight()/15);
			g2.setPaint(new Color(0,0,0,0.1f));
			g2.fill(p);
			
			Line2D.Double l1 = new Line2D.Double(x, y+(width1/30), x, y+height1-(width1/30));
			Line2D.Double l2 = new Line2D.Double(x+width1/30, y+2, x+(29*width1/30)-4, y+2);
			Line2D.Double l3 = new Line2D.Double(x+width1, y+(width1/30)+2, x+width1, y+height1-(width1/30)-5);
			Line2D.Double l4 = new Line2D.Double(x+width1/30+4, y+height1-1, x+(29*width1/30)-5, y+height1-1);
			Arc2D.Double cur1 = new Arc2D.Double(x+1, y+1, width1/15, width1/15, 90, 90,Arc2D.OPEN);
			Arc2D.Double cur2 = new Arc2D.Double(x+1, y+height1-(width1/15)-1, width1/15, width1/15, 180, 45,Arc2D.OPEN);
			Arc2D.Double cur3 = new Arc2D.Double(x+(28*width1/30)-1, y+1, width1/15, width1/15, 45, 45,Arc2D.OPEN);
			Arc2D.Double cur4 = new Arc2D.Double(x+1, y+height1-(width1/15)-2, width1/15, width1/15, 225, 45,Arc2D.OPEN);
			Arc2D.Double cur5 = new Arc2D.Double(x+(28*width1/30)-1, y+height1-(width1/15)-1, width1/15, width1/15, 270, 90,Arc2D.OPEN);
			Arc2D.Double cur6 = new Arc2D.Double(x+(28*width1/30)-1, y+1, width1/15, width1/15, 0, 45,Arc2D.OPEN);
			g2.setColor(new Color(1, 1, 1,0.7f));
			g2.setStroke(new java.awt.BasicStroke((float) 4));
			g2.draw(l1);
			g2.draw(l2);
			g2.draw(cur1);
			g2.draw(cur2);
			g2.draw(cur3);		
			g2.setColor(new Color(0,0,0,0.4f));
			g2.draw(cur4);
			g2.draw(cur6);		
			g2.setColor(new Color(0,0,0,0.5f));
			g2.draw(l3);
			g2.draw(l4);
			g2.draw(cur5);
			
			g2.dispose();
			
			RoundRectangle2D.Double p1 = new RoundRectangle2D.Double(x1, y1, width, height, width/15, width/15);
			g.setClip(p1);
			
			g.drawImage(img, x1, y1, width, height, null);
		}	
	}
}
