import javax.swing.JFrame;

public class MancalaTest {

	public static void main(String[] args)
	{
		JFrame menuFrame = new JFrame("CS151 Mancala Project Team 9ine");
		TheSelectionMenu theSelectionMenu = new TheSelectionMenu();
		MenuFormatter menuFormatter = new MenuFormatter(theSelectionMenu);
		theSelectionMenu.setFormatter(menuFormatter);

		menuFrame.setResizable(false);
		menuFrame.add(theSelectionMenu);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.pack();
		menuFrame.setVisible(true);
	}
}
