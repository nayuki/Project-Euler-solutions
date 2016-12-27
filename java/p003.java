/* 
 * Solution to Project Euler problem 3
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p003 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p003().run());
	}
	
	
	/* 
	 * By the fundamental theorem of arithmetic, every integer n > 1 has a unique factorization as a product of prime numbers.
	 * In other words, the theorem says that n = p_0 * p_1 * ... * p_{m-1}, where each p_i > 1 is prime but not necessarily unique.
	 * Now if we take the number n and repeatedly divide out its smallest factor (which must also be prime), then the last
	 * factor that we divide out must be the largest prime factor of n. For reference, 600851475143 = 71 * 839 * 1471 * 6857.
	 */
	public String run() {
		long n = 600851475143L;
		while (true) {
			long p = smallestFactor(n);
			if (p < n)
				n /= p;
			else
				return Long.toString(n);
		}
	}
	
	
	// Returns the smallest factor of n, which is in the range [2, n]. The result is always prime.
	private static long smallestFactor(long n) {
		if (n <= 1)
			throw new IllegalArgumentException();
		for (long i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return i;
		}
		return n;  // n itself is prime
	}
	
}
