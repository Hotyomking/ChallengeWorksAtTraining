package othello;

/**
 * オセロの石の色を列挙した型.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public enum Color {
	BLACK("●"),
	WHITE("○");
	public String colorMark;
	private int number;
	private Color(String colorMark){
		this.colorMark = colorMark;
	}
	public int getNumber() {
		return number;
	}
	public void countUpNumber() {
		number++;
	}
}
