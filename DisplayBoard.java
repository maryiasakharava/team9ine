/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * The type Display board.
 */
public class DisplayBoard extends JPanel{
	private JLabel[] number1, number2;
	private static JLabel playerTurn;
	private JButton menu ;
	private static JButton undo;
	private JButton[] pits1, pits2;
	private JButton m1, m2;
	private JLabel background;
	private static MancalaModel mancalaModel;
	private MancalaFormatter formatter;
    private ArrayList<Marble>[] marble;
    private String state;
    private TheSelectionMenu selectionMenu;

	/**
	 * Instantiates Display Board extending Jpanel
	 *
	 * @param mancalaModel the mancala model
	 * @param s            TheSelectionMenu oobject
	 */
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
		undo = new JButton("Undo: 3");
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

	/**
	 * Undo player move and updates text
	 */
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
		}
		else {
			undo.setText("Undo: " + (mancalaModel.getPlayer2Undo()));
		}
	}

	/**
	 * Player move
	 *
	 * @param i the
	 */
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

	/**
	 * Gets marble from Array
	 *
	 * @param i the index
	 * @return the marble in array at i index
	 */
	public ArrayList<Marble> getMarble(int i) {
		return marble[i];
	}

	/**
	 * Sets player marbles
	 */
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
		}

		for (Integer integer : clear) {
			int m;
			int n = integer;
			marble[n] = new ArrayList<>();
		}
	}

	/**
	 * Undo marbles from pit when undoing turn
	 */
	public void undoMarble() {
		ArrayList<Integer> change = mancalaModel.getChange();
		ArrayList<Integer> temp = mancalaModel.getClear();
		ArrayList<Integer> clear = new ArrayList<>();
		for(Integer t : temp) {
			if(!clear.contains(t)) {
				clear.add(t);
			}
		}
		
		if((change.isEmpty() && clear.isEmpty())) {
			return;
		}
		

		for( int i = 0; i < clear.size(); i++) {
			int m;
			int n = clear.get(i);	
			if(n<12) {
				m = mancalaModel.getTempInnerPits(n);
			}
			else {
				m = mancalaModel.getTempSidePits(n-12);
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

		for (int n : change) {
			int m = marble[n].size() - 1;
			if (!marble[n].isEmpty()) {
				marble[n].remove(m);
			}
		}	
	}

	/**
	 * Paint component.
	 */
	public void paintComponent() {
		removeAll();
		revalidate();
		repaint();
	
		background.add(this.formatter.addMainPanel(this), BorderLayout.CENTER);
		background.add(this.formatter.addHeadPanel(this), BorderLayout.NORTH);
		background.add(this.formatter.addButtonPanel(this), BorderLayout.SOUTH);
		this.add(background);
	}

	/**
	 * Sets MancalaFormatter.
	 *
	 * @param formatter the MancalaFormatter
	 */
	public void setFormatter(MancalaFormatter formatter) {
		this.formatter = formatter;
		this.formatter.setLayout(this);
		paintComponent();	
	}

	/**
	 * Sets number.
	 *
	 * @param n the n
	 */
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

	/**
	 * Sets number 1.
	 */
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

	/**
	 * Gets menu button.
	 *
	 * @return the menu button
	 */
	public JButton getMenuButton() {
		return menu;
	}

	/**
	 * Gets undo button.
	 *
	 * @return the undo button
	 */
	public JButton getUndoButton() {
		return undo;
	}

	/**
	 * Gets m1 button.
	 *
	 * @return the m1 button
	 */
	public JButton getM1Button() {
		return m1;
	}

	/**
	 * Gets m2 button.
	 *
	 * @return the m2 button
	 */
	public JButton getM2Button() {
		return m2;
	}

	/**
	 * Get jButton for pitsPlayer1
	 *
	 * @return the j button [ ]
	 */
	public JButton[] getPits1() {
		return pits1;
	}

	/**
	 * Get jButton for pitsPlayer2
	 *
	 * @return the j button [ ]
	 */
	public JButton[] getPits2() {
		return pits2;
	}

	/**
	 * Get number 1 j label [ ].
	 *
	 * @return the j label [ ]
	 */
	public JLabel[] getNumber1() {
		return number1;
	}

	/**
	 * Get number 2 j label [ ].
	 *
	 * @return the j label [ ]
	 */
	public JLabel[] getNumber2() {
		return number2;
	}

	/**
	 * Player turn j label.
	 *
	 * @return the j label
	 */
	public JLabel playerTurn() {
		return playerTurn;
	}

	/**
	 * Gets label background.
	 *
	 * @return the label background
	 */
	public JLabel getLabelBackground() {
		return background;
	}

	/**
	 * Sets background.
	 *
	 * @param background the background
	 */
	public void setBackground(JLabel background) {
		this.background = background;
	}

	/**
	 * Gets mancala model.
	 *
	 * @return the mancala model
	 */
	public MancalaModel getMancalaModel() {
		return mancalaModel;
	}

	/**
	 * Sets mancala model.
	 *
	 * @param mancalaModel the mancala model
	 */
	public void setMancalaModel(MancalaModel mancalaModel) {
		this.mancalaModel = mancalaModel;
		this.setNumber1();
	}

	/**
	 * Return current text label
	 *
	 * @param optionPane the option pane
	 * @param text       the text
	 * @return the button
	 */
	public JButton getButton(final JOptionPane optionPane, String text) {
		    final JButton b = new JButton(text);
		    ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        optionPane.setValue(b.getText());
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
