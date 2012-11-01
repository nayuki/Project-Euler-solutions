/* 
 * Solution to Project Euler problem 49
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p049 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p049().run());
	}
	
	
	public String run() {
		boolean[] isPrime = Library.listPrimality(10000);
		for (int base = 1000; base < 10000; base++) {
			if (!isPrime[base])
				continue;
			for (int step = 1; step < 10000; step++) {
				int a = base + step * 1;
				int b = base + step * 2;
				if (       a < 10000 && isPrime[a] && hasSameDigits(a, base)
				        && b < 10000 && isPrime[b] && hasSameDigits(b, base)
				        && (base != 1487 || step != 3330))
					return "" + base + a + b;
			}
		}
		throw new RuntimeException("Not found");
	}
	
	
	private static boolean hasSameDigits(int x, int y) {
		char[] xdigits = Integer.toString(x).toCharArray();
		char[] ydigits = Integer.toString(y).toCharArray();
		Arrays.sort(xdigits);
		Arrays.sort(ydigits);
		return Arrays.equals(xdigits, ydigits);
	}
	
}
