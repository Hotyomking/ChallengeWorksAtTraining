package othello;

public class Printer {
	/**
	 * マップとメッセージを表示するメソッド
	 * @param map 石の状態を全て保有しているgameMap
	 * @param player 次に石を置くことを期待されているプレイヤー
	 */
	public void printAll(GameMap map, GamePlayer player) {
		printMap(map);
		printMessage(player);
	}
	private void printMap(GameMap gameMap) {
		System.out.print("  ");
		for (int x = 0; x < GameMap.MATRIX_LENGTH; x++ ) {
			System.out.print("  " + (char)(97 + x));
		}
		System.out.println();
		
		printTopWall();
		for (int y = 0; y < GameMap.MATRIX_LENGTH; y++) {
			System.out.print(y + 1 + " ┃");
			for (int x = 0 ; x < GameMap.MATRIX_LENGTH; x++) {
				printStone(gameMap , x, y);
				System.out.print("┃");
			}
			System.out.println();
			
			if (y != GameMap.MATRIX_LENGTH -1) {
				printHorizontalWall();
			} else {
				printBottomWall();
			}
		}
	}
	
	private void printTopWall() {
		System.out.print("  ┏━");
		for (int x = 0; x < GameMap.MATRIX_LENGTH - 1; x++ ) {
			System.out.print("┳━");
		}
		System.out.println("┓");
	}
	
	private void printHorizontalWall() {
		System.out.print("  ┣━");
		for (int x = 0; x < GameMap.MATRIX_LENGTH - 1; x++ ) {
			System.out.print("╋━");
		}
		System.out.println("┫");
	}
	
	private void printBottomWall() {
		System.out.print("  ┗━");
		for (int x = 0; x < GameMap.MATRIX_LENGTH - 1; x++ ) {
			System.out.print("┻━");
		}
		System.out.println("┛");
	}
	
	private void printStone(GameMap gameMap, int x, int y) {
		if (gameMap.stones[x][y] == null) {
			System.out.print("　");
		} else {
			System.out.print(gameMap.stones[x][y].getColor().colorMark);
		}
	}

	private void printMessage(GamePlayer player) {
		System.out.println(player.getName() + "さん、" + player.getColor().colorMark + "石を置いてください　例) a1 *パスは\"00\", 降参は\"99\"");
	}
	
	/**
	 * 勝敗結果を表示するメソッド.
	 * @param gameMap
	 * @param mario
	 * @param luigi
	 */
	public void printResult(GamePlayer mario, GamePlayer luigi) {
		System.out.println(mario.getName() + "さんの" + mario.getColor().colorMark + "石は、" + mario.getColor().getNumber() + "個");
		System.out.println(luigi.getName() + "さんの" + luigi.getColor().colorMark + "石は、" + luigi.getColor().getNumber() + "個");
		if (luigi.getColor().getNumber() < mario.getColor().getNumber()) {
			System.out.println(luigi.getName() + "さんの勝利です!");
		} else if(mario.getColor().getNumber() < luigi.getColor().getNumber()) {
			System.out.println(mario.getName() + "さんの勝利です!");
		} else {
			 System.out.println("引き分けです！お疲れさまでした！");
		}
		
	}
}
