package poker;

import java.util.ArrayList;
import java.util.Arrays;

import poker.Card.Suit;

/**
 * 役の審判をするクラス.
 * @author Hotty
 *
 */
public class Judge {
	/**
	 * 手札が示すランクを判定するメソッド.
	 * @param hands ユーザの手札
	 * @return 手札が持っているランク（役）
	 */
	public Rank judgement(ArrayList<Card> hands) {
		//indexの数字がvalue枚持つように設定するint配列
		int[] numberList = new int[13];
		for (Card hand : hands) {
			numberList[hand.getNumber() - 1]++;
		}
		//contains()やsize()を用いるためにArrayListに入れ直す
		ArrayList<Integer> numberArray = new ArrayList<Integer>();
		for (int i = 0; i < 13; i++) {
			if (numberList[i] != 0) numberArray.add(numberList[i]);
		}
		
		if (numberArray.contains(4)) 
			return Rank.FOUR_CARD;
		if (numberArray.contains(3))
			//3が含まれてサイズ2の場合は3枚2枚,3が含まれてサイズ2じゃない場合は1枚1枚3枚(サイズ3))
			return numberArray.size() == 2 ? Rank.FULL_HOUSE: Rank.THREE_CARD;
		if (numberArray.contains(2))
			//2が含まれてサイズ3の場合は2枚2枚1枚,2が含まれてサイズ3じゃない場合は1枚1枚1枚2枚(サイズ4))
			return numberArray.size() == 3 ? Rank.TWO_PAIR: Rank.ONE_PAIR;
		
		if (isStraight(numberList) && isFlash(hands))
			//ストレートフラッシュで1のカードが含まれていたら(含まれている場合は1枚)ロイヤルストレートフラッシュである
			return numberList[0] == 1 ? Rank.ROYAL_STRAIGHT_FLASH : Rank.STRAIGHT_FLASH;
		if (isStraight(numberList)) 
			return Rank.STRAIGHT;
		if (isFlash(hands)) 		
			return Rank.FLASH;
		System.out.println(Arrays.asList(numberList).size());
		return Rank.HIGHT_CARD; 
	}
	/**
	 * フラッシュ（全部同じSuit）かどうかを判定するメソッド
	 * @param hands 手札
	 * @return 全て同じSuitならtrue
	 */
	private boolean isFlash(ArrayList<Card> hands) {
		Suit thisSuit = hands.get(0).getSuit();
		for (int i = 1; i < hands.size(); i++) {
			if (hands.get(i).getSuit() != thisSuit) return false;
		}
		return true;
	}
	
	/**
	 * ストレート(2.3.4....13.1の順で並べたときに連続する値5つをとっている)かどうかを判定するメソッド
	 * @param numberList 手札にindexの数字のカードがvalue枚入っていることを示す配列
	 * @return 2.3...13.1の順で連続していればtrue
	 */
	private boolean isStraight(int[] numberList) {
		double unionNumber = 0;
		for (int i = 0; i < 13 ; i++) {
			//ストレートの判定では1と13がつながるのでnumberList[0](Aの枚数)を一番大きくする
			if (i == 0) {
				unionNumber = unionNumber + numberList[i] * Math.pow(2, 13);
			}
			unionNumber = unionNumber + numberList[i] * Math.pow(2, i);			
		}
		//2^4+2^3+2^2+2^1+2^0
		return ((int)unionNumber % 31 == 0);
	}
}