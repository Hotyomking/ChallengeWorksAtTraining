package poker;
import java.lang.IllegalArgumentException;
import java.math.BigInteger;

public class Card implements Comparable<Card> {
	public Suit getSuit() {
		return suit;
	}
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	private Suit suit;
	private int number;
	public enum Suit {
		CLUB("クラブ"),
		DIAMOND("ダイヤ"),
		HEART("ハート"),
		SPADE("スペード"),
		JOKER("ジョーカー");
		private String name;
		private Suit(String name){
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
	public Card(Suit suit, int number) throws IllegalArgumentException {
		if (suit != Suit.JOKER) {
			this.suit = suit;
		} else {
			throw new IllegalArgumentException(Suit.JOKER + "は数字を指定しないでください");
		}
		if (0 < number && number < 14) {
			this.number = number;
		} else {
			throw new IllegalArgumentException("1~13で指定してください");
		}
	}
	public Card(Suit suit) {
		if (suit == Suit.JOKER) {
			this.suit = suit;
			this.number = 0;
		} else {
			throw new IllegalArgumentException(Suit.JOKER+"以外は数字を指定してください");
		}
	}
	public int compareTo(Card other){
		if (this.suit == Suit.JOKER || other.suit == Suit.JOKER) {
			return BigInteger.valueOf(this.suit.ordinal() - other.suit.ordinal()).signum();
		} else {
			return BigInteger.valueOf((this.suit.ordinal() - other.suit.ordinal()) * 13 + (this.number - other.number)).signum();
		}
	}
}
