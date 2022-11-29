import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

public class DisplayBoard extends JPanel{
	private JLabel[] number1, number2;
	private static JLabel playerTurn;
	private JButton menu ;
	private static JButton undo;
	private JButton[] pits1, pits2;
	private JButton m1, m2;
	private JLabel background;
	private int undoTime;
	private static MancalaModel mancalaModel;
	private MancalaFormatter formatter;
//	private PitModel[] innerPits;
//    private PitModel[] sidePits;
    private ArrayList<Marble>[] marble;
    private String state;
    private TheSelectionMenu selectionMenu;
	
	public DisplayBoard(MancalaModel mancalaModel, TheSelectionMenu s) {
		this.mancalaModel = mancalaModel;
		this.selectionMenu = s;
		this.marble = new ArrayList[14];     
        for (int i = 0; i < 14; i++) {
        	marble[i] = new ArrayList<>();
        	if (i > 11) {
        		for(int j = 0; j < 0; j++) {
            		marble[i].add(new Marble(50, 210));
            	}
        	}
        	else {
        		for(int j = 0; j < mancalaModel.getMarblesPit(0); j++) {
            		marble[i].add(new Marble(50, 75));
            	}
        	}
        }
		menu = new JButton("Menu");
		
		menu.addActionListener(e -> {
			SwingUtilities.getWindowAncestor(this).dispose();
			selectionMenu.displayMainMenu();
		});
		undoTime = 3;
		undo = new JButton("Undo: " + undoTime);
		number1 = new JLabel[8];
		number2 = new JLabel[8];
		for (int i = 1; i < number1.length; i++) {
			number1[i] = new JLabel("0", SwingConstants.CENTER);
			number2[i] = new JLabel("0", SwingConstants.CENTER);
		}
		this.setNumber1();
		pits1 = new JButton[6];
		pits2 = new JButton[6];
		m1 = new JButton();
		m2 = new JButton();
		playerTurn = new JLabel("PLAYER A TURN");
		background = new JLabel();

		number2[0] = new JLabel("⬅ Player B", SwingConstants.CENTER);
		number1[0] = new JLabel("Player A ➞", SwingConstants.CENTER);
		this.setNumber1();
		
		for (int i = 0; i < pits1.length; i++) {		
			pits1[i] = new JButton();		
			pits2[i] = new JButton();			
		}	
		
		for (int i = 0; i < pits1.length; i++) {
			final int j = i;
			final int t = i+7;
			pits1[i].addActionListener(e-> { move(j);});
			pits2[i].addActionListener(e-> { move(t);});
		}

		undo.addActionListener(e-> { Undo();});
		
		this.mancalaModel.attach(event -> {
			this.setNumber1();
			if(state.equals("move")) {
				this.setMarble();
			}
			else if(state.equals("undo")) {
				this.undoMarble();
			}			
			paintComponent();
			System.out.println(mancalaModel.getCheck());
			if(mancalaModel.getCheck() == true) {
				String response = mancalaModel.winner();
				JOptionPane optionPane = new JOptionPane();
			    optionPane.setMessage(response);
			    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
			    JButton button = getButton(optionPane, "Go to menu");
			    button.addActionListener(e -> {
					SwingUtilities.getWindowAncestor(this).dispose();
					SwingUtilities.getWindowAncestor(optionPane).dispose();
					selectionMenu.displayMainMenu();
				});
			    JButton button1 = getButton(optionPane, "Cancel");
			    button1.addActionListener(e -> {
					SwingUtilities.getWindowAncestor(optionPane).dispose();
				});
			    optionPane.setOptions(new Object[] { button, button1 });
			    JDialog dialog = optionPane.createDialog(null, "Result");
			    dialog.setVisible(true);
			}
		});
	
	}
	
	public void Undo() {
		state = "undo";
		mancalaModel.undoPrevMove();
		if(mancalaModel.getPlayer() == 1) {
			playerTurn.setText("PLAYER A TURN");	
		}
		else {
			playerTurn.setText("PLAYER B TURN");
		}
		if(mancalaModel.getPlayer1Undo() < mancalaModel.getPlayer2Undo()) {
			undo.setText("Undo: " + (mancalaModel.getPlayer1Undo()));
			//if(mancalaModel.getPlayer1Undo()<0) {undo.setText("Undo: 0");}
		}
		else {
			undo.setText("Undo: " + (mancalaModel.getPlayer2Undo()));
			//if(mancalaModel.getPlayer2Undo()<0) {undo.setText("Undo: 0");}
		}
	}
	
	public void move(int i) {
		state = "move";
		mancalaModel.playerMove(i);
		if(mancalaModel.getPlayer() == 1) {
			playerTurn.setText("PLAYER A TURN");
		}
		else {
			playerTurn.setText("PLAYER B TURN");
		}
		if(mancalaModel.getPlayer1Undo() < mancalaModel.getPlayer2Undo()) {
			undo.setText("Undo: " + (mancalaModel.getPlayer1Undo()));
		}
		else {
			undo.setText("Undo: " + (mancalaModel.getPlayer2Undo()));
		}
	}
	
	public ArrayList<Marble> getMarble(int i) {
		return marble[i];
	}
	
	public void setMarble() {
		ArrayList<Integer> change = mancalaModel.getChange();
		ArrayList<Integer> clear = mancalaModel.getClear();
		
		for( int i = 0; i < change.size(); i++) {
			int m;
			int n = change.get(i);
			if(n<12) {
				m = mancalaModel.getMarblesPit(n);
			}
			else {
				m = mancalaModel.getPlayerMarbles(n-12);
			}
			if (n > 11) {
            	marble[n].add(new Marble(50, 210));
        	}
			else {
	        	marble[n].add(new Marble(50, 75));
			}
			System.out.println(m);
//			int m;
//			int n = change.get(i);	
//			if(n<12) {				
//				m = mancalaModel.getMarblesPit(n) - mancalaModel.getTempInnerPits(n);
//			}
//			else {
//				m = mancalaModel.getPlayerMarbles(n-12) - mancalaModel.getTempSidePits(n-12);
//			}
//			if (n > 11) {
//				System.out.println(m);
//        		for(int j = 0; j < m; j++) {
//            		marble[n].add(new Marble(50, 210));
//            	}
//        	}
//			else {
//				System.out.println(m);
//				for(int j = 0; j < m; j++) {
//	        		marble[n].add(new Marble(50, 75));
//	        	}
//			}
		}
		
		System.out.println();
		
		for( int i = 0; i < clear.size(); i++) {
			int m;
			int n = clear.get(i);
			marble[n] = new ArrayList<>();			
		}
	}
	
	public void undoMarble() {
		ArrayList<Integer> change = mancalaModel.getChange();
		ArrayList<Integer> temp = mancalaModel.getClear();
		ArrayList<Integer> clear = new ArrayList<>();
		System.out.println(change.toString());
		System.out.println(clear.toString());
		for(Integer t : temp) {
			if(!clear.contains(t)) {
				clear.add(t);
			}
		}
		
		if((change.isEmpty() && clear.isEmpty())) {
			return;
		}
		
//		System.out.println(change.toString());
					
		for( int i = 0; i < clear.size(); i++) {
			int m;
			int n = clear.get(i);	
			if(n<12) {
				m = mancalaModel.getTempInnerPits(n);
//				m = mancalaModel.getMarblesPit(n) - mancalaModel.getTempInnerPits(n);
			}
			else {
				m = mancalaModel.getTempSidePits(n-12);
//				m = mancalaModel.getPlayerMarbles(n-12) - mancalaModel.getTempSidePits(n-12);
			}
			if (n > 11) {
        		for(int j = 0; j < m; j++) {
            		marble[n].add(new Marble(50, 210));
            	}
        	}
			else {
				for(int j = 0; j < m; j++) {
	        		marble[n].add(new Marble(50, 75));
	        	}
			}
		}
		System.out.println(clear.toString());
		for( int i = 0; i < change.size(); i++) {
			int n = change.get(i);
			int m = marble[n].size()-1;
			System.out.println(m);
			if(!marble[n].isEmpty()) {
				marble[n].remove(m);
			}
			
		}	
	}	
	
	public void paintComponent() {
		removeAll();
		revalidate();
		repaint();
	
		background.add(this.formatter.addMainPanel(this), BorderLayout.CENTER);
		background.add(this.formatter.addHeadPanel(this), BorderLayout.NORTH);
		background.add(this.formatter.addButtonPanel(this), BorderLayout.SOUTH);
		this.add(background);
	}
	
	public void setFormatter(MancalaFormatter formatter) {
		this.formatter = formatter;
		this.formatter.setLayout(this);
		paintComponent();	
	}
	
	public void setNumber(int n) {
		for (int i = 1; i < number1.length; i++) {
			number1[i] = new JLabel(String.valueOf(n), SwingConstants.CENTER);
			number2[i] = new JLabel(String.valueOf(n), SwingConstants.CENTER);
			if (i == number1.length-1 ) {
				number1[i] = new JLabel("0", SwingConstants.CENTER);
				number2[i] = new JLabel("0", SwingConstants.CENTER);
			}
		}
	}
	public void setNumber1() {
		for (int i = 1; i < number1.length-1; i++) {
			int n = mancalaModel.getMarblesPit(i-1);
			number1[i].setText(String.valueOf(n));
			n = mancalaModel.getMarblesPit(i+6-1);
			number2[i].setText(String.valueOf(n));
		}
		number1[number1.length-1].setText(String.valueOf(mancalaModel.getPlayerMarbles(0)));
		number2[number1.length-1].setText(String.valueOf(mancalaModel.getPlayerMarbles(1)));
	}
	public JButton getMenuButton() {
		return menu;
	}
	public JButton getUndoButton() {
		return undo;
	}
	public JButton getM1Button() {
		return m1;
	}
	public JButton getM2Button() {
		return m2;
	}
	public JButton[] getPits1() {
		return pits1;
	}
	public JButton[] getPits2() {
		return pits2;
	}
	public JLabel[] getNumber1() {
		return number1;
	}
	public JLabel[] getNumber2() {
		return number2;
	}
	public JLabel playerTurn() {
		return playerTurn;
	}

	public JLabel getLabelBackground() {
		return background;
	}

	public void setBackground(JLabel background) {
		this.background = background;
	}
	public int getUndoTime() {
		return undoTime;
	}

	public void setUndoTime(int undoTime) {
		this.undoTime = undoTime;
	}
	
	
	public MancalaModel getMancalaModel() {
		return mancalaModel;
	}

	public void setMancalaModel(MancalaModel mancalaModel) {
		this.mancalaModel = mancalaModel;
		this.setNumber1();
	}
	
	 public JButton getButton(final JOptionPane optionPane, String text) {
		    final JButton b = new JButton(text);
		    ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        // Return current text label, instead of argument to method
		        optionPane.setValue(b.getText());
		        System.out.println(b.getText());
		      }
		    };

		    b.setBackground(Color.white);
			b.setForeground(Color.black);
			b.setOpaque(false);			
			b.setFont(new Font(b.getName(), Font.BOLD, 15));
			b.setFocusPainted(true);
		    return b;
		  }

}
