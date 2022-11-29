import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PitModel implements Cloneable{
	private int marbles;

	private MancalaModel model;
	private int ID;
	
	public PitModel(int marbles, int id, MancalaModel model){
		this.marbles = marbles;
		this.model = model;
		this.ID = id;
	}

	public PitModel(){

	}
	public int getID(){
		return this.ID;
	}
	public void addMarbles(int marbles){
		this.marbles +=marbles;
	}
	public int getMarbles(){
		return marbles;
	}
	public void setMarbles(int marbles) {
		this.marbles = marbles;
	}
	public void clearPit(){
		marbles =0;
	}
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
