/* 
 * Solution to Project Euler problem 401
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p401 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p401().run());
	}
	
	
	private static final long LIMIT = 1000000000000000L;  // Must be less than 2^56
	private static final long MODULUS = Library.pow(10, 9);  // Should be less than 2^30
	
	
	/* 
	 * Consider the set of all integers from 1 to n, inclusive: {1, 2, ..., n}.
	 * Now form the set of divisors for each number:
	 *   1: {1}
	 *   2: {1, 2}
	 *   3: {1, 3}
	 *   4: {1, 2, 4}
	 *   5: {1, 5}
	 *   6: {1, 2, 3, 6}
	 *   et cetera until n.
	 * Next consider the multiset union of all these sets of divisors.
	 * 
	 * We know that for a given integer k > 0, it occurs as a divisor in this multiset
	 * exactly floor(n / k) times (we call this the "count"), which are namely the multiples of k.
	 * So instead of considering each integer and summing its squared divisors, we can consider
	 * each divisor from 1 to n and compute how much it contributes to the final sum, namely floor(n / k) * k^2.
	 * 
	 * A further observation is that when k is large, the count factor of floor(n / k) does not change often.
	 * (For example, for k from floor(n/2)+1 to n, this count is always 1.)
	 * So we can calculate the squared divisor sum for many numbers at a time.
	 * This is helpful for k > sqrt(n), and we can bring the run time from O(n) down to O(sqrt(n)).
	 * 
	 * For a given count of m = floor(n / k), which integer values of k yield this m?
	 * By the definition of floor, m <= n/k, so mk <= n, and k <= n/m, thus k <= floor(n/m).
	 * Also by definition, m > n/k - 1, so mk > n - k, and k(m + 1) > n, and k > n/(m+1), so k > floor(n/(m+1)).
	 * Together, we have: floor(n / (m + 1)) < k <= floor(n / m).
	 * 
	 * Useful fact: (sum k^2 for k=1 to n) = n(n + 1)(2n + 1) / 6.
	 */
	public String run() {
		// Can be any number from 1 to LIMIT, but somewhere near sqrt(LIMIT) is preferred
		int splitCount = (int)Library.sqrt(LIMIT);
		// Optimization: Put more weight on direct sums instead of slow BigInteger sums
		splitCount = Math.max(splitCount / 3, 1);
		// Consider divisors individually up and including this number
		int splitAt = (int)(LIMIT / (splitCount + 1));
		
		// Sum individual divisors
		long sum = 0;
		for (int i = 1; i <= splitAt; i++) {
			long count = LIMIT / i % MODULUS;
			long term = (long)i * i % MODULUS;
			term = term * count % MODULUS;
			sum = (sum + term) % MODULUS;
		}
		
		// Sum divisors grouped by count
		for (int i = splitCount; i >= 1; i--) {
			// Find all divisors with the count of i
			long start = LIMIT / (i + 1);  // Exclusive
			long end = LIMIT / i;  // Inclusive
			long sumSquares = sumSquaresMod(end) - sumSquaresMod(start);  // (start+1)^2 + (start+2)^2 + ... + end^2 mod MODULUS
			sumSquares = (sumSquares + MODULUS) % MODULUS;
			sum = (sum + i * sumSquares % MODULUS) % MODULUS;
		}
		return Long.toString(sum);
	}
	
	
	private static final BigInteger MODULUS_BI = BigInteger.valueOf(MODULUS);
	private static final BigInteger SIX_BI = BigInteger.valueOf(6);
	
	
	// Returns 1^2 + 2^2 + 3^2 + ... + n^2 mod MODULUS.
	private static long sumSquaresMod(long n) {
		// Compute y = (n)(n + 1)(2n + 1) / 6 mod MODULUS
		BigInteger x = BigInteger.valueOf(n);
		BigInteger y = x.multiply(x.add(BigInteger.ONE));
		y = y.multiply(x.shiftLeft(1).add(BigInteger.ONE));
		y = y.divide(SIX_BI);
		y = y.mod(MODULUS_BI);
		return y.longValue();
	}
	
}
