package intermediate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class CardTest {

	@Test
	public void Suitが同じならば数字が大きい方が強い() {
		Card card = new Card(Card.Suit.CLUB, 12);
		int actual = card.compareTo(new Card(Card.Suit.CLUB, 10));
		assertThat(actual, is(1));
	}
	@Test
	public void Suitがダイヤモンドならばクラブのカードよりも強い() {
		Card card = new Card(Card.Suit.CLUB, 10);
		int actual = card.compareTo(new Card(Card.Suit.DIAMOND, 8));
		assertThat(actual, is(-1));
	}
	@Test
	public void Suitがハートならばダイヤモンドのカードよりも強い() {
		Card card = new Card(Card.Suit.HEART, 8);
		int actual = card.compareTo(new Card(Card.Suit.DIAMOND, 10));
		assertThat(actual, is(1));
	}
	@Test
	public void Suitがスペードならばハートのカードよりも強い() {
		Card card = new Card(Card.Suit.HEART, 10);
		int actual = card.compareTo(new Card(Card.Suit.SPADE, 8));
		assertThat(actual, is(-1));
	}
	@Test
	public void ジョーカーが一番大きい() {
		Card card = new Card(Card.Suit.SPADE, 11);
		int actual = card.compareTo(new Card(Card.Suit.JOKER));
		assertThat(actual, is(-1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void JOKERは数字を指定するとIllegalArgumentExceptionを返す(){
		new Card(Card.Suit.JOKER, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void JOKER以外は数字を指定しないとIllegalArgumentExceptionを返す(){
		new Card(Card.Suit.HEART);
	}
	@Test(expected = IllegalArgumentException.class)
	public void JOKER以外は1から13以外の数字を指定するとIllegalArgumentExceptionを返す() {
		new Card(Card.Suit.HEART, 0);
	}
}
