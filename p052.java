/* 
 * Solution to Project Euler problem 52
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p052 {
	
	public static void main(String[] args) {
		outer:
		for (int i = 1; ; i++) {
			for (int j = 2; j < 6; j++) {
				if (!hasSameDigits(i * j, i * (j + 1)))
					continue outer;
			}
			System.out.println(i);
			break;
		}
	}
	
	
	private static boolean hasSameDigits(int x, int y) {
		char[] xdigits = Integer.toString(x).toCharArray();
		char[] ydigits = Integer.toString(y).toCharArray();
		Arrays.sort(xdigits);
		Arrays.sort(ydigits);
		return Arrays.equals(xdigits, ydigits);
	}
	
}
