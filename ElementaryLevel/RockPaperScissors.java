import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * あいこの場合は繰り返しがあるじゃんけんゲームをするクラス.
 * @version	1.0,14 April, 2014
 * @author Hotty
 */
public class RockPaperScissors {

	static final String[] NAME = {"グー", "チョキ", "パー"};
	static final int[] KEY = {1, 2, 3};

	/**
	 * 勝負の結果を列挙する型
	 */
	enum Result {
		YOUWIN,
		YOULOSE,
		DRAW
	}

	/**
	 * ゲームの流れを示すメインメソッド.
	 */
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
		Result result = Result.DRAW;

		printStartMessage();
		while (!isFinished(result)) {
			int playerCommand = getPlayerCommand(reader);
			if (isCorrectInputed(playerCommand)) {
				int cpuCommand = makeCpuCommand();
				result = judgement(playerCommand, cpuCommand);
				printBattleMessage(playerCommand, cpuCommand, result);
			}
			else {
				printErrorMessage();
			}
		}
		printResultMessage(result);
	}

	/**
	 * ゲーム開始時のメッセージをまとめて示すメソッド.
	 */
	public static void printStartMessage(){ 
		System.out.println("じゃんけんをしましょう！");
		System.out.println(KEY[0] + ":" + NAME[0] + "、" + KEY[1] + ":" + NAME[1] + ", " + KEY[2] + ":" + NAME[2] + "です。" );
		System.out.println("じゃーんけーん・・");
	}

	/**
	 * じゃんけんが終了したかどうかを判定するメソッド.
	 *
	 * @param result 前回のじゃんけんの結果
	 * @return 終了していたらtrueを返す
	 */
	 public static boolean isFinished(Result result) {
	 	return result == Result.YOUWIN || result == Result.YOULOSE;
	 }

	/**
	 * プレイヤーからコマンド入力を受け取るメソッド.
	 * @param  入力された文字列
	 * @return プレイヤーの入力コマンド
	 */
	public static int getPlayerCommand(BufferedReader reader) {
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.print("出す手を入力 =>");
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()){
			for (int i = 0; i < KEY.length; i++) {
				if(Integer.parseInt(input) == KEY[i]) {
					return i; 
				}
			}
		}
		return -1;
	}
	
	/**
	 * プレイヤーのコマンド入力が指示に従ったものかどうかを判定するメソッド.
	 * @param プレイヤーから受け取った入力
	 * @return 指示にしたがっていればtrue,いなければfalse
	 */
	public static boolean isCorrectInputed(int playerCommand) {
		return playerCommand != -1;
	}

	/**
	 * コンピュータ側のコマンドをランダムに生成するメソッド.
	 * @return コンピュータ側のコマンド
	 */
	public static int makeCpuCommand() {
		return (int)(Math.random() * 3);
	}

	/**
	 * プレイヤーとコンピュータの勝ち負けを判定するメソッド.
	 * @param playerCommand プレイヤーのコマンド
	 * @param cpuCommand コンピュータのコマンド
	 * @return result プレイヤー側から見た勝ち負け
	 */
	public static Result judgement(int playerCommand, int cpuCommand) {
		switch ((playerCommand - cpuCommand + 3) % 3) {
			case 0:
				return Result.DRAW;
			case 1:
				return Result.YOULOSE;
			default:
				return Result.YOUWIN;
		}
	}

	/**
	 * じゃんけん中のメッセージを示すメソッド.
	 * @param playerCommand プレイヤーのコマンド
	 * @param cpuCommand コンピュータのコマンド
	 */
	public static void printBattleMessage(int playerCommand, int cpuCommand, Result result) {
		System.out.println("ぽん！");
		System.out.println("あなた:" + NAME[playerCommand] + "、コンピュータ：" + NAME[cpuCommand]);
		if (result == Result.DRAW) {
			System.out.println("あーいこーで・・");
		}
	}


	/**
	 * 勝敗結果メッセージを示すメソッド.
	 * @param result プレイヤー側から見た勝ち負け
	 */
	public static void printResultMessage(Result result) {
		if (result == Result.YOUWIN) {
			System.out.println("あなたの勝ちです！");
		} else if (result == Result.YOULOSE) {
			System.out.println("あなたの負けです！");
		}
	}

	/**
	 * プレイヤーの入力が指示に従っていない場合のメッセージを示すメソッド.
	 */
	public static void printErrorMessage() {
		System.out.println(KEY[0] + " か " + KEY[1] + " か " + KEY[2] + "で入力してください!");
	}
}
