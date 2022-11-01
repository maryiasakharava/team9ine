import java.awt.Component;

import javax.swing.JPanel;

public interface MancalaFormatter {
	//public void paint();

	public JPanel addHeadPanel();

	public JPanel addButtonPanel();

	public JPanel addMainPanel();

	public void setLayout();
}
