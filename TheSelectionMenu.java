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


    public TheSelectionMenu() {
        gameTitle = new JLabel("CS151 Mancala Game", JLabel.CENTER);
        threePitButton = new JButton("3 Pits");
        fourPitButton = new JButton("4 Pits");
        exitButton = new JButton("Exit");
        themeOneButton = new JButton("Purple");
        themeTwoButton = new JButton("Random");
        themeThreeButton = new JButton("Realistic");
    }

    public void setFormatter(MenuFormatter menuFormatter) {
        this.menuFormatter = menuFormatter;
        DisplayComponents(menuFormatter);
    }

    public void DisplayComponents(MenuFormatter formatter) {
        formatter.setLayout();
        this.add(formatter.addPlayButtonPanel(), BorderLayout.CENTER);
        this.add(formatter.addThemeButtonPanel(), BorderLayout.SOUTH);
        this.add(formatter.addJLabel(), BorderLayout.NORTH);
        this.setBackground(new Color(0.2f, 0.4f, 0.8f, 0.95f));
    }

    public void startGame(int numberOfMarbles){
        SwingUtilities.getWindowAncestor(this).dispose();
        JFrame gameFrame = new JFrame("CS151 Mancala Project Team 9ine | %s Pit Game".formatted(numberOfMarbles));
        DisplayBoard displayboard = new DisplayBoard();
        MancalaFormatter formatter = new SimpleFormatter(displayboard, 3);
        displayboard.setFormatter(formatter);

        gameFrame.setResizable(false);
        gameFrame.add(displayboard);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
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
        menuFrame.setVisible(true);
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
