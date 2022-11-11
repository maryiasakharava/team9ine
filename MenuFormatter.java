import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuFormatter {
    private TheSelectionMenu selectionMenu;

    private int frameWidth = 630;
    private int frameHeight = 350;
    private JPanel menuButtonPanel, themeButtonPanel, titlePanel;

    public MenuFormatter(TheSelectionMenu selectionMenu){
        this.selectionMenu = selectionMenu;
        menuButtonPanel = new JPanel();
        themeButtonPanel = new JPanel();
        titlePanel = new JPanel();
    }

    public void setLayout() {
        selectionMenu.setLayout(new BorderLayout());
        selectionMenu.setMinimumSize(new Dimension(frameWidth,frameHeight));
        selectionMenu.setMaximumSize(new Dimension(frameWidth,frameHeight));
        selectionMenu.setPreferredSize(new Dimension(frameWidth,frameHeight));
    }

    public JPanel addJLabel(){
        titlePanel.setLayout(new BorderLayout());

        this.setJLabel(selectionMenu.getGameTitle());
        titlePanel.add(selectionMenu.getGameTitle(), BorderLayout.NORTH);

        titlePanel.setOpaque(false);

        return titlePanel;
    }

    public void setJLabel(JLabel label){
        label.setOpaque(false);
        label.setFont(new Font("Serif", Font.PLAIN, 15));
        label.setFont(new Font(label.getName(), Font.BOLD, 15));
        label.setForeground(Color.WHITE);
    }

    public JPanel addPlayButtonPanel() {
        JPanel menuButtonPanelCont = new JPanel();
        menuButtonPanelCont.setLayout(new GridBagLayout());
        menuButtonPanel.setLayout(new GridLayout(0, 1, 20, 15));

        menuButtonPanel.setBackground(new Color(0.2f, 0.4f, 0.8f, 0.95f));
        menuButtonPanelCont.setBackground(new Color(0.2f, 0.4f, 0.8f, 0.95f));


        this.setButton(selectionMenu.getThreePitButton(), Color.WHITE, Color.BLACK, 0, e -> selectionMenu.startGame(3));
        this.setButton(selectionMenu.getFourPitButton(), Color.WHITE, Color.BLACK, 0, e -> selectionMenu.startGame(4));
        this.setButton(selectionMenu.getExitButton(), Color.WHITE, Color.BLACK, 0, e -> selectionMenu.exitButtonApplication());
        menuButtonPanel.add(selectionMenu.getThreePitButton());
        menuButtonPanel.add(selectionMenu.getFourPitButton());
        menuButtonPanel.add(selectionMenu.getExitButton());
        menuButtonPanelCont.add(menuButtonPanel);
        return menuButtonPanelCont;
    }

    public JPanel addThemeButtonPanel() {
        themeButtonPanel.setLayout(new FlowLayout());

        this.setButton(selectionMenu.getThemeOneButton(), Color.WHITE, new Color(102,0,153), 0, e -> selectionMenu.exitButtonApplication());
        this.setButton(selectionMenu.getThemeTwoButton(), Color.WHITE, Color.WHITE, 0, e -> selectionMenu.exitButtonApplication());
        this.setButton(selectionMenu.getThemeThreeButton(), Color.WHITE, Color.WHITE, 0, e -> selectionMenu.exitButtonApplication());

        this.setThemeButton(selectionMenu.getThemeTwoButton(), "C:\\Users\\josep\\Desktop\\CS151Project\\MancalaProject\\src\\questionMakr.png");
        this.setThemeButton(selectionMenu.getThemeThreeButton(), "C:\\Users\\josep\\Desktop\\CS151Project\\MancalaProject\\src\\mancalaRealImg.jpg");


        themeButtonPanel.add(selectionMenu.getThemeOneButton(), BorderLayout.WEST);
        themeButtonPanel.add(selectionMenu.getThemeTwoButton(), BorderLayout.WEST);
        themeButtonPanel.add(selectionMenu.getThemeThreeButton(), BorderLayout.WEST);
        themeButtonPanel.setBackground(new Color(0.2f, 0.4f, 0.8f, 0.95f));

        return themeButtonPanel;
    }


    public void setButton(JButton button, Color backgroundColor, Color foregroundColor, int op, ActionListener actionListener) {
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font(button.getName(), Font.BOLD, 14));
        button.setBorder(BorderFactory.createBevelBorder(op));
        button.setFocusPainted(false);
//        button.setBounds(x,y, buttonWidth, buttonHeight);
        button.setPreferredSize(new Dimension(100,50));
        button.setOpaque(false);
        button.addActionListener(actionListener);
    }

    public void setThemeButton(JButton button, String imagePath){
        try {
            Image image = ImageIO.read(new File(imagePath)).getScaledInstance(100, 50, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(image));
            button.setBounds(0,0, 200, 100);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
