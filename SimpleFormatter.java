/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * SimpleFormatter class the implements MancalaFormatter
 */
public class SimpleFormatter implements MancalaFormatter {
	private JPanel buttonPanel, mainPanel, headPanel, numberStone1, numberStone2, leftP, rightP, 
				   mainboard, board, pitPanel1, pitPanel2, pitPanel3, pitPanel4;
	private JLabel headField, left, right, background;
	private JLabel[] letter1, letter2;
	private Icon theBoard;

	/**
	 * SimpleFormatter constructor
	 */
	public SimpleFormatter() {
		mainboard = new JPanel();
		pitPanel1 = new JPanel();
		pitPanel2 = new JPanel();
		pitPanel3 = new JPanel();
		pitPanel4 = new JPanel();
		board = new JPanel();
		buttonPanel = new JPanel();
		mainPanel = new JPanel();
		headPanel = new JPanel();
		leftP = new JPanel();
		rightP = new JPanel();
		numberStone1 = new JPanel();
		numberStone2 = new JPanel();
		headField = new JLabel("MANCALA");
		left = new JLabel();
		right = new JLabel();
		letter1 = new JLabel[6];
		letter2 = new JLabel[6];

		theBoard = new Board(540, 260, "");
		background = new JLabel(theBoard);
	}
	/**
	 * Set layout for displayboard
	 */
	public void setLayout(DisplayBoard displayboard) {	
		
		displayboard.getLabelBackground().setLayout(new BorderLayout());
		displayboard.getLabelBackground().setOpaque(false);
		Dimension d = new Dimension(630,345);
		displayboard.getLabelBackground().setMinimumSize(d);
		displayboard.getLabelBackground().setMaximumSize(d);
		displayboard.getLabelBackground().setPreferredSize(d);
		displayboard.setBackground(RndColor());
		
		d = new Dimension(630,350);
		displayboard.setMinimumSize(d);
		displayboard.setMaximumSize(d);
		displayboard.setPreferredSize(d);

	}
	/**
	 * Add Header panel for displayboard
	 */
	public JPanel addHeadPanel(DisplayBoard displayboard) {			
		headField.setOpaque(false);
		headField.setFont(new Font("Serif", Font.PLAIN, 25));
		headField.setFont(new Font(headField.getName(), Font.BOLD, 25));
		headPanel.setOpaque(false);
		headPanel.add(headField);
		return headPanel;
	}
	/**
	 * Add button panel for displayboard
	 */
	public JPanel addButtonPanel(DisplayBoard displayboard) {	
		buttonPanel.setLayout(new BorderLayout());
		
		this.setButton(displayboard.getMenuButton(), Color.white, Color.black, false, true, 0);
		this.setButton(displayboard.getUndoButton(), Color.white, Color.black, false, true, 0);
		displayboard.playerTurn().setHorizontalAlignment(JLabel.CENTER);
		displayboard.playerTurn().setFont(new Font("MONOSPACED", Font.BOLD, 18));
		
		buttonPanel.add(displayboard.getMenuButton(),BorderLayout.WEST);
		buttonPanel.add(displayboard.getUndoButton(),BorderLayout.EAST);
		buttonPanel.add(displayboard.playerTurn(),BorderLayout.CENTER);
		
		buttonPanel.setOpaque(false);

		return buttonPanel;
	}
	/**
	 * Add main panel for displayboard
	 */
	public JPanel addMainPanel(DisplayBoard displayboard) {			
		mainPanel.setLayout(new BorderLayout());
		
		left.setText(transformStringToHtml("MANCALA B"));
		left.setFont(new Font("MONOSPACED", Font.BOLD, 15));
		left.setForeground(Color.black);
		leftP.setBorder(BorderFactory.createEmptyBorder(35,10,0,0));
		leftP.add(left);
		
		right.setText(transformStringToHtml("MANCALA A"));
		right.setFont(new Font("MONOSPACED", Font.BOLD, 15));
		right.setForeground(Color.black);
		rightP.setBorder(BorderFactory.createEmptyBorder(35,5,0,5));
		rightP.add(right);
		
		numberStone1.setLayout(new GridLayout(1, 8));
		numberStone2.setLayout(new GridLayout(1, 8));
		
		for (int i = 0; i < displayboard.getNumber1().length; i++) {
			numberStone2.add(displayboard.getNumber2()[displayboard.getNumber2().length-1 - i]);	
			numberStone1.add(displayboard.getNumber1()[i]);
		}

		mainboard.setLayout(null);
		board.setLayout(null);
		
		for (int i = 0; i < letter1.length; i++) {
			int j = letter1.length-i;
			letter1[i] = new JLabel("B" + j, SwingConstants.CENTER);
			j = i + 1;
			letter2[i] = new JLabel("A" + j, SwingConstants.CENTER);
		}
		
		for (int i = 0; i < displayboard.getPits1().length; i++) {
			displayboard.getPits1()[i].setIcon(new Pit(50, 75, displayboard.getMarble(i)));
			displayboard.getPits2()[i].setIcon(new Pit(50, 75, displayboard.getMarble(i+6)));
			
			this.setButton(displayboard.getPits1()[i], Color.white, Color.black, false, false, 0);
			this.setButton(displayboard.getPits2()[i], Color.white, Color.black, false, false, 0);
		}
		
		for (int i = 0; i < 6; i++) {
			pitPanel1.add(displayboard.getPits1()[i]);
			displayboard.getPits1()[i].setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
			pitPanel2.add(displayboard.getPits2()[5-i]);
			displayboard.getPits2()[5-i].setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
			pitPanel3.add(letter1[i]);
			letter1[i].setBorder(BorderFactory.createEmptyBorder(0,20,0,25));
			pitPanel4.add(letter2[i]);
			letter2[i].setBorder(BorderFactory.createEmptyBorder(0,20,0,25));		
		}
		
		setComponentOpaque(pitPanel1);
		setComponentOpaque(pitPanel2);
		setComponentOpaque(pitPanel3);
		setComponentOpaque(pitPanel4);
		
		pitPanel1.setBounds(0, 0, 400, 90);
		pitPanel2.setBounds(0, 0, 400, 85);
		pitPanel3.setBounds(0, 0, 400, 20);
		pitPanel4.setBounds(0, 0, 400, 20);
		
		pitPanel2.setLocation(0,0);
		pitPanel1.setLocation(0,135);
		pitPanel3.setLocation(0,80);
		pitPanel4.setLocation(0,115);		
		
		board.add(pitPanel2);
		board.add(pitPanel3);
		board.add(pitPanel4);
		board.add(pitPanel1);
		
		board.setBounds(0, 0, 405, 220);
		board.setLocation(75,20);

		displayboard.getM1Button().setIcon(new Pit(50, 210, displayboard.getMarble(12)));
		displayboard.getM2Button().setIcon(new Pit(50, 210, displayboard.getMarble(13)));
		
		this.setButton(displayboard.getM1Button(), Color.white, Color.black, false, false, 0);
		this.setButton(displayboard.getM2Button(), Color.white, Color.black, false, false, 0);
		
		displayboard.getM1Button().setBounds(0, 0, 60, 215);
		displayboard.getM2Button().setBounds(0, 0, 60, 215);
		displayboard.getM2Button().setLocation(20,24);
		displayboard.getM1Button().setLocation(470,24);
		
		numberStone1.setBounds(0, 0, 520, 20);
		numberStone2.setBounds(0, 0, 520, 20);
		
		numberStone2.setLocation(15,5);
		numberStone1.setLocation(15,240);
		
		background.setBounds(0, 0, 550, 270);		
		background.setLocation(0,0);	

		setComponentOpaque(background);
		setComponentOpaque(numberStone1);
		setComponentOpaque(numberStone2);
		setComponentOpaque(board);
		
		mainboard.add(displayboard.getM1Button());
		mainboard.add(displayboard.getM2Button());
		mainboard.add(numberStone2);
		mainboard.add(numberStone1);
		mainboard.add(board);
		mainboard.add(background);
		
		setComponentOpaque(mainboard);
		setComponentOpaque(leftP);
		setComponentOpaque(rightP);
		
		mainPanel.add(mainboard,BorderLayout.CENTER);		
		mainPanel.add(leftP,BorderLayout.WEST);
		mainPanel.add(rightP,BorderLayout.EAST);
		
		mainPanel.setOpaque(false);
		
		return mainPanel;
	}

	/**
	 * Sets component opaque
	 *
	 * @param c the JComponent
	 */
	public static void setComponentOpaque(JComponent c) {
		c.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		c.setOpaque(false);
	}

	/**
	 * Sets button.
	 *
	 * @param b      the JButton
	 * @param backCo the back color
	 * @param ForeCo the fore color
	 * @param b1     the setOpaque true/false
	 * @param b2     the setBorderPainted true/false
	 * @param op     the op
	 */
	public void setButton(JButton b, Color backCo, Color ForeCo, boolean b1, boolean b2, int op) {
		b.setBackground(backCo);
		b.setForeground(ForeCo);
		b.setOpaque(b1);			
		b.setBorderPainted(b2);
		b.setFont(new Font(b.getName(), Font.BOLD, 15));
		b.setBorder(BorderFactory.createBevelBorder(op));
		b.setFocusPainted(false);
	}

	/**
	 * Generates a random color (RGB)
	 *
	 * @return the Random Color
	 */
	public Color RndColor() {
		Random random = new Random();
		int r = (random.nextInt(128)+127);
		int g = (random.nextInt(128)+127);
		int b = (random.nextInt(128)+127);
		return new Color(r,g,b);
	}
}
