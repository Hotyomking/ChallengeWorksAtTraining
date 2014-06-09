package othello;
/**
 * オセロに用いる石を表現するクラス.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public class Stone {

	private Color color;
	
	/**
	 * オセロの石インスタンスを生成するコンストラクタ
	 * @param color　石の色
	 */
	public Stone(Color color) {
		setColor(color);
	}
	
	/**
	 * 自分の色を反転するメソッド.
	 */
	public void changeColor() {
		if (this.color == Color.WHITE) {
			setColor(Color.BLACK);
		} else {
			setColor(Color.WHITE);
		}
	}
	
	/**
	 * 自分を指定の色に設定するメソッド.
	 * @param color 石に設定する色
	 */
	private void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * 自分の色を取得するメソッド
	 * @return 石の色
	 */
	public Color getColor() {
		return color;
	}
}
