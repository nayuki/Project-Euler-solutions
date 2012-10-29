/* 
 * Solution to Project Euler problem 49
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p049 {
	
	private static boolean[] isPrime = Library.listPrimality(10000);
	
	
	public static void main(String[] args) {
		for (int base = 1000; base < 10000; base++) {
			if (!isPrime[base])
				continue;
			middle:
			for (int stepSize = 1; stepSize < 10000; stepSize++) {
				for (int i = 1; i <= 2; i++) {
					int n = base + i * stepSize;
					if (n >= 10000 || !isPrime[n] || !hasSameDigits(base, n))
						continue middle;
				}
				System.out.println(base + " " + (base + stepSize) + " " + (base + stepSize * 2));
			}
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
