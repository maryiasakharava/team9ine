/**
 * The type Pit model.
 */
public class PitModel implements Cloneable{
	private int marbles;

	private MancalaModel model;
	private int ID;

	/**
	 * PitModel Constructor
	 *
	 * @param marbles the marbles
	 * @param id      the id
	 * @param model   the MancalaModel
	 */
	public PitModel(int marbles, int id, MancalaModel model){
		this.marbles = marbles;
		this.model = model;
		this.ID = id;
	}

	/**
	 * PitModel Constructor x2 no args
	 */
	public PitModel(){

	}

	/**
	 * Get id int
	 *
	 * @return the int
	 */
	public int getID(){
		return this.ID;
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
