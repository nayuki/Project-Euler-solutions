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
	
	
	private static final int LIMIT = Library.pow(10, 7);
	
	public String run() {
		int minNumer = 1;  // Initially infinity
		int minDenom = 0;
		int[] totients = Library.listTotients(LIMIT - 1);
		for (int n = 2; n < totients.length; n++) {
			int tot = totients[n];
			if ((long)n * minDenom < (long)minNumer * tot && hasSameDigits(n, tot)) {
				minNumer = n;
				minDenom = tot;
			}
		}
		if (minDenom == 0)
			throw new RuntimeException("Not found");
		return Integer.toString(minNumer);
	}
	
	
	private static boolean hasSameDigits(int x, int y) {
		char[] xdigits = Integer.toString(x).toCharArray();
		char[] ydigits = Integer.toString(y).toCharArray();
		Arrays.sort(xdigits);
		Arrays.sort(ydigits);
		return Arrays.equals(xdigits, ydigits);
	}
	
}
