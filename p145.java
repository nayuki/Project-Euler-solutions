/* 
 * Solution to Project Euler problem 145
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p145 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p145().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 9);
	
	
	public String run() {
		int count = 0;
		for (int i = 0; i < LIMIT; i++) {
			if (isReversible(i))
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private static boolean isReversible(int x) {
		return x % 10 != 0 && hasOnlyOddDigits(x + reverse(x));
	}
	
	
	// Assumes x does not have trailing zeros
	private static int reverse(int x) {
		int y = 0;
		for (; x != 0; x /= 10)
			y = y * 10 + x % 10;
		return y;
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
