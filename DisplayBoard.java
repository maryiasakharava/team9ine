import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DisplayBoard extends JPanel {
	private JLabel[] number1, number2;
	private JLabel playerTurn;
	private JButton menu, undo ;
	private JButton[] pits1, pits2;
	private JButton m1, m2;
	
	private MancalaFormatter formatter;

	public DisplayBoard() {
		menu = new JButton("Menu");
		menu.addActionListener(e -> {
			SwingUtilities.getWindowAncestor(this).dispose();
			new TheSelectionMenu().displayMainMenu();
		});

		int undoTime = 3;
		undo = new JButton("Undo: " + undoTime);
		number1 = new JLabel[8];
		number2 = new JLabel[8];
		pits1 = new JButton[6];
		pits2 = new JButton[6];
		m1 = new JButton();
		m2 = new JButton();
		playerTurn = new JLabel("PLAYER A TURN");

		number1[0] = new JLabel("⬅ Player B", SwingConstants.CENTER);
		number2[0] = new JLabel("Player A ➞", SwingConstants.CENTER);
		
		for (int i = 0; i < pits1.length; i++) {
			pits1[i] = new JButton();
			pits2[i] = new JButton();
		}
		
		for (int i = 1; i < number1.length; i++) {
			number1[i] = new JLabel("4", SwingConstants.CENTER);
			number2[i] = new JLabel("4", SwingConstants.CENTER);
			if (i == number1.length-1 ) {
				number1[i] = new JLabel("0", SwingConstants.CENTER);
				number2[i] = new JLabel("0", SwingConstants.CENTER);
			}
		}
		
	}
	
	public void setFormatter(MancalaFormatter formatter) {
		this.formatter = formatter;
		Display(formatter);
	}
	
	public void Display(MancalaFormatter formatter) {
		formatter.setLayout();
		this.add(formatter.addHeadPanel(), BorderLayout.NORTH);
		this.add(formatter.addButtonPanel(), BorderLayout.SOUTH);
		this.add(formatter.addMainPanel(), BorderLayout.CENTER);
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
}
