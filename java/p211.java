/* 
 * Solution to Project Euler problem 211
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p211 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p211().run());
	}
	
	
	private static final int LIMIT = 64000000;
	
	
	// Requires about 512 MB of memory
	public String run() {
		// sigma2[i] is the sum of the square of i's divisors.
		// For example, sigma2[6] = 1^2 + 2^2 + 3^2 + 6^2 = 20.
		// Computing using a modification of the sieve of Eratosthenes.
		long[] sigma2 = new long[LIMIT];
		Arrays.fill(sigma2, 1, sigma2.length, 1);
		for (int i = 2; i < sigma2.length; i++) {
			if (sigma2[i] == 1) {
				for (int j = i; j < sigma2.length; j += i)
					sigma2[j] *= powerSquareSum(j, i);
			}
		}
		
		long sum = 0;
		for (int i = 1; i < sigma2.length; i++) {
			if (isPerfectSquare(sigma2[i]))
				sum += i;
		}
		return Long.toString(sum);
	}
	
	
	// Suppose i is the highest natural number such that k^i divides n.
	// Then this function returns k^(2*0) + k^(2*1) + k^(2*2) + ... + k^(2*i).
	// For example, with n=50 and k=5, i=2 because k^2 = 25 divides n = 50,
	// so the result is k^0 + k^2 + k^4 = 1 + 25 + 625 = 651.
	private static long powerSquareSum(int n, int k) {
		long result = 1;
		long k2 = (long)k * k;
		while (n % k == 0) {
			n /= k;
			result = result * k2 + 1;
		}
		return result;
	}
	
	
	private static boolean isPerfectSquare(long x) {
		long y = 0;
		for (long i = 1L << 31; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y * y == x;
	}
	
}
