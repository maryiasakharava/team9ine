import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaModel {
    private ArrayList<Pit> innerPits;
    private ArrayList<BigPits> sidePits;
    private ArrayList<Pit> tempInnerPits;
    private ArrayList<BigPits> tempSidePits;
    private ArrayList<ChangeListener> listener;
    private int currentPlayer;
    private int undoTheMove;
    private boolean gameOver;
    private boolean redoTurn;
    private String rowPickedLast;

    public MancalaModel(){
        this.currentPlayer = 1;
        innerPits = new ArrayList<Pit>();
        sidePits = new ArrayList<BigPits>();
        listener = new ArrayList<ChangeListener>();
        undoTheMove = 3;
        gameOver = false;
        rowPickedLast = "top";
    }

    public int getPlayer(){
        return this.currentPlayer;
    }
    public boolean redoTurn(){
        return this.redoTurn;
    }
    public boolean player2(){
        return this.currentPlayer==2;
    }
    public void setUndoTheMove(){
        this.undoTheMove = 3;
    }
    public void addSidePit(BigPits b){
        sidePits.add(b);
    }
    public int getPlayerMarbles(int i){
        return sidePits.get(i).getMarbles();
    }
    public int getUndoTheMove(){
        return this.undoTheMove;
    }
    public boolean checkUndoTheMove(){
        if (this.undoTheMove>0){
            return true;
        }
        else{
            return false;
        }
    }
    public int getMarblesPit(int i){
        return innerPits.get(i).getMarbles();
    }
    public void setMarbles(int num){
        for (Pit p: innerPits){
            p.addMarbles(num);
        }
        for(ChangeListener l:this.listener){
            l.stateChanged(new ChangeEvent(this));
        }
    }

public void saveUndo(){
        tempInnerPits = new ArrayList<Pit>();
    for (Pit p : innerPits) {
        tempInnerPits.add(p.clone());
    }
    tempSidePits = new ArrayList<BigPits>();
    for (BigPits b : sidePits){
        tempSidePits.add(b.clone());
    }
}

//add PlayerMove method

public void undoPrevMove(){
        if (((this.getLastMove().equals("bot") && this.currentPlayer ==1) ||(this.getLastMove().equals("top") && this.currentPlayer == 2)) || this.redoTurn() == true){
            if(this.redoTurn()==true){
                if(currentPlayer==1){
                    currentPlayer+=1;
                }
                else if (currentPlayer ==2){
                    currentPlayer-=1;
                }
                this.redoTurn = false;
            }
            undoTheMove -=1;
            innerPits = new ArrayList<Pit>();
            for (Pit p: tempInnerPits){
                innerPits.add(p.clone());
            }
            sidePits = new ArrayList<BigPits>();
            for (BigPits b: tempSidePits){
                sidePits.add(b.clone());
            }
            if(currentPlayer==1){
                currentPlayer+=1;
            }
            else if (currentPlayer ==2){
                currentPlayer-=1;
            }
            for (ChangeListener l: this.listener){
                l.stateChanged(new ChangeEvent(this));
            }
    }
        else{
            return;
        }
}
public String getLastMove(){
        return this.rowPickedLast;
}

public void attach(ChangeListener listener){
        this.listener.add(listener);
}

public void addPit(Pit p){
        innerPits.add(p);
    for (ChangeListener l : this.listener) {
        l.stateChanged(new ChangeEvent(this));
    }
}

public Boolean endofTheGame(){
        return gameOver;
}

public String winner(){
    String w = "";
    if(sidePits.get(0).getMarbles() > sidePits.get(1).getMarbles())
        w = "Player 2 Won the game";
    else if(sidePits.get(0).getMarbles() < sidePits.get(1).getMarbles())
        w = "Player 1 Won the game";
    else
        w = "THE GAME IS A TIE";

    return w;
}
public void checkWinner(){
        int count = 0;
    for (int i = 0; i <=5; i++){
        if (innerPits.get(i).getMarbles() == 0)
            ++count;
    }
    if (count ==6 ){
        for (int i = 6; i <=11; i++) {
            sidePits.get(1).addMarbles(innerPits.get(i).getMarbles());
            innerPits.get(i).clearPit();
        }
        for(ChangeListener l : this.listener) {
            l.stateChanged(new ChangeEvent(this));
        }
        gameOver = true;
        setMarbles(0);
    }
    count = 0;
    for(int i = 6; i <=11; i++) {
        if (innerPits.get(i).getMarbles() == 0)
            ++count;
    }
    if (count == 6) {
        for (int i = 0; i <=5; i++) {
            sidePits.get(0).addMarbles(innerPits.get(i).getMarbles());
            innerPits.get(i).clearPit();
        }
        for(ChangeListener l : this.listener) {
            l.stateChanged(new ChangeEvent(this));
        }
        gameOver = true;
    }
    gameOver = false;
}
}
