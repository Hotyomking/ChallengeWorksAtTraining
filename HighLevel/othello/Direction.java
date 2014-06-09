package othello;

/**
 * オセロの隣接石を探す時の方向をまとめた列挙型.
 * @author Hotty
 * 
 */
public enum Direction {
	NORTH(0, 1),
	NORTHEAST(1, 1),
	EAST(1, 0),
	SOUTHEAST(1, -1),
	SOUTH(0, -1),
	SOUTHWEST(-1,-1),
	WEST(-1, 0),
	NORTHWEST(-1,1);
	public int directionX;
	public int directionY;
	private Direction(int directionX, int directionY) {
		this.directionX = directionX;
		this.directionY = directionY;
	}
}
