package poker;

/**
 * 役の列挙型
 * @author Hotty
 */
public enum Rank {
	ROYAL_STRAIGHT_FLASH("ロイヤルストレートフラッシュ"),
	STRAIGHT_FLASH("ストレートフラッシュ"),
	FOUR_CARD("フォーカード"),
	FULL_HOUSE("フルハウス"),
	FLASH("フラッシュ"),
	STRAIGHT("ストレート"),
	THREE_CARD("スリーカード"),
	TWO_PAIR("ツーペア"),
	ONE_PAIR("ワンペア"),
	HIGHT_CARD("役なし");
	private String name;
	/**
	 * コンストラクタ
	 * @param name 日本語での呼び名
	 */
	private Rank(String name) {
		this.name = name;
	}
	/**
	 * 役のname(日本語名)を取得する
	 * @return 役に対応する日本語名
	 */
	public String getName(){
		return name;
	}
}
