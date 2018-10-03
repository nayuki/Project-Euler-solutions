/* 
 * Solution to Project Euler problem 211
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p211 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p211().run());
	}
	
	
	private static final int LIMIT = 64000000;
	
	
	// Requires about 512 MB of memory
	public String run() {
		// sigma2[i] is the sum of the square of i's divisors.
		// For example, sigma2[6] = 1^2 + 2^2 + 3^2 + 6^2 = 20.
		long[] sigma2 = new long[LIMIT];
		for (int i = 1; i < sigma2.length; i++) {
			long i2 = (long)i * i;
			for (int j = i; j < sigma2.length; j += i)
				sigma2[j] += i2;
		}
		
		long sum = 0;
		for (int i = 1; i < sigma2.length; i++) {
			if (isPerfectSquare(sigma2[i]))
				sum += i;
		}
		return Long.toString(sum);
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
