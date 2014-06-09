package othello;
/**
 * オセロ盤を表現するクラス.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public class GameMap {
	/**
	 * GameMapの一辺の長さ.
	 */
	public static final int MATRIX_LENGTH = 8;

	Stone[][] stones = new Stone[MATRIX_LENGTH][MATRIX_LENGTH];
	
	/**
	 * GameMapコンストラクタ.作成時に最初の4石を置く.
	 * @param mario 先攻プレイヤー
	 * @param luigi 後攻プレイヤー
	 */
	public GameMap(GamePlayer mario, GamePlayer luigi) {
		mario.placeStone(this, new MapPoint(MATRIX_LENGTH/2 - 1, MATRIX_LENGTH/2 - 1));
		mario.placeStone(this, new MapPoint(MATRIX_LENGTH/2	  , MATRIX_LENGTH/2	  ));
		luigi.placeStone(this, new MapPoint(MATRIX_LENGTH/2 - 1, MATRIX_LENGTH/2	  ));
		luigi.placeStone(this, new MapPoint(MATRIX_LENGTH/2 	  , MATRIX_LENGTH/2 - 1));
	}
	/**
	 * GameMap上の石の数を数え、ColorEnumの各色のプロパティnumberに値を設定するメソッド.
	 */
	public void countStones() {
		for (int y = 0; y < GameMap.MATRIX_LENGTH ; y++){
			for (int x = 0; x < GameMap.MATRIX_LENGTH ; x++){
				if (stones[x][y] != null) {
					if (stones[x][y].getColor() == Color.BLACK) {
						Color.BLACK.countUpNumber();
					} else {
						Color.WHITE.countUpNumber();
					}
					
				}
			}
		}
	}
	
}

