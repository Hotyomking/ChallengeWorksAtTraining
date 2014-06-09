import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.lang.IllegalArgumentException;

/**
 * エラトステネスの篩いをかけて素数を求めるクラス.
 * @version 1.0, 15 April, 2014
 * @author TanakaYuki
 */
public class Eratosthenes {
	/**
	 * エラトステネスの篩いの流れを示すメインメソッド.
	 */
	public static void main(String[] args) {
		showStartMessage();
		try {
			showResult(searchPrime(receiveInput()));
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 開始時のメッセージを示すメソッド.
	 */
	public static void showStartMessage() {
		System.out.println("素数リストを出力するプログラムです。");
		System.out.println("リストの最大値を整数で入力してください");
	}


	/**
	 * プレイヤーの入力を受け取るメソッド.
	 * @return input 受け取った値java
	 */
	public static int receiveInput() throws IllegalArgumentException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			line = reader.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(line);
		if (!matcher.find()) {
			throw new IllegalArgumentException("不正な値が入力されました。");
		}
		return Integer.parseInt(line);
	}

	/**
	 * 素数を探し出すメソッド.
	 * @param max リストの最大値
	 * @return 素数一覧
	 */
	public static LinkedList<Integer> searchPrime(int max){
		LinkedList<Integer> primeList = new LinkedList<Integer>();
		if (max < 2) {
			return primeList;
		}
		for(int i = 2; i < max + 1; i++) {
			primeList.add(i);
		}
		int root = (int)Math.sqrt(max);
		for(int i = 2; i < root + 1; i++) {
			if(primeList.contains((Integer)i)) {
				for(int j = 4; j < max + 1; j++) {
					if (primeList.contains((Integer)j) && isMultiple(j,i)) {
						primeList.remove((Integer)j);
					}
				}
			}
		}
		return primeList;
	}

	/**
	 * 倍数であるかどうかを返すメソッド.
	 * @param devided 大きい数
	 * @param splitter 小さい数
	 * @return 倍数ならtrue
	 */
	public static boolean isMultiple(int devided, int splitter) {
		return (devided % splitter == 0 && devided / splitter > 1);
	}

	/**
	 * 格納した素数を表示するメソッド.
	 * @param 素数を格納したリンクドリスト
	 */
	public static void showResult(LinkedList<Integer> primeList) {
		for (int i : primeList) {
			System.out.print(i + " ");
		}
	}
}