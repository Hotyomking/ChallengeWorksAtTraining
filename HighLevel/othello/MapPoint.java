package othello;

/**
 * オセロ盤の一マスを表現するクラス.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public class MapPoint {
	private int coordinateX;
	public int getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	public int getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	private int coordinateY;
	public MapPoint() {
		
	}
	public MapPoint(int coordinateX, int coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}
	
	/**
	 * このポイントがオセロ盤上に存在するかどうかを返すメソッド.
	 * @return このポイントがオセロ盤上に存在する場合はtrue
	 */
	public boolean isOnGameMap() {
		return (-1 < coordinateX && coordinateX < GameMap.MATRIX_LENGTH && -1 < coordinateY && coordinateY < GameMap.MATRIX_LENGTH);
	}
}
