import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;

/**
 * 指定された数値のヒストグラムを作成するクラス.
 *
 * @version 1.0, 15 April, 2014
 * @author yuki_tanaka
 */
public class Histogram {
	
	/**
	 * ヒストグラム作成の流れを表すメインメソッド.
	 *
	 * @param args 入力されたパラメータ
	 */
	public static void main(String[] args) {
		InputPattern inputPattern = CheckInput(args);
		if (isValidInput(inputPattern)) {
			int[] count = parseIntArgs(args);
			makeHistogram(count);
		} else{
			showErrorMessage(inputPattern);
		}
	}

	/**
	 * 入力パターンを列挙するenum.
	 */
	public static enum InputPattern{
		NO_INPUT,
		NOT_NUMBER,
		NOT_NATURAL_NUMBER,
		VALID_INPUT,
	}

	/**
	 * argsに適切な入力がなされているかを判定するメソッド.
	 *
	 * @param inputPattern 入力をパターンわけしたもの
	 * @return 適切な入力がなされた場合trueを返す
	 */
	public static boolean isValidInput(InputPattern inputPattern){
		switch (inputPattern) {
			case VALID_INPUT:
				return true;
			case NO_INPUT:
			case NOT_NUMBER:
			case NOT_NATURAL_NUMBER:
				return false;
		}
		return false;
	}

	/**
	 * argsへの入力がどの入力パターンに属すかを判定するメソッド.
	 * 
	 * @param args 入力されたパラメータ
	 * @return 入力パターン
	 */
	public static InputPattern CheckInput(String[] args) {
		if (args.length == 0) {
			return InputPattern.NO_INPUT;
		}
		
		Pattern patternNumber = Pattern.compile("^-?[0-9]*$");
		Matcher matcherNumber;
		for (int i = 0; i < args.length;i++ ) {
			matcherNumber = patternNumber.matcher(args[i]);
			if (!matcherNumber.find()) {
				return InputPattern.NOT_NUMBER;
			}
		}
		
		Pattern patternPositiveNumber = Pattern.compile("^[0-9]*$");
		Matcher matcherPositiveNumber;
		for (int i = 0; i < args.length;i++ ) {
			matcherPositiveNumber = patternPositiveNumber.matcher(args[i]);
			if (!matcherPositiveNumber.find()) {
				return InputPattern.NOT_NATURAL_NUMBER;
			}
		}
		return InputPattern.VALID_INPUT;
	}

	/**
	 * 適切な入力がなされなかった場合のエラーメッセージを吐き出すメソッド.
	 *
	 * @param inputPattern 入力をパターン分けしたもの
	 */
	public static void showErrorMessage(InputPattern inputPattern) {
		switch (inputPattern) {
			case NO_INPUT:
				System.out.println("グラフにプロットする値を引数に指定してください。");
				System.out.println("例) java Histogram 1 2 3 3 2 1");
				break;
			case NOT_NUMBER:
				System.out.println("引数に指定出来るのは数値のみです。");
				break;
			case NOT_NATURAL_NUMBER:
				System.out.println("引数に指定出来るのは正の値のみです。");
				break;		}
	}



	/**
	 * 入力されたString配列をint配列に変換するメソッド.
	 *
	 * @param args 入力されたString配列
	 * @param int配列
	 */
	public static int[] parseIntArgs(String[] args) {
		int[] count = new int[args.length];
		for(int i = 0; i < args.length; i++){
			count[i] = Integer.parseInt(args[i]);
		}
		return count;
	}

	/**
	 * 入力値に対応したヒストグラムを作成するメソッド.
	 * 
	 * @param count 入力されたパラメータ.
	 */
	public static void makeHistogram(int[] count) {
		int max = findMax(count);
		for(int i = max; i > 0; i--) {
			for (int j = 0; j < count.length; j++) {
				if (count[j] >= i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 入力値の中で最大値を求めるメソッド.
	 *
	 * @param count 入力されたパラメータ.
	 * @return 入力値のうち最大値
	 */
	public static int findMax(int[] count) {
		int max = 0;
		for(int i = 0; i < count.length; i++) {
			if (max < count[i]) {
				max = count[i];
			}
		}
		return max;			
	}
}