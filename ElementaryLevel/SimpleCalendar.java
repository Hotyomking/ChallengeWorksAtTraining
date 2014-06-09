import java.util.Calendar;

/**
 * 今月のカレンダーを表示するクラス.
 * @version 1.0,14 April 2014
 * @author Hotty
 */
public class SimpleCalendar {
	/**
	 * 今月のカレンダーを表示する流れを示すメインメソッド.
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int firstDay = getFirstDayOfMonth(calendar);
		showTitle(calendar);
		alignFirstWeek(firstDay);
		placeAllDay(calendar, firstDay);
	}

	/**
	 * タイトルメッセージを表示するメソッド.
	 * @param calendar 本日の日付 
	 */
	 public static void showTitle(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		System.out.println(year + "年" + month + "月のカレンダーを出力します");
		System.out.println(year + "年 " + month + "月");
		System.out.println("日 月 火 水 木 金 土");
	 }

	 /**
	 * 月初日の曜日を取得するメソッド.
	 * @param calendar 本日の日付 
	 * @return 月初日の曜日
	 */
	 public static int getFirstDayOfMonth(Calendar calendar) {
	 	int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(year, month, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	 }

	/**
	 * 1行目初日の開始位置を調整するメソッド.
	 * @param day 月初日の曜日
	 */	 
	public static void alignFirstWeek(int day) {
		if (day != 0) {
	 		for (int i = 0; i < day - 1; i++) {
				System.out.print("   ");
			}
		}
	}

	/**
	 * 日付を書き込むメソッド.
	 * @param calendar 本日の日付
	 * @param day 初日の曜日
	 */	 
	public static void placeAllDay(Calendar calendar, int day) {
		int max = calendar.getActualMaximum(Calendar.DATE);
		for (int i = 1; i < max + 1; i++) {
			if (i < 10) {
				System.out.print(" " + i + " ");	
			} else {
				System.out.print(i + " ");
			}
			if ((i + day) % 7 == 1) {
				System.out.println();
			}
		}
		System.out.println();
	}
}