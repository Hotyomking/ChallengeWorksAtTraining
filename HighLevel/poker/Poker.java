package poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Poker {
	/** カードを交換しないコマンド */
	public static final String ACTION_CHECK = "0";
	/** 交換出来る回数 */
	private static final int MAX_LOOP = 100;
	/** ジョーカーの枚数 */
	private static final int JOKER = 0;
	
	public static void main(String[] args) {
		Poker poker = new Poker();
		poker.playGame();
	}
	private void playGame() {
		Judge judge = new Judge();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/** 現在の交換回数 */
		int  loop = 0;
		/** デッキ*/
		Deck deck = new Deck(JOKER);
		/** 手札 */
		ArrayList<Card> hands = new ArrayList<Card>();
		
		hands = deck.drawCards(5);
		while (loop < MAX_LOOP) {
			Printer.printStartMessage();
			Printer.printHands(hands);
			String input;
			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			if (input.equals(ACTION_CHECK)){	
				break;
			}
			if (!isValidInput(input)) {
				Printer.duplicationError();
				continue;
			}
			try {
				changeHands(hands, input, deck);
			} catch (IllegalArgumentException e) {
				Printer.printNoCardError();
				return;
			}
				loop++;
		}
		Printer.printHands(hands);
		Rank yourRank = judge.judgement(hands);
		Printer.printResult(yourRank);
	}
	private void changeHands(ArrayList<Card> hands, String input, Deck deck) {
		//何枚交換しようとしているか
		int changeSize = input.length();
		//何番を変更ようとしているか
		int changeNumber = Integer.parseInt(input);
		ArrayList<Card> newCards = new ArrayList<Card>();
		//新しく引いたカード
		newCards = deck.drawCards(changeSize);
		//新しく引いたカードのうち何枚目を加えるか
		int selectNumber = 0;
		//134が入力されたら、hands[4-1]、hands[3-1]、hands[1-1]の順に新しく入れ替わる。
		while (changeNumber >= 1) {
			hands.set(changeNumber % 10 - 1, newCards.get(selectNumber));
			changeNumber = changeNumber / 10;
			selectNumber++;
		}
	}
	private boolean isValidInput(String input) {
		if (!input.matches("^[1-5]+$")) return false;
		char[] inputChar = input.toCharArray();
		for (int i = 0; i < inputChar.length; i++) {
			for (int j = i + 1; j < inputChar.length ; j++) {
				if (inputChar[i] == inputChar[j]) return false;
			}
		}
		return true;
	}
	
}
