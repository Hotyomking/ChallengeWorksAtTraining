import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * シンプルなじゃんけんゲームをするクラス.
 * @version	1.1,18 April, 2014
 * @author Hotty
 */
public class SimpleRockPaperScissors {
	//Rock,Scissors,Paperの順に配列に入れます.
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
		printStartMessage();
		int playerCommand = getPlayerCommand(receiveInput());
		if (isCorrectInput(playerCommand)) {
			int cpuCommand = makeCpuCommand();
			Result result = judgement(playerCommand, cpuCommand);
			printResultMessage(playerCommand, cpuCommand, result);
		} else {
			printErrorMessage();
		}
	}

	/**
	 * ゲーム開始時のメッセージをまとめて示すメソッド.
	 */
	public static void printStartMessage() { 
		System.out.println("じゃんけんをしましょう！");
		for (int i = 0; i < KEY.length; i++) {
			System.out.print(KEY[i] + ":" + NAME[i]);
			if (i < KEY.length - 1) {
				System.out.print(","); 
			}
		}
		System.out.println("です。");
		System.out.println("じゃーんけーん・・");
		System.out.print("出す手を入力 ==> ");
	}

	/**
	 * プレイヤーの入力をコマンドに変換するメソッド.
	 * @return プレイヤーの入力
	 */
	public static int getPlayerCommand(String input) {
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
	 * プレイヤーの入力を受け取るメソッド.
	 * @return プレイヤーから受け取った入力
	 */
	 public static String receiveInput() {
		BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
		try {
			return reader.readLine();
		} catch (IOException e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * プレイヤーのコマンド入力が指示に従ったものかどうかを判定するメソッド.
	 * @param プレイヤーから受け取った入力をコマンド化したもの
	 * @return 指示にしたがっていればtrue,いなければfalse
	 */
	public static boolean isCorrectInput(int playerCommand) {
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
	 * @return プレイヤー側から見た勝ち負け
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
	 * 勝敗結果メッセージを示すメソッド.
	 * @param playerCommand プレイヤーのコマンド 
	 * @param cpuCommand コンピュータのコマンド
	 * @param result プレイヤー側から見た勝ち負け
	 */
	public static void printResultMessage(int playerCommand, int cpuCommand, Result result) {
		System.out.println("ぽん！");
		System.out.println("あなた:" + NAME[playerCommand] + "、コンピュータ：" + NAME[cpuCommand]);
		if (result == Result.YOUWIN) {
			System.out.println("あなたの勝ちです！");
		} else if (result == Result.YOULOSE) {
			System.out.println("あなたの負けです！");
		} else {
			System.out.println("あいこでした！");
		}
	}
	
	/**
	 * プレイヤーの入力が指示に従っていない場合のメッセージを示すメソッド.
	 */
	public static void printErrorMessage() {
		System.out.println(KEY[0] + " か " + KEY[1] + " か " + KEY[2] + "で入力してください!");
	}


}
