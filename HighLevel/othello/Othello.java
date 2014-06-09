package othello;

/**
 * オセロゲームを開始するクラス.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public class Othello {
	/**
	 * ゲームマスターを呼び出しオセロを開始するメインメソッド.
	 * @param args 引数。とってはいけない
	 */
	public static void main(String[] args) {
		GameMaster gameMaster = new GameMaster();
		gameMaster.playGame();
	}
}