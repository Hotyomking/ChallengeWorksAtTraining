package intermediate;

public class Test {
	public static void main(String[] args) {
		Deck deck = new Deck(1);
		for (int i=0; i<53; i++) {
			System.out.println(deck.card.get(i).suit + "," + deck.card.get(i).number);
		}
	}
}
