/* 
 * Solution to Project Euler problem 127
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p127 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p127().run());
	}
	
	
	/* 
	 * A straightforward solution would look like this:
	 *   for (int c = 2; c < LIMIT; c++) {
	 *      for (int a = 1; a < c; a++) {
	 *        int b = c - a;
	 *        if (isAbcHit(a, b, c))
	 *          ans += c;
	 *      }
	 *   }
	 * 
	 * Here are some observations that lead to optimizations:
	 * - By Euclid's GCD algorithm, gcd(c,b) = gcd(a+b,b) = gcd(a,b) = gcd(a,a+b) = gcd(a,c).
	 *   Hence gcd(a,b) = 1 if and only if gcd(a,c) = 1 and gcd(b,c) = 1.
	 *   We only need to compute and check one of these three GCDs.
	 * - Since {a, b, c} are mutually coprime, we have rad(a * b * c) = rad(a) * rad(b) * rad(c).
	 * - For each integer n >= 2, we have 2 <= rad(n) <= n.
	 * - Therefore, it is clear that rad(c) <= rad(a * b * c). If rad(c) = c, then no solution exists for this c.
	 */
	
	private static final int LIMIT = 120000;
	
	public String run() {
		// Modification of the sieve of Eratosthenes
		int[] rads = new int[LIMIT];
		Arrays.fill(rads, 1, rads.length, 1);
		for (int i = 2; i < rads.length; i++) {
			if (rads[i] == 1) {
				for (int j = i; j < rads.length; j += i)
					rads[j] *= i;
			}
		}
		
		long sum = 0;
		for (int c = 2; c < LIMIT; c++) {
			if (rads[c] == c)
				continue;
			for (int a = 1, end = (c - 1) / 2; a <= end; a++) {
				int b = c - a;
				assert a < b;
				if ((long)rads[a] * rads[b] * rads[c] < c && Library.gcd(a, b) == 1)
					sum += c;
			}
		}
		return Long.toString(sum);
	}
	
}
