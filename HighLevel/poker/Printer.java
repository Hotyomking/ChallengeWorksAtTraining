package poker;

import java.util.ArrayList;

/**
 * 種々のメッセージをコンソールに表示するクラス.
 * @author Hotty
 */
public class Printer {
	/**
	 * ゲーム開始時のメッセージを表示するメソッド.
	 */
	public static void printStartMessage() {
		System.out.println("交換するカードの番号を入力して下さい(例 : 135)。");
		System.out.println(Poker.ACTION_CHECK + "を入力すると交換しません。");
	}
	/**
	 * 手札を順番に表示するメソッド.
	 * @param hands 手札
	 */
	public static void printHands(ArrayList<Card> hands) {
		for (int i = 0; i < hands.size(); i++) {
			System.out.printf("%s:%sの%s\n",i + 1,hands.get(i).getSuit().getName(), hands.get(i).getNumber());
		}
	}
	/**
	 * 手役を表示するメソッド.
	 * @param rank 手役
	 */
	public static void printResult(Rank rank) {
		System.out.printf("役は%sでした。\n", rank.getName());
	}
	
	/**
	 * 入力に重複がある場合のメッセージ.
	 */
	public static void duplicationError() {
		System.out.println("入力に重複があります。");
	}
	/**
	 * デッキから引くカードが足りなくなった場合のメッセージ.
	 */
	public static void printNoCardError() {
		System.out.println("デッキにカードがなくなりました");
	}
}
