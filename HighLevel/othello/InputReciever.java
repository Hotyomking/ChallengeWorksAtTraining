package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 入力受け取りをまとめたクラス.
 * @author Hotty
 */
public class InputReciever {
	/**
	 * 入力文字列を受け取るメソッド.
	 * @return 入力された文字列;
	 */
	public String recieveInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	/**
	 * 入力された文字列が適切かどうかを返すメソッド.
	 * @param inputString 入力された文字列.
	 * @return 適切な入力がなされていた場合true
	 */
	public boolean isValidInput(String inputString) {
		char[] inputChar = inputString.toCharArray();
		if (!(inputChar.length == 2)) {
			return false;
		}
		if ((int)inputChar[0] < (int)'a' - 1 || ((int)'a' + GameMap.MATRIX_LENGTH) < (int)inputChar[0]) {
			return false;
		} 
		if ((int)inputChar[1] < (int)'0' - 1 || ((int)'0' + GameMap.MATRIX_LENGTH) < (int)inputChar[1]) {
			return false;
		}
		return true;
	}
	
	/**
	 * 入力された適切な文字列をMapPoint型に変換するメソッド.
	 * @param inputString 入力された適切な文字列
	 * @return 入力文字列をMapPoint型に変換したもの
	 */
	public MapPoint changeToMapPoint(String inputString) {
		char[] inputChar = inputString.toCharArray();
		return new MapPoint((int)inputChar[0] - (int)'a', (int)inputChar[1] - (int)'1');
	}
}
