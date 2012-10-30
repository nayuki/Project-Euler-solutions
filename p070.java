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
		int[] totients = Library.listTotients(LIMIT);
		
		int minNumer = 1;  // Dummy initial values
		int minDenom = 0;
		for (int i = 2; i < LIMIT; i++) {
			int tot = totients[i];
			if ((long)i * minDenom < (long)minNumer * tot && hasSameDigits(i, tot)) {
				minNumer = i;
				minDenom = tot;
			}
		}
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
