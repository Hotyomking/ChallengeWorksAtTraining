package othello;
import java.util.ArrayList;
/**
 * オセロゲームをプレイするユーザーを表現するクラス.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public class GamePlayer {
	private static int playerNumber;
	private final int PLAYER_LIMIT_NUMBER = 2; 
	
	private String name;
	private Stone stone;
	
	/**
	 * 指定の名前、作られた順番に対応した色の石を持つプレイヤーを作成するコンストラクタ.
	 * @param name プレイヤーにつける名前
	 */
	public GamePlayer(String name) {
		if (playerNumber < PLAYER_LIMIT_NUMBER){
			this.name = name;
			this.stone = new Stone(Color.values()[playerNumber]);
			playerNumber++;
		} else {
			throw new IllegalArgumentException("プレイヤーを増やし過ぎです");
		}
	}
	
	/**
	 * プレイヤーの名前を取得するメソッド.
	 * @return プレイヤーの名前
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * プレイヤーの持つ石の色を取得するメソッド.
	 * @return プレイヤーの持つ石の色
	 */
	public Color getColor() {
		return this.stone.getColor();
	}
	
	/**
	 * プレイヤーが現状のオセロ盤に石を置く場所があるかどうかを返すメソッド.
	 * @param gameMap 現在のオセロ盤の状態を保持したmap
	 * @return 石を置けるポイントが一個でもあればtrue
	 */
	public boolean canPlaceAnyStone(GameMap gameMap) {
		MapPoint mapPoint = new MapPoint();
		for (int y = 0; y < GameMap.MATRIX_LENGTH ; y++){
			for (int x = 0; x < GameMap.MATRIX_LENGTH ; x++){
				mapPoint.setCoordinateX(x);
				mapPoint.setCoordinateY(y);
				if (canPlaceStone(gameMap, mapPoint)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * プレイヤーが現状のオセロ盤の指定したポイントに石を置けるかどうかを返すメソッド.
	 * @param gameMap 現在のオセロ盤の状態を保持したmap
	 * @param placePoint 新しく石を置けるか判定したいポイント
	 * @return プレイヤーが新しく石を置ける場合にはtrue
	 */
	public boolean canPlaceStone(GameMap gameMap, MapPoint placePoint) {
		if (gameMap.stones[placePoint.getCoordinateX()][placePoint.getCoordinateY()] != null) {
			return false;
		}
		return !getReversibleStoneList(gameMap, placePoint).isEmpty();
	}
	/**
	 * 指定した位置に石を置くメソッド.
	 * @param gameMap 現状のオセロ盤の状態を保持したgameMap
	 * @param placePoint 石を置くポイント
	 */
	public void placeStone(GameMap gameMap, MapPoint mapPoint) {
		gameMap.stones[mapPoint.getCoordinateX()][mapPoint.getCoordinateY()] = new Stone(this.stone.getColor());
	}
	
	/**
	 * 石を実際にひっくり返すメソッド
	 * @param gameMap 現状のオセロ盤の状態を保持したgameMap
	 * @param placePoint 石を置いたポイント
	 */
	public void reverseStone(GameMap gameMap, MapPoint mapPoint) {
		ArrayList<MapPoint> reversibleStoneList = getReversibleStoneList(gameMap, mapPoint);
		for (int i = 0; i < reversibleStoneList.size(); i++) {
			gameMap.stones[reversibleStoneList.get(i).getCoordinateX()][reversibleStoneList.get(i).getCoordinateY()].changeColor();
		}
	}
	
	/**
	 * ポイントに石を置いた時にひっくり返る石のリストを取得するメソッド.
	 * @param gameMap 現状のオセロ盤の状態を保持したgameMap
	 * @param placePoint 石を置こうとしている、もしくは置いたポイント
	 * @return ひっくり返る石のリスト
	 */
	private ArrayList<MapPoint> getReversibleStoneList(GameMap gameMap, MapPoint placePoint) {
		MapPoint searchPoint = new MapPoint();
		ArrayList<MapPoint> changePointList = new ArrayList<MapPoint>();
		anotherDirection:
		for (Direction direction : Direction.values()) {
			for (int distance = 1; distance < GameMap.MATRIX_LENGTH; distance++ ) {
				searchPoint.setCoordinateX(placePoint.getCoordinateX() + distance * direction.directionX);
				searchPoint.setCoordinateY(placePoint.getCoordinateY() + distance * direction.directionY);
				if (!searchPoint.isOnGameMap()) {
					continue anotherDirection;
				}
				if (gameMap.stones[searchPoint.getCoordinateX()][searchPoint.getCoordinateY()] == null ) {
					continue anotherDirection;
				}
				if (gameMap.stones[searchPoint.getCoordinateX()][searchPoint.getCoordinateY()].getColor() == this.stone.getColor() ) {
					if(distance == 1) {
						continue anotherDirection;
					}
					for (int i = 1 ; i < distance ; i++){
						changePointList.add(new MapPoint(placePoint.getCoordinateX() + i * direction.directionX, placePoint.getCoordinateY() + i * direction.directionY));
					}	
				}
			}
		}
		return changePointList;
	}
}
