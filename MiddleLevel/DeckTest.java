package intermediate;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

public class DeckTest {

	@Test
	public void インスタンスを生成時適切な枚数のデッキを用意出来る() {
		int joker = 7;
		Deck deck = new Deck(joker);
		int actual = deck.card.size();
		assertThat(actual, is(52 + joker));
	}
	@Test
	public void インスタンス生成時引数に負の数字を入れるとジョーカーが０枚の時と同じ振る舞いをする() {
		int joker = -3;
		Deck deck = new Deck(joker);
		int actual = deck.card.size();
		assertThat(actual, is(52));
	}
	@Test
	public void インスタンス生成時毎回異なる並びのデッキが用意出来る() {
		int joker = 3;
		Deck deck1 = new Deck(joker);
		Deck deck2 = new Deck(joker);
		assertThat(deck1, is(not(deck2)));
	}
	@Test
	public void インスタンス生成時指定枚数のJOKERが含まれたデッキを用意出来る() {
		int makeJoker = 5;
		Deck deck = new Deck(makeJoker);
		int actualJoker = 0;
		for (int i = 0; i < deck.card.size(); i++) {
			if (deck.card.get(i).suit == Card.Suit.JOKER ) {
				actualJoker++;
			}
		}
		assertThat(actualJoker, is(makeJoker));
	}
	@Test
	public void デッキから指定枚数を引いて残り枚数を確認することが出来る() {
		int makeJoker = 3;
		Deck deck = new Deck(makeJoker);
		int drawCard = 10;
		deck.drawCards(drawCard);
		int actual = deck.checkNumberOfRest();
		assertThat(actual, is(52 + makeJoker - drawCard));
	}
	
	
	
}
