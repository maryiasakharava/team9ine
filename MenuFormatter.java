/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Menu Formatter Class for Selection Menu
 */
public class MenuFormatter {
    private TheSelectionMenu selectionMenu;

    private int frameWidth = 630;
    private int frameHeight = 350;
    private JPanel menuButtonPanel, themeButtonPanel, titlePanel;

    /**
     * Menu formatter Constructor
     *
     * @param selectionMenu the selection menu
     */
    public MenuFormatter(TheSelectionMenu selectionMenu){
        this.selectionMenu = selectionMenu;
        menuButtonPanel = new JPanel();
        themeButtonPanel = new JPanel();
        titlePanel = new JPanel();
    }

    /**
     * Sets layout for Slection menu
     */
    public void setLayout() {
        selectionMenu.setLayout(new BorderLayout());
        selectionMenu.setMinimumSize(new Dimension(frameWidth,frameHeight));
        selectionMenu.setMaximumSize(new Dimension(frameWidth,frameHeight));
        selectionMenu.setPreferredSize(new Dimension(frameWidth,frameHeight));
    }

    /**
     * Add j label to title Jpanel.
     *
     * @return the title Jpanel
     */
    public JPanel addJLabel(){
        titlePanel.setLayout(new BorderLayout());

        this.setJLabel(selectionMenu.getGameTitle());
        titlePanel.add(selectionMenu.getGameTitle(), BorderLayout.NORTH);

        titlePanel.setOpaque(false);

        return titlePanel;
    }

    /**
     * Set jlabel text style
     *
     * @param label label
     */
    public void setJLabel(JLabel label){
        label.setOpaque(false);
        label.setFont(new Font("Serif", Font.PLAIN, 15));
        label.setFont(new Font(label.getName(), Font.BOLD, 15));
        label.setForeground(Color.WHITE);
    }

    /**
     * Add play buttonpanel jpanel.
     *
     * @return the Menubuttonpanel
     */
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

    /**
     * Add theme button panel jpanel.
     *
     * @return the themeButtonPanel
     */
    public JPanel addThemeButtonPanel() {
        themeButtonPanel.setLayout(new GridBagLayout());
        themeButtonPanel.setLayout(new GridLayout(1, 2, 20, 15));

        this.setButton(selectionMenu.getThemeOneButton(), Color.WHITE, Color.BLACK, 0, e -> {
        	selectionMenu.setStyleFormatter(new SimpleFormatter());
        	}
        );
        this.setButton(selectionMenu.getThemeTwoButton(), Color.WHITE, Color.BLACK, 0, e -> {
        	selectionMenu.setStyleFormatter(new RealisticFormatter());
        	}
        );

        themeButtonPanel.add(selectionMenu.getThemeOneButton());
        themeButtonPanel.add(selectionMenu.getThemeTwoButton());

        return themeButtonPanel;
    }


    /**
     * Sets button.
     *
     * @param button          the button
     * @param backgroundColor the background color
     * @param foregroundColor the foreground color
     * @param op              the setOpaque
     * @param actionListener  the action listener
     */
    public void setButton(JButton button, Color backgroundColor, Color foregroundColor, int op, ActionListener actionListener) {
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font(button.getName(), Font.BOLD, 14));
        button.setBorder(BorderFactory.createBevelBorder(op));
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(actionListener);

    }
}
