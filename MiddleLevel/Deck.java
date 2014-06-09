package intermediate;
import java.util.ArrayList;
/**
 * トランプのデッキを表現するクラス.
 * @author TanakaYuki
 * @version 1.0, 18 April 2014
 */
public class Deck {
	
	ArrayList<Card> card = new ArrayList<Card>();
	
	/**
	 * インスタンス生成時に基本52枚と指定枚のジョーカーを含めたデッキを作るコンストラクタ。
	 * @param joker
	 */
	public Deck(int joker) {
		if (joker < 0) {
			joker = 0;
		}
		ArrayList<Integer> restList = new ArrayList<Integer>();
		for (int i = 0; i < 52 + joker; i++) {
			restList.add(i); 
		}
		int serialNumber;
		for (int i = 0; i < 52 + joker; i++) {
			int randomNumber = (int)(Math.random() * (52 + joker - i));
			serialNumber = restList.get(randomNumber);
			restList.remove(randomNumber);
			if (serialNumber < 52){
				this.card.add(new Card(Card.Suit.values()[(int)serialNumber / 13], serialNumber % 13 + 1)); 
			} else {
				this.card.add(new Card(Card.Suit.JOKER));
			}
		}
	}
	
	/**
	 * デッキの残り枚数を確認するメソッド
	 * @return デッキの残り枚数
	 */
	public int checkNumberOfRest() {
		return card.size();
	}
	
	/**
	 * デッキから指定枚数を引くメソッド.
	 * @param デッキから減らすカード枚数
	 */
	public ArrayList<Card> drawCards(int drawNumber) throws IllegalArgumentException {
		ArrayList<Card> hand = new ArrayList<Card>();
		if (drawNumber > checkNumberOfRest()) {
			throw new IllegalArgumentException("指定枚数が残枚数を超えています");
		}
			
		for (int i = 0; i <drawNumber; i++) {
			hand.add(this.card.get(0));
			this.card.remove(0);
		}
		return hand;
	}
}
