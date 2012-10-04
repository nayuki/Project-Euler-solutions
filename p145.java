/* 
 * Solution to Project Euler problem 145
 * By Nayuki Minase
 */


public class p145 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 1000000000; i++) {
			if (isReversible(i))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static boolean isReversible(int x) {
		return x % 10 != 0 && hasOnlyOddDigits(x + reverse(x));
	}
	
	
	private static int reverse(int x) {
		return Integer.parseInt(Library.reverse(Integer.toString(x)));
	}
	
	
	private static boolean hasOnlyOddDigits(int x) {
		while (x != 0) {
			if (x % 2 == 0)
				return false;
			x /= 10;
		}
		return true;
	}
	
}
