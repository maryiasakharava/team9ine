import javax.swing.JPanel;

public interface MancalaFormatter {

	public JPanel addHeadPanel(DisplayBoard displayboard);

	public JPanel addButtonPanel(DisplayBoard displayboard);

	public JPanel addMainPanel(DisplayBoard displayboard);

	public void setLayout(DisplayBoard displayboard);
	
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
