/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

/**
 * The type Pit model.
 */
public class PitModel implements Cloneable{
	private int marbles;

	/**
	 * PitModel Constructor x2 no args
	 */
	public PitModel(){

	}

	/**
	 * Add marbles
	 *
	 * @param marbles number of marbles
	 */
	public void addMarbles(int marbles){
		this.marbles +=marbles;
	}

	/**
	 * Get marbles count
	 *
	 * @return the number of marbles
	 */
	public int getMarbles(){
		return marbles;
	}

	/**
	 * Sets number of marbles
	 *
	 * @param marbles the marbles
	 */
	public void setMarbles(int marbles) {
		this.marbles = marbles;
	}

	/**
	 * Clear pits
	 */
	public void clearPit(){
		marbles =0;
	}

	/**
	 * Is empty boolean.
	 *
	 * @return true or false
	 */
	public boolean isEmpty(){
		if (marbles ==0){
			return true;
		}
		else{
			return false;
		}
	}

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}
