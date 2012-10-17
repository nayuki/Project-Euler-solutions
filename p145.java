/* 
 * Solution to Project Euler problem 145
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p145 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 0, end = Library.pow(10, 9); i < end; i++) {
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
