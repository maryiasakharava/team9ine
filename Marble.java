import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Marble Class for Board class
 */
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

	/**
	 * Marble constructor
	 *
	 * @param width  the width
	 * @param height the height
	 */
	public Marble(int width, int height) {
		this.x = (random.nextInt((width) - (width/5 + 12))) + (width/5 + 5);
		this.y = (random.nextInt((height) - (width/5 + 20))) + (width/5 + 5);
	}

	/**
	 * Sets image name.
	 *
	 * @param imageName the image name
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * Gets x.
	 *
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets y.
	 *
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}
}
