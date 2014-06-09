package intermediate;

public class Intermediate2 {	
	/**
	 * 重複を取り除いた配列を返す.
	 * @param number 
	 * @return
	 */
	public static int[] uniq(int[] oldList){
		if (oldList.length == 0) {
			return new int[0]; 
		}
		int[] temporaryList = new int[oldList.length];
		temporaryList[0] = oldList[0];
		int newLength = 1;
		for (int i = 1; i < oldList.length; i++) {
			if (oldList[i] != oldList[i - 1]) {
				temporaryList[newLength] = oldList[i];
				newLength++;
			}
		}
		int[] newList = new int[newLength];
		System.arraycopy(temporaryList, 0, newList, 0, newList.length);		
		return newList;
	}
}
