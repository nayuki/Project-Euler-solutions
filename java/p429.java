/* 
 * Solution to Project Euler problem 429
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p429 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p429().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	private static final int MODULUS = 1000000009;
	
	
	/* 
	 * Let n be an arbitrary positive integer. Suppose n is factorized as p1^k1 * p2^k2 * ... * {p_m}^{k_m},
	 * where the p's are prime and distinct (thus the k's are as large as possible).
	 * Let {p1^k1, p2^k2, ..., {p_m}^{k_m}} be the set of "maximal prime powers".
	 * Then all the unitary divisors of n are exactly all the subsets of maximal prime powers,
	 * where each subset is viewed as a product of its elements.
	 * 
	 * For n!, its prime factorization uses and only uses all prime numbers from 1 to n (inclusive).
	 * For each prime p, the number n! has exactly floor(n/p) + floor(n/p^2) + floor(n/p^3) + ... factors of p.
	 * Thus we can calculate the p's and k's quite easily.
	 * 
	 * To solve the remaining parts of the problem, we use dynamic programming.
	 * Suppose we have found all the unitary divisors that are products of maximal prime powers less than {p_i}^{k_i},
	 * and suppose this set is {a, b, c}. Then when we include {p_i}^{k_i} into consideration, we double the size of the set
	 * because now {a * {p_i}^{k_i}, b * {p_i}^{k_i}, c * {p_i}^{k_i}} are also unitary divisors.
	 */
	public String run() {
		int[] primes = Library.listPrimes(LIMIT);
		long sum = 1;  // In this computation, it's actually a product
		for (int p : primes) {
			int power = countFactors(LIMIT, p);
			sum *= 1 + Library.powMod(p, power * 2, MODULUS);
			sum %= MODULUS;
		}
		return Long.toString(sum);
	}
	
	
	// Returns the number of factors of p (prime) in factorial(n)
	private static int countFactors(int n, int p) {
		if (n == 0)
			return 0;
		else
			return n / p + countFactors(n / p, p);
	}
	
}
