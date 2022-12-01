/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

import java.util.Random;

/**
 * Marble Class for Board class
 */
public class Marble {
	private int x;
	private int y;
	private Random random = new Random();
	
	/**
	 * Marble constructor
	 *
	 * @param width  the width
	 * @param height the height
	 */
	public Marble(int width, int height) {
		this.x = (random.nextInt((width) - (width/5 + 15))) + (width/5 + 5);
		this.y = (random.nextInt((height) - (width/5 + 20))) + (width/5 + 5);
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
