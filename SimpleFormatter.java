import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SimpleFormatter implements MancalaFormatter {
	private JPanel buttonPanel, mainPanel, headPanel, numberStone1, numberStone2, leftP, rightP, 
				    board, pitPanel1, pitPanel2, pitPanel3, pitPanel4;
	private JLabel headField, left, right, background;
	private JLabel[] letter1, letter2;
	private Icon pit;
	private Icon theBoard;

	public JPanel mainboard;
	public DisplayBoard displayboard;

	public int numberOfMarbles;

	private Color lightGrey = new Color(0.0f, 0.0f, 0.0f, 0.5f);


	public SimpleFormatter(DisplayBoard displayboard, int numberOfMarbles) {
		this.displayboard = displayboard;
		this.numberOfMarbles = numberOfMarbles;
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
		pit = new Pit(50, 75);
		theBoard = new Board(540, 260);
		background = new JLabel(theBoard);
	}

	public void setLayout() {
		displayboard.setLayout(new BorderLayout());
		Dimension d = new Dimension(630,350);
		displayboard.setMinimumSize(d);
		displayboard.setMaximumSize(d);
		displayboard.setPreferredSize(d);
		displayboard.setBackground(new Color(0.2f, 0.4f, 0.8f, 0.95f));
	}
	
	public JPanel addHeadPanel() {			
		headField.setOpaque(false);
		headField.setFont(new Font("Serif", Font.PLAIN, 25));
		headField.setFont(new Font(headField.getName(), Font.BOLD, 25));
		headPanel.setOpaque(false);
		headPanel.add(headField);
		return headPanel;
	}
	
	public JPanel addButtonPanel() {	
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
	
	public JPanel addMainPanel() {			
		mainPanel.setLayout(new BorderLayout());
		
		left.setText(transformStringToHtml("MANCALA B"));
		leftP.setBorder(BorderFactory.createEmptyBorder(35,10,0,0));
		left.setFont(new Font("MONOSPACED", Font.BOLD, 15));
		leftP.add(left);
		
		right.setText(transformStringToHtml("MANCALA A"));
		rightP.setBorder(BorderFactory.createEmptyBorder(35,5,0,5));
		right.setFont(new Font("MONOSPACED", Font.BOLD, 15));
		rightP.add(right);
		
		numberStone1.setLayout(new GridLayout(1, 8));
		numberStone2.setLayout(new GridLayout(1, 8));
		
		for (int i = 0; i < displayboard.getNumber1().length; i++) {
			numberStone1.add(displayboard.getNumber1()[displayboard.getNumber1().length-1 - i]);
		}

		for (int i = 0; i < displayboard.getNumber2().length; i++) {
			numberStone2.add(displayboard.getNumber2()[i]);
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
			displayboard.getPits1()[i].setIcon(pit);
			displayboard.getPits2()[i].setIcon(pit);
			
			this.setButton(displayboard.getPits1()[i], Color.white, Color.black, false, false, 0);
			this.setButton(displayboard.getPits2()[i], Color.white, Color.black, false, false, 0);
		}
		
		for (int i = 0; i < 6; i++) {
			pitPanel1.add(displayboard.getPits1()[i]);
			displayboard.getPits1()[i].setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
			pitPanel2.add(displayboard.getPits2()[i]);
			displayboard.getPits2()[i].setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
			pitPanel3.add(letter1[i]);
			letter1[i].setBorder(BorderFactory.createEmptyBorder(0,20,0,25));
			pitPanel4.add(letter2[i]);
			letter2[i].setBorder(BorderFactory.createEmptyBorder(0,20,0,25));		
		}
		
		pitPanel1.setBackground(lightGrey);
		pitPanel1.setOpaque(false);
		pitPanel2.setBackground(lightGrey);
		pitPanel2.setOpaque(false);
		pitPanel3.setBackground(lightGrey);
		pitPanel3.setOpaque(false);
		pitPanel4.setBackground(lightGrey);
		pitPanel4.setOpaque(false);

		setPanelLocation(pitPanel1, 0, 0, 400, 90, 0, 0);
		setPanelLocation(pitPanel2, 0, 0, 400, 85, 0, 135);
		setPanelLocation(pitPanel3, 0, 0, 400, 20, 0, 80);
		setPanelLocation(pitPanel4, 0, 0, 400, 20, 0, 115);

		board.add(pitPanel1);
		board.add(pitPanel2);
		board.add(pitPanel3);
		board.add(pitPanel4);

		board.setBounds(0, 0, 405, 220);
		board.setLocation(75,20);

		setPanelLocation(numberStone1, 0, 0, 520, 20, 15, 5);
		setPanelLocation(numberStone2, 0, 0, 520, 20, 15, 240);

		background.setBounds(0, 0, 550, 270);		
		background.setLocation(0,0);
		background.setBackground(lightGrey);
		background.setOpaque(false);

		setPanel(numberStone1, lightGrey, false);
		setPanel(numberStone2, lightGrey, false);
		setPanel(board, lightGrey, false);

		addMainboardComp(new BigPit(this.displayboard, this.numberOfMarbles).addLeftBigPit());
		addMainboardComp(new BigPit(this.displayboard, this.numberOfMarbles).addRightBigPit());
		addMainboardComp(numberStone1);
		addMainboardComp(numberStone2);
		addMainboardComp(board);
		addMainboardComp(background);

		setPanel(mainboard, lightGrey, false);
		setPanel(leftP, lightGrey, false);
		setPanel(rightP, lightGrey, false);

		mainPanel.add(mainboard,BorderLayout.CENTER);
		mainPanel.add(leftP,BorderLayout.WEST);
		mainPanel.add(rightP,BorderLayout.EAST);
		
		mainPanel.setOpaque(false);
		
		return mainPanel;
	}

	public void addMainboardComp(Component component){
		mainboard.add(component);
	}

	public void setPanel(JPanel panel, Color backgroundColor, boolean isOpaque){
		panel.setBackground(backgroundColor);
		panel.setOpaque(isOpaque);
	}

	public void setPanelLocation(JPanel panel, int boundsX, int boundsY, int boundsWidth, int boundsHeight, int locationX, int locationY){
		panel.setBounds(boundsX, boundsY, boundsWidth, boundsHeight);
		panel.setLocation(locationX, locationY);
	}

	public void setButton(JButton b, Color backCo, Color ForeCo, boolean b1, boolean b2, int op) {
		b.setBackground(backCo);
		b.setForeground(ForeCo);
		b.setOpaque(b1);			
		b.setBorderPainted(b2);
		b.setFont(new Font(b.getName(), Font.BOLD, 15));
		b.setBorder(BorderFactory.createBevelBorder(op));
		b.setFocusPainted(false);
	}

	public Color RndColor() {
		Random random = new Random();
		double r = random.nextInt(255)/255.0;
		double g = random.nextInt(200)/255.0;
		double b = random.nextInt(80)/255.0;
		
		return new Color((float) r,(float) g,(float) b, 0.9f);
	}

	
	public static String transformStringToHtml(String strToTransform) {
	    String ans = "<html>";
	    String br = "<br>";
	    String[] lettersArr = strToTransform.split("");
	    for (String letter : lettersArr) {
	        ans += letter + br;
	    }
	    ans += "</html>";
	    return ans;
	}
	
}
