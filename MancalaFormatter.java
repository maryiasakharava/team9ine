/**
 * CS151 Fall 2022 Team Project - 9ine
 * @Tam Ly, Jose Betancourt Jr. Huizar, Maryia Sakharava
 * @version 1.0 12/01/2022
 */

import javax.swing.JPanel;

/**
 * The interface Mancala formatter.
 */
public interface MancalaFormatter {

	/**
	 * Add head panel j panel.
	 *
	 * @param displayboard the displayboard
	 * @return the j panel
	 */
	public JPanel addHeadPanel(DisplayBoard displayboard);

	/**
	 * Add button panel j panel.
	 *
	 * @param displayboard the displayboard
	 * @return the j panel
	 */
	public JPanel addButtonPanel(DisplayBoard displayboard);

	/**
	 * Add main panel j panel.
	 *
	 * @param displayboard the displayboard
	 * @return the j panel
	 */
	public JPanel addMainPanel(DisplayBoard displayboard);

	/**
	 * Sets layout.
	 *
	 * @param displayboard the displayboard
	 */
	public void setLayout(DisplayBoard displayboard);

	/**
	 * Transform string to html string.
	 *
	 * @param strToTransform the str to be transformed
	 * @return the transformed string
	 */
	public default String transformStringToHtml(String strToTransform) {
	    String ans = "<html>";
	    String br = "<br>";
	    String[] lettersArr = strToTransform.split("");
	    for (String letter : lettersArr) {
	        ans += letter + br;
	    }
	    ans += "</html>";
	    return ans;
	}
}
