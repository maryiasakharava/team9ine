import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 * Pit Class
 */
public class Pit implements Icon, Cloneable{
	private int width;
	private int height;
	private int marbles;
	private MancalaModel model;
	private int ID;
	Random random = new Random();
	private double[] xm;
	private double[] ym;
	private String imageName = "";
	BufferedImage img;
	private double widthMarble;

	/**
	 * Pit Constructor
	 *
	 * @param w the width
	 * @param h the height
	 * @param m the marble arraylist
	 */
	public Pit(int w, int h,  ArrayList<Marble> m){
		width = w;
		height = h;
		this.imageName = "";
		this.widthMarble = 13;
		this.marbles = m.size();
		xm = new double[marbles];
		ym = new double[marbles];
		for(int i = 0; i < marbles; i++) {
			xm[i] = m.get(i).getX();
			ym[i] = m.get(i).getY();
		}
	}

	/**
	 * Pit Constructor x2
	 *
	 * @param w the width
	 * @param h the height
	 * @param m the marble arraylist
	 * @param name the name
	 */
	public Pit(int w, int h,  ArrayList<Marble> m, String name){
		width = w;
		height = h;
		this.imageName = name;
		this.widthMarble = 13;
		this.marbles = m.size();
		xm = new double[marbles];
		ym = new double[marbles];
		for(int i = 0; i < marbles; i++) {
			xm[i] = m.get(i).getX();
			ym[i] = m.get(i).getY();
		}
	}


	/**
	 * Get id int.
	 *
	 * @return the int
	 */
	public int getID(){
		return this.ID;
	}

	/**
	 * Add marbles.
	 *
	 * @param marbles the marbles
	 */
	public void addMarbles(int marbles){
		this.marbles +=marbles;
	}

	/**
	 * Get marbles int.
	 *
	 * @return the int
	 */
	public int getMarbles(){
		return marbles;
	}

	/**
	 * Clear pit.
	 */
	public void clearPit(){
		marbles =0;
	}

	/**
	 * Is empty boolean.
	 *
	 * @return the boolean
	 */
	public boolean isEmpty(){
		if (marbles ==0){
			return true;
		}
		else{
			return false;
		}
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
		
		Ellipse2D.Double[] m = new Ellipse2D.Double[marbles];
		if (imageName.equals("")) {
			for(int i = 0; i < marbles; i++) {
				m[i] = new Ellipse2D.Double(xm[i], ym[i], widthMarble, widthMarble);
				g2.setColor(new Color(1f,0f,0f,1f));
				g2.fill(m[i]);
				g2.setColor(Color.black);
				g2.draw(m[i]);
			}
		}
		else {				
			InputStream is = getClass().getResourceAsStream(imageName);
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {e.printStackTrace();}
			
			BufferedImage bi = new BufferedImage((int)widthMarble, (int)widthMarble, BufferedImage.TYPE_INT_ARGB);
			g2 = img.createGraphics();
			Ellipse2D.Double r = new Ellipse2D.Double(img.getMinX(), img.getMinY(), img.getWidth(), img.getHeight());
			g2.setColor(new Color(0.9f,0f,0f,0.1f));
			g2.fill(r);
			g2.setStroke(new java.awt.BasicStroke(img.getWidth()/20));
			g2.setColor(Color.black);
			g2.draw(r);

			g2.setStroke(new java.awt.BasicStroke(1));	
			
			for(int i = 0; i < marbles; i++) {							
				g.drawImage(img, (int)xm[i], (int)ym[i], (int)widthMarble, (int)widthMarble, null);
			}	
		}
		
		g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,0.1f));
		g2.fill(p);
		g2.setColor(new Color(0,0,0,0.3f));
		g2.setStroke(new java.awt.BasicStroke(4));
		g2.draw(p);
		g2.draw(l1);
		g2.draw(l2);
		g2.draw(cur1);
		g2.draw(cur2);
		g2.draw(cur3);
		g2.setStroke(new java.awt.BasicStroke(1));
	}
	
	public Object clone() throws CloneNotSupportedException{
		return this.clone();
	}

	/**
	 * Check for overlaps
	 *
	 * @param i the index
	 * @return true or fasle
	 */
	public boolean checkOverlap(int i) {
		for (int j = 0; j < i; j++) {
			if ((((xm[j]-(width/10)) < xm[i]) && ((xm[j]+(width/5)+(width/10)) > xm[i])) && (((ym[j]-5)-(width/10) < ym[i]) && ((ym[j]+(width/5)+(width/10)) > ym[i]))) {
				return true;
			}
		}
		return false;
	}
}
