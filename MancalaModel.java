/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Mancala Model class
 */
public class MancalaModel {
	private PitModel[] innerPits;
	private PitModel[] sidePits;
	private PitModel[] tempInnerPits;
	private PitModel[] tempSidePits;
    private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
    private static int currentPlayer;
    private boolean gameOver;
    private boolean redoTurn;
    private static boolean lastStep;
    private int last;
    private static int player1Undo;
    private static int player2Undo;
    private ArrayList<Integer> change;
    private ArrayList<Integer> clear;
    private boolean check = false;

	/**
	 * Instantiates a new Mancala model.
	 *
	 * @param n the numberOfPits
	 */
	public MancalaModel(int n){
        this.currentPlayer = 0;
        innerPits = new PitModel[12];
        sidePits = new PitModel[2];
        tempInnerPits = new PitModel[12];
        tempSidePits = new PitModel[2];
        change = new ArrayList<>();
        clear = new ArrayList<>();
        last = -1;
        player1Undo = 3;
        player2Undo = 3;
        gameOver = false;
        lastStep = false;
        this.setInit(n);
    }

	/**
	 * Check player turn boolean.
	 *
	 * @param c the c
	 * @return the boolean
	 */
	public static boolean checkPlayerTurn(int c) {
    	if (c < 6) {
    		if(currentPlayer==2) {return false;}
    		else {
    			player2Undo = 3;
    			currentPlayer=2;
    			return true;
    		}
    	}
    	else {
    		if(currentPlayer==1) {return false;}
    		else {
    			player1Undo = 3;
    			currentPlayer=1;
    			return true;
    		}
    	}
    	
    }

	/**
	 * Sets init.
	 *
	 * @param n the n
	 */
	public void setInit(int n) {
    	for (int i = 0; i < 12; i++) {
    		PitModel p = new PitModel();
    		innerPits[i] = p;
    		innerPits[i].setMarbles(n);
    	}
    	for (int i = 0; i < 2; i++) {
    		PitModel p = new PitModel();
    		sidePits[i] = p;
    		sidePits[i].setMarbles(0);
    	}
    }

	/**
	 * Extra point int.
	 *
	 * @param c the c
	 * @return the int
	 */
	public int extraPoint(int c) {
    	if(c < 6 && (last < 6 && last >= 0)) {
    		if((innerPits[last].getMarbles() == 1) && (innerPits[11 - last].getMarbles() != 0)) {
    			int p = innerPits[11 - last].getMarbles() + innerPits[last].getMarbles();
    			sidePits[0].addMarbles(p);
    			int j = change.size()-1;
				change.remove(j);
    			clear.add(last);
    			clear.add(11 - last);
    			for (int i = 1; i <= p; i++) {
            		change.add(12);
            	}
    			innerPits[11 - last].clearPit();
    			innerPits[last].clearPit();
    			return p;
    		}
    	}
    	else if (c > 6 && last >= 6) {
    		if((innerPits[last].getMarbles() == 1) && innerPits[11 - last].getMarbles() != 0) {
    			int p = innerPits[11 - last].getMarbles() + innerPits[last].getMarbles();
    			sidePits[1].addMarbles(p);
    			int j = change.size()-1;
				change.remove(j);
    			clear.add(last);
    			clear.add(11 - last);
    			for (int i = 1; i <= p; i++) {
            		change.add(13);
            	}
    			innerPits[11 - last].clearPit();
    			innerPits[last].clearPit();
    			return p;
    		}
    	}
    	return 0;
    }

	/**
	 * Player move.
	 *
	 * @param c the number of MarblesPit
	 */
	public void playerMove(int c) {
    	int n = c, m = 0, c1 = c;
    	if(c>6) {n = c - 1;}
    	while(getMarblesPit(n)!=0 && checkPlayerTurn(n)) {
    		change = new ArrayList<>();
        	clear = new ArrayList<>();
    		this.saveUndo();
    		redoTurn = true;
    		lastStep = false;
        	for (int i = 1; i <= getMarblesPit(n); i++) {	
        		if((i+c1)%14 == 6) {
        			if(c1<6) {
        				m = sidePits[0].getMarbles() + 1;
            			sidePits[0].setMarbles(m);
            			change.add(12);
            			if (i == getMarblesPit(n)) {
            				lastStep = true;
            				currentPlayer=1;
            			}
        			}
        			else {
        				i--;
        				c1++;
        			}      			
        		}
        		else if((i+c1)%14 == 13) {
        			if(c1>6) {
        				m = sidePits[1].getMarbles() + 1;
            			sidePits[1].setMarbles(m);
            			change.add(13);
            			if (i == getMarblesPit(n)) {
            				lastStep = true;
            				currentPlayer=2;            				
            			}
        			}
        			else {
        				i--;
        				c1++;
        			}
        		}
        		else if((i+c1)%14 < 6) {
        			m = innerPits[(i+c1)%14].getMarbles() + 1;
            		innerPits[(i+c1)%14].setMarbles(m);
            		change.add((i+c1)%14);
            		if (i == getMarblesPit(n)) {
        				last = (i+c1)%14;
        				
        				int p = extraPoint(c);
        			}
            		
        		}
        		else {
        			m = innerPits[(i+c1)%14-1].getMarbles() + 1;
            		innerPits[(i+c1)%14-1].setMarbles(m);
            		change.add((i+c1)%14-1);
            		if (i == getMarblesPit(n)) {
        				last = (i+c1)%14-1;
        				int p = extraPoint(c);
        			}
        		}
        	}
        	innerPits[n].setMarbles(0);
        	clear.add(n);
        	this.check = this.checkWinner();     	
        	for (ChangeListener l : listeners) {l.stateChanged(new ChangeEvent(this));}
        	
    	}
    }

	/**
	 * Gets check.
	 *
	 * @return the boolean for check
	 */
	public boolean getCheck() {
    	return this.check;
    }

	/**
	 * Check who won the game
	 *
	 * @return the winner of the geame boolean
	 */
	public boolean checkWinner(){
        int count = 0;
        int m = 0;
        for (int i = 0; i <=5; i++){
        	if (innerPits[i].getMarbles() == 0)
        		++count;
        }
        if (count == 6 ){
        	for (int i = 6; i <=11; i++) {
        		m += innerPits[i].getMarbles();
        		sidePits[1].addMarbles(innerPits[i].getMarbles());
        		innerPits[i].clearPit();
        		clear.add(i);    		
        	}
        	for (int i = 1; i <= m; i++) {
        		change.add(13);
        	}
        	gameOver = true;
        	setMarbles(0);
        	return gameOver;
        }
        count = 0;
        for(int i = 6; i <=11; i++) {
        	if (innerPits[i].getMarbles() == 0)
        		++count;
        }
        if (count == 6) {
        	for (int i = 0; i <=5; i++) {
        		m += innerPits[i].getMarbles();
        		sidePits[0].addMarbles(innerPits[i].getMarbles());
        		innerPits[i].clearPit();
        		clear.add(i);
        	}
        	for (int i = 1; i <= m; i++) {
        		change.add(12);
        	}
        	gameOver = true;
        	setMarbles(0);
        	return gameOver;
        }
        gameOver = false;
        return gameOver;
    }

	/**
	 * End of the game boolean.
	 *
	 * @return the boolean
	 */
	public Boolean endofTheGame(){return gameOver;}

	/**
	 * Winner text Player A or Player B
	 *
	 * @return the Winning player Text
	 */
	public String winner(){
    	String w = "";
    	if(sidePits[0].getMarbles() > sidePits[1].getMarbles()) w = "Player A Won the game";
    	else if(sidePits[0].getMarbles() < sidePits[1].getMarbles()) w = "Player B Won the game";
    	else w = "THE GAME IS A TIE";
    	return w;
    }

	/**
	 * Attach changelisteners
	 *
	 * @param c the ChangeListener
	 */
	public void attach(ChangeListener c) {this.listeners.add(c);}


	/**
	 * Gets change.
	 *
	 * @return the change
	 */
	public ArrayList<Integer> getChange() {return change;}

	/**
	 * Gets clear.
	 *
	 * @return the clear
	 */
	public ArrayList<Integer> getClear() {return clear;}

	/**
	 * Get inner pits pit model [ ].
	 *
	 * @return the pit model [ ]
	 */
	public PitModel[] getInnerPits() {return innerPits;}

	/**
	 * Sets inner pits.
	 *
	 * @param innerPits the inner pits
	 */
	public void setInnerPits(PitModel[] innerPits) {this.innerPits = innerPits;}

	/**
	 * Get side pits pit model [ ].
	 *
	 * @return the pit model [ ]
	 */
	public PitModel[] getSidePits() {return sidePits;}

	/**
	 * Sets side pits.
	 *
	 * @param sidePits the side pits
	 */
	public void setSidePits(PitModel[] sidePits) {this.sidePits = sidePits;}

	/**
	 * Gets current player
	 *
	 * @return current player
	 */
	public int getPlayer() {return this.currentPlayer;}

	/**
	 * Redo turn
	 *
	 * @return redoTurn Boolean
	 */
	public boolean redoTurn() {return this.redoTurn;}

	/**
	 * Gets temp inner pits.
	 *
	 * @param i the
	 * @return the temp inner pits
	 */
	public int getTempInnerPits(int i) {return tempInnerPits[i].getMarbles();}

	/**
	 * Gets temp side pits.
	 *
	 * @param i the
	 * @return the temp side pits
	 */
	public int getTempSidePits(int i) {return tempSidePits[i].getMarbles();}

//    public void setUndoTheMove() {this.undoTheMove = 3;}

	/**
	 * Gets player marbles.
	 *
	 * @param i the number of marbles
	 * @return the marble count
	 */
	public int getPlayerMarbles(int i) {return sidePits[i].getMarbles();}

	/**
	 * Gets marbles pit.
	 *
	 * @param i the
	 * @return the marbles pit
	 */
	public int getMarblesPit(int i) {return innerPits[i].getMarbles();}

	/**
	 * Set marbles.
	 *
	 * @param num the num
	 */
	public void setMarbles(int num){
        for (PitModel p: innerPits) {p.addMarbles(num);}
    }

	/**
	 * Save undo.
	 */
	public void saveUndo(){
		for (int i = 0; i < 12; i++) {
			PitModel p = new PitModel();
			tempInnerPits[i] = p;
			try {
				tempInnerPits[i] = ((PitModel) innerPits[i].clone());
			} catch (CloneNotSupportedException e) {}
		}
		for (int i = 0; i < 2; i++) {
			PitModel p = new PitModel();
			tempSidePits[i] = p;
			try {
				tempSidePits[i] = ((PitModel) sidePits[i].clone());
			} catch (CloneNotSupportedException e) {}
		}
	}

	/**
	 * Check undo
	 *
	 * @return the checkUndo result
	 */
	public boolean checkUndo() {
		if(this.currentPlayer == 1) {
			if(lastStep == true) {
				if((player1Undo>0)) {
					player1Undo -= 1;
					return true;
				}
				else {return false;}
			}
			else {
				if((player2Undo>0)) {
					player2Undo -= 1;
					return true;
				}
				else {return false;}
			}	
		}
		else if (this.currentPlayer == 2) {
			if(lastStep == true) {
				if((player2Undo>0)) {
					player2Undo -= 1;
					return true;
				}
				else {return false;}
			}
			else {
				if((player1Undo>0)) {
					player1Undo -= 1;
					return true;
				}
				else {return false;}
			}	
		}
		
		return false;
	}

	/**
	 * Undo previous move.
	 */
	public void undoPrevMove(){
		if ((this.redoTurn() == true) && (checkUndo() == true)){
			if(this.redoTurn()==true) {this.redoTurn = false;}

			for (int i = 0; i < 12; i++) {
				try {
					innerPits[i] = ((PitModel) tempInnerPits[i].clone());
				} catch (CloneNotSupportedException e) {}
			}
			for (int i = 0; i < 2; i++) {
				try {
					sidePits[i] = ((PitModel) tempSidePits[i].clone());
				} catch (CloneNotSupportedException e) {}
			}
			if(lastStep == false) {
				if(currentPlayer==1) {currentPlayer+=1;}
				else if (currentPlayer ==2) {currentPlayer-=1;}			
			}
			
			lastStep = false;
			this.check = this.checkWinner();
			for (ChangeListener l: this.listeners){
				l.stateChanged(new ChangeEvent(this));
			}

		}
		else {return;}
	}

	/**
	 * Gets player 1 undo.
	 *
	 * @return the player 1 undo
	 */
	public static int getPlayer1Undo() {return player1Undo;}

	/**
	 * Gets player 2 undo.
	 *
	 * @return the player 2 undo
	 */
	public static int getPlayer2Undo() {return player2Undo;}

}
