package intermediate;

public class Intermediate1 {	
	/**
	 * 10進数の数を2進数表記に変換する.
	 * @param number 10進数で与えられた数
	 * @return
	 */
	public static String toBinaryString(int number){
		if (number == 0) {
			return "0";
		}
		if (number < 0) {
			throw new IllegalArgumentException();
		}
		final int digit = 10;
		int binary = 0;
		for (int i = digit - 1;i >= 0; i--) {
			if (number >= Math.pow(2,i)) {
				binary += Math.pow(10,i);
				number -= Math.pow(2, i);
			} 
		}
		
		return Integer.toString(binary);
	}
}
