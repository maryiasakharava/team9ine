import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MancalaTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mancala App");
		DisplayBoard displayboard = new DisplayBoard();
		MancalaFormatter formatter = new SimpleFormatter(displayboard);
		displayboard.setFormatter(formatter);
		
		frame.add(displayboard);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
