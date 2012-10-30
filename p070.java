/* 
 * Solution to Project Euler problem 70
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p070 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p070().run());
	}
	
	
	public String run() {
		int minNumerator = 10;
		int minDenominator = 1;
		for (int i = 2; i < 10000000; i++) {
			int tot = Library.totient(i);
			if (hasSameDigits(i, tot) && (long)i * minDenominator < (long)minNumerator * tot) {
				minNumerator = i;
				minDenominator = tot;
			}
		}
		return Integer.toString(minNumerator);
	}
	
	
	private static boolean hasSameDigits(int x, int y) {
		char[] xdigits = Integer.toString(x).toCharArray();
		char[] ydigits = Integer.toString(y).toCharArray();
		Arrays.sort(xdigits);
		Arrays.sort(ydigits);
		return Arrays.equals(xdigits, ydigits);
	}
	
}
