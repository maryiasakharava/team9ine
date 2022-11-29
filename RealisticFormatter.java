import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Realistic formatter for Board realistic option
 */
public class RealisticFormatter implements MancalaFormatter{
	private JPanel buttonPanel, mainPanel, headPanel, numberStone1, numberStone2, leftP, rightP, 
	   mainboard, board, pitPanel1, pitPanel2, pitPanel3, pitPanel4;
	private JLabel headField, left, right, background;
	private JLabel[] letter1, letter2;
	private Icon pit;
	private Icon theBoard;
	private String marbleName = "stone2.png";
	//private DisplayBoard displayboard;

	/**
	 * Realistic formatter constructor
	 */
	public RealisticFormatter() {
		//this.displayboard = displayboard;
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
		theBoard = new Board(540, 260, "board_Bg.jpg");
		background = new JLabel(theBoard);	
	}

	/**
	 * set layout for displayBoard
	 */
	public void setLayout(DisplayBoard displayboard) {
		
		ImageIcon img1 = new ImageIcon(this.getClass().getResource("bg.jpg"));
		img1.setImage(img1.getImage().getScaledInstance(630, 345, Image.SCALE_DEFAULT));
		displayboard.setLayout(new FlowLayout());
		
		displayboard.getLabelBackground().setIcon(img1);
		displayboard.getLabelBackground().setOpaque(true);
		displayboard.getLabelBackground().setLayout(new BorderLayout());

		Dimension d = new Dimension(630,350);
		
		displayboard.setMinimumSize(d);
		displayboard.setMaximumSize(d);
		displayboard.setPreferredSize(d);
	}

	/**
	 * add Header Panel for displayBoard
	 */
	public JPanel addHeadPanel(DisplayBoard displayboard) {			
		headField.setOpaque(false);
		headField.setFont(new Font("Serif", Font.PLAIN, 25));
		headField.setFont(new Font(headField.getName(), Font.BOLD, 25));
		headField.setForeground(Color.white);
		headPanel.setOpaque(false);
		headPanel.add(headField);
		return headPanel;
	}

	/**
	 * add Button Panel for displayBoard
	 */
	public JPanel addButtonPanel(DisplayBoard displayboard) {	
		buttonPanel.setLayout(new BorderLayout());
		
		this.setButton(displayboard.getMenuButton(), Color.white, Color.white, false, false, 0);
		this.setButton(displayboard.getUndoButton(), Color.white, Color.white, false, false, 0);
		displayboard.playerTurn().setHorizontalAlignment(JLabel.CENTER);
		displayboard.playerTurn().setFont(new Font("MONOSPACED", Font.BOLD, 18));
		displayboard.playerTurn().setForeground(Color.white);
		
		buttonPanel.add(displayboard.getMenuButton(),BorderLayout.WEST);
		buttonPanel.add(displayboard.getUndoButton(),BorderLayout.EAST);
		buttonPanel.add(displayboard.playerTurn(),BorderLayout.CENTER);
		
		buttonPanel.setOpaque(false);

		return buttonPanel;
	}

	/**
	 * add Main Panel to displayboard
	 */
	public JPanel addMainPanel(DisplayBoard displayboard) {			
		mainPanel.setLayout(new BorderLayout());
		
		left.setText(transformStringToHtml("MANCALA B"));
		left.setFont(new Font("MONOSPACED", Font.BOLD, 15));
		left.setForeground(Color.white);
		leftP.setBorder(BorderFactory.createEmptyBorder(35,10,0,0));
		leftP.add(left);
		
		right.setText(transformStringToHtml("MANCALA A"));
		right.setFont(new Font("MONOSPACED", Font.BOLD, 15));
		right.setForeground(Color.white);
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
//			int marble = Integer.parseInt(displayboard.getNumber1()[i+1].getText());
//			displayboard.getPits1()[i].setIcon(new Pit(50, 75, marble));
//			marble = Integer.parseInt(displayboard.getNumber2()[i+1].getText());
//			displayboard.getPits2()[i].setIcon(new Pit(50, 75, marble));
			
			displayboard.getPits1()[i].setIcon(new Pit(50, 75, displayboard.getMarble(i), marbleName));
			displayboard.getPits2()[i].setIcon(new Pit(50, 75, displayboard.getMarble(i+6), marbleName));
			
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
		
//		int marble = Integer.parseInt(displayboard.getNumber1()[7].getText());
//		displayboard.getM1Button().setIcon(new Pit(50, 210, marble));
//		marble = Integer.parseInt(displayboard.getNumber2()[7].getText());
//		displayboard.getM2Button().setIcon(new Pit(50, 210, marble));
		
		displayboard.getM1Button().setIcon(new Pit(50, 210, displayboard.getMarble(12), marbleName));
		displayboard.getM2Button().setIcon(new Pit(50, 210, displayboard.getMarble(13), marbleName));
		
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
	 * @param op     the opaque
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

//	public static String transformStringToHtml(String strToTransform) {
//	    String ans = "<html>";
//	    String br = "<br>";
//	    String[] lettersArr = strToTransform.split("");
//	    for (String letter : lettersArr) {
//	        ans += letter + br;
//	    }
//	    ans += "</html>";
//	    return ans;
//	}
}
