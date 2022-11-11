import javax.swing.*;
import java.awt.*;

public class BigPit extends SimpleFormatter
{
    DisplayBoard displayboard;
    int numberOfMarbles;

    public BigPit(DisplayBoard displayboard, int numberOfMarbles) {
        super(displayboard, numberOfMarbles);
        this.displayboard = displayboard;
        this.numberOfMarbles = numberOfMarbles;
    }

    public JButton addLeftBigPit(){
        JButton m1Button = displayboard.getM1Button();
        m1Button.setIcon(new Pit(50, 210));

		setButton(m1Button, Color.white, Color.black, false, false, 0);

        m1Button.setBounds(0, 0, 60, 215);

        m1Button.setLocation(20,24);
        return m1Button;
    }

    public JButton addRightBigPit(){
        JButton m2Button = displayboard.getM2Button();

        m2Button.setIcon(new Pit(50, 210));

        setButton(m2Button, Color.white, Color.black, false, false, 0);

        m2Button.setBounds(0, 0, 60, 215);

        m2Button.setLocation(470,24);
        return m2Button;
    }

}
