import java.util.Calendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.NumberFormatException;

/**
 * 指定された年、月のカレンダーを表示するクラス.
 * @version 1.1,16 April 2014
 * @author Hotty
 */
public class MyCalendar {
	/**
	 * 指定された年、月のカレンダーを表示する流れを示すメインメソッド.
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int year = 0;
		int month = 0;

		System.out.println("カレンダーを出力します");
		try {
			year = recieveInputYear(reader, calendar.get(Calendar.YEAR));
			month = recieveInputMonth(reader, calendar.get(Calendar.MONTH));
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			return;
		}
		calendar.set(year, month, 1);
		int day = getFirstDayOfWeek(calendar);
		printTitle(calendar);
		alignFirstDate(day);
		printAllDate(calendar, day);
	}

	/**
	 * 年の入力を受け取るメソッド.
	 * @param reader BufferedReaderクラスのインスタンス
	 * @param thisYear 例に用いる今年の値
	 * @return 入力された年
	 */
	public static int recieveInputYear(BufferedReader reader, int thisYear) throws IllegalArgumentException {
		System.out.println("年を入力してください (例 : " + thisYear + ")");
		int year = 0;
		try {
			year = receiveInput(reader);
		} catch (IllegalArgumentException e) {
			throw e;
		}
		if (year < 1) {
			throw new IllegalArgumentException("年は１以上の自然数で入力してください。");
		}
		return year;
	}

	/**
	 * 月の入力を受け取るメソッド.
	 * @param reader BufferedReaderクラスのインスタンス
	 * @param thisMonth 例に用いる今月の値
	 * @return 入力された年
	 */
	public static int recieveInputMonth(BufferedReader reader, int thisMonth) throws IllegalArgumentException {
		System.out.println("月を入力してください (例 : " + (thisMonth + 1) + ")");
		int month = 0;
		try {
			month = receiveInput(reader) - 1;
		} catch (IllegalArgumentException e) {
			throw e;
		}
		if (month < 0 || 11 < month) {
			throw new IllegalArgumentException("月は１～１２の間で入力してください。");
		}
		return month;
	}

	/**
	 * 入力を受け取るメソッド.
	 * @param reader BufferedReaderクラスのインスタンス
	 * @return 入力された値
	 */
	public static int receiveInput(BufferedReader reader) throws IllegalArgumentException{
		String input = "";
		int number = 0;
		try {
			input = reader.readLine();
			number = Integer.parseInt(input);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("数値の形式が正しくありません。");
		}
		return number;
	}

	/**
	 * タイトルメッセージを表示するメソッド.
	 * @param calendar 本日の日付 
	 */
	 public static void printTitle(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		System.out.println(year + "年 " + month + "月");
		System.out.println("日 月 火 水 木 金 土");
	 }

	 /**
	 * 月初日の曜日を取得するメソッド.
	 * @param calendar 本日の日付 
	 * @return 月初日の曜日
	 */
	 public static int getFirstDayOfWeek(Calendar calendar) {
	 	int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(year, month, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	 }

	/**
	 * 1行目初日の開始位置を調整するメソッド.
	 * @param day 初日の曜日
	 */	 
	public static void alignFirstDate(int day) {
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
	public static void printAllDate(Calendar calendar, int day) {
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