import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        themeButtonPanel.setLayout(new BorderLayout());

        this.setButton(selectionMenu.getThemeOneButton(), Color.WHITE, Color.BLACK, 0, e -> selectionMenu.exitButtonApplication());
        this.setButton(selectionMenu.getThemeTwoButton(), Color.WHITE, Color.BLACK, 0, e -> selectionMenu.exitButtonApplication());
        this.setButton(selectionMenu.getThemeThreeButton(), Color.WHITE, Color.BLACK, 0, e -> selectionMenu.exitButtonApplication());

        themeButtonPanel.add(selectionMenu.getThemeOneButton(), BorderLayout.WEST);
        themeButtonPanel.add(selectionMenu.getThemeTwoButton(), BorderLayout.WEST);
        themeButtonPanel.add(selectionMenu.getThemeThreeButton(), BorderLayout.WEST);

        return themeButtonPanel;
    }


    public void setButton(JButton button, Color backgroundColor, Color foregroundColor, int op, ActionListener actionListener) {
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font(button.getName(), Font.BOLD, 14));
        button.setBorder(BorderFactory.createBevelBorder(op));
        button.setFocusPainted(false);
//        button.setBounds(x,y, buttonWidth, buttonHeight);
        button.setOpaque(false);
        button.addActionListener(actionListener);

    }
}
