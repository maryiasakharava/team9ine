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
//		JPanel test = new JPanel();
//		JButton test1 = new JButton("Test");
//		test.add(test1);		
//		frame.add(test);
		
		frame.add(displayboard);
		
		//frame.add((Component)formatter);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
