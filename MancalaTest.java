import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MancalaTest {
	public static void main(String[] args) {
//		JFrame frame = new JFrame("Mancala App");
//		MancalaModel mancalaModel = new MancalaModel(3);
//		DisplayBoard displayboard = new DisplayBoard(mancalaModel);
////		MancalaFormatter formatter = new RealisticFormatter();
//		MancalaFormatter formatter = new SimpleFormatter();
//
//		displayboard.setFormatter(formatter);
////		displayboard.paintComponent();
//		
//		frame.add(displayboard);
//		
////		displayboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		displayboard.pack();
////		displayboard.setVisible(true);
////		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
		
		JFrame menuFrame = new JFrame("CS151 Mancala Project Team 9ine");
		TheSelectionMenu theSelectionMenu = new TheSelectionMenu();
		MenuFormatter menuFormatter = new MenuFormatter(theSelectionMenu);
		theSelectionMenu.setFormatter(menuFormatter);

		menuFrame.setResizable(false);
		menuFrame.add(theSelectionMenu);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		menuFrame.pack();
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);
	}
}
