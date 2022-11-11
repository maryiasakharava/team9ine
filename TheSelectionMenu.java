
import javax.swing.*;
import java.awt.*;

public class TheSelectionMenu extends JPanel {
    private JLabel gameTitle;
    private JButton threePitButton, fourPitButton, exitButton, themeOneButton, themeTwoButton, themeThreeButton;
    private int frameWidth = 630;
    private int frameHeight = 350;
    private int buttonWidth = 100;
    private int buttonHeight = 50;
    private MenuFormatter menuFormatter;
    private DisplayBoard displayboard = new DisplayBoard();

	private MancalaFormatter formatter = new SimpleFormatter(displayboard);


    public TheSelectionMenu() {
        gameTitle = new JLabel("CS151 Mancala Game", JLabel.CENTER);
        threePitButton = new JButton("3 Pits");
        fourPitButton = new JButton("4 Pits");
        exitButton = new JButton("Exit");
        themeOneButton = new JButton("Simple Theme");
        themeTwoButton = new JButton("Realistic Theme");
        themeThreeButton = new JButton("XXX3 Theme");
    }

    public void setFormatter(MenuFormatter menuFormatter) {
        this.menuFormatter = menuFormatter;
        DisplayComponents(menuFormatter);
    }
    
    public void setStyleFormatter(MancalaFormatter formatter) {
        this.formatter = formatter;
    }

    public void DisplayComponents(MenuFormatter formatter) {
        formatter.setLayout();
        this.setBackground(new Color(0.2f, 0.4f, 0.8f, 0.95f));
        this.add(formatter.addPlayButtonPanel(), BorderLayout.CENTER);
        this.add(formatter.addThemeButtonPanel(), BorderLayout.SOUTH);
        this.add(formatter.addJLabel(), BorderLayout.NORTH);
    }

    public void startGame(int numberOfPits){
        SwingUtilities.getWindowAncestor(this).dispose();
        JFrame gameFrame = new JFrame("CS151 Mancala Project Team 9ine | %s Pit Game".formatted(numberOfPits));      
        
        displayboard.setNumber(numberOfPits);
        displayboard.setFormatter(formatter);      

        gameFrame.setResizable(false);
        gameFrame.add(displayboard);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    public void exitButtonApplication(){
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    public void displayMainMenu(){
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

    public DisplayBoard getDisplayboard() {
		return displayboard;
	}
    public JButton getExitButton() {
        return exitButton;
    }
    public JButton getThreePitButton() {
        return threePitButton;
    }
    public JButton getFourPitButton() {
        return fourPitButton;
    }
    public JButton getThemeOneButton() {
        return themeOneButton;
    }
    public JButton getThemeTwoButton() {
        return themeTwoButton;
    }
    public JButton getThemeThreeButton() {
        return themeThreeButton;
    }
    public JLabel getGameTitle() {
        return gameTitle;
    }

}