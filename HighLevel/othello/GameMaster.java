package othello;

/**
 * オセロを実行するゲームマスターを表現するクラス.
 * @author Hotty
 * @version 1.0, 22 April 2014
 */
public class GameMaster {
	private static final String SURRENDER_KEY = "99";
	private static final String PASS_KEY = "00";
	/**
	 * オセロゲームを実行するメソッド.
	 */
	public void playGame(){
		GamePlayer mario = new GamePlayer("mario");
		GamePlayer luigi = new GamePlayer("luigi");
		GamePlayer player;
		GameMap gameMap = new GameMap(mario, luigi);
		MapPoint placePoint = new MapPoint();
		InputReciever inputReciever = new InputReciever();
		Printer printer = new Printer();
		String input;
		
		player = mario;
		while (isContinued(gameMap, mario, luigi)) {
			printer.printAll(gameMap, player);
			input = inputReciever.recieveInput();
			if (input.equals(SURRENDER_KEY)) {
				System.out.println(player.getName() + "さんの負けです。");
				return;
			}
			if (input.equals(PASS_KEY)) {
				if (player.canPlaceAnyStone(gameMap)) {
					System.out.println("パスできません！おける場所があります。");
					continue;
				}
				player = changePlayer(player, mario, luigi);
				continue;
			}
			if (!inputReciever.isValidInput(input)) {
				System.out.println("不正な値[" + input + "]が入力されました。a1からh8までの範囲の値を入力してください。");
				continue;
			}
			placePoint = inputReciever.changeToMapPoint(input);
			if (!player.canPlaceStone(gameMap, placePoint)) {
				System.out.println("\"" + input + "\"には置けません！");
				continue;
			}
			player.placeStone(gameMap, placePoint);
			player.reverseStone(gameMap, placePoint);
			player = changePlayer(player, mario, luigi);
		}
		gameMap.countStones();
		printer.printResult(mario, luigi);
	}
	
	/**
	 * ゲームがまだ続くかどうかを判断するメソッド.
	 * @return プレイ継続可能の時はtrue
	 */
	private boolean isContinued(GameMap gameMap, GamePlayer mario, GamePlayer luigi) {
		return mario.canPlaceAnyStone(gameMap) || luigi.canPlaceAnyStone(gameMap);
	}
	
	/**
	 * 次に石を置くプレイヤーをチェンジするメソッド.
	 * @param player 前回石を置いたプレイヤー
	 * @param mario 先攻のプレイヤー
	 * @param luigi 後攻のプレイヤー
	 * @return 次回石を置くプレイヤー
	 */
	private GamePlayer changePlayer(GamePlayer player, GamePlayer mario, GamePlayer luigi) {
		return (player.equals(mario)) ? luigi : mario;
	}
	

}
