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
import java.util.Random;

import javax.imageio.ImageIO;

public class Marble {
	private int x;
	private int y;
	private Random random = new Random();
	private String imageName = "";
	BufferedImage img;
	
//	public Marble(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
	
	public Marble(int width, int height) {
		this.x = (random.nextInt((width) - (width/5 + 12))) + (width/5 + 5);
		this.y = (random.nextInt((height) - (width/5 + 20))) + (width/5 + 5);
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
//	public void paintIcon(Component c, Graphics g, int x, int y) {
//		Graphics2D g2 = (Graphics2D) g;
//		if (imageName.equals("")) {
//				m[i] = new Ellipse2D.Double(xm[i], ym[i], widthMarble, widthMarble);
//				g2.setColor(new Color(1f,0f,0f,1f));
//				g2.fill(m[i]);
//				g2.setColor(Color.black);
//				g2.draw(m[i]);
//			}
//		}
//		else {				
//			InputStream is = getClass().getResourceAsStream(imageName);
//			try {
//				img = ImageIO.read(is);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
////			BufferedImage bi = new BufferedImage(widthMarble, widthMarble, BufferedImage.TYPE_INT_ARGB);
////			Graphics2D g2 = img.createGraphics();
////			g2 = img.createGraphics();
////			
////			int xImg = img.getMinX();
////			int yImg = img.getMinY();
////			int width1 = img.getWidth();
////			int height1 = img.getHeight();
//				
//			BufferedImage bi = new BufferedImage(widthMarble, widthMarble, BufferedImage.TYPE_INT_ARGB);
//			g2 = img.createGraphics();
//			for(int i = 0; i < marbles; i++) {
//				//g2 = img.createGraphics();
////				BufferedImage bi = new BufferedImage(widthMarble, widthMarble, BufferedImage.TYPE_INT_ARGB);
////				g2 = img.createGraphics();
////				Ellipse2D.Double[] m = new Ellipse2D.Double[marbles];
//				
//				g2.setColor(Color.black);
////				g2.draw(m[i]);
////				g2.setColor(new Color(1f,0f,0f,1f));
////				g2.fill(m[i]);
//				g2.dispose();	
//				
//				m[i] = new Ellipse2D.Double(xm[i], ym[i], widthMarble, widthMarble);
//				g.setClip(m[i]);
//							
//				g.drawImage(img, (int)xm[i], (int)ym[i], widthMarble, widthMarble, null);
//			}	
//		}
//
//		g2 = (Graphics2D) g;
//		g2.setColor(new Color(0,0,0,0.1f));
//		g2.fill(p);
//		g2.setColor(new Color(0,0,0,0.3f));
//		g2.setStroke(new java.awt.BasicStroke(4));
//		g2.draw(p);
//		g2.draw(l1);
//		g2.draw(l2);
//		g2.draw(cur1);
//		g2.draw(cur2);
//		g2.draw(cur3);
//		
//	}
}
