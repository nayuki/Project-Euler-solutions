/* 
 * Solution to Project Euler problem 417
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p417 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p417().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	private int[] primePowers;  // Sorted powers of all primes except 2 and 5, up to LIMIT - e.g. 3, 7, 9, 11, 13, 17, 19, 23, 27, 29, ...
	private int[] orders;       // orders[i] is the smallest positive integer such that 10^orders[i] = 1 mod primePowers[i]
	private int[] smallestPrimeFactor;  // Requires 400 MB
	
	
	public String run() {
		int[] primes = Library.listPrimes(LIMIT);
		
		// Build sorted array of prime powers
		int len = 0;
		primePowers = new int[primes.length * 2];
		for (int p : primes) {
			if (p == 2 || p == 5)
				continue;
			for (long temp = p; temp <= LIMIT; temp *= p) {
				if (len == primePowers.length)
					primePowers = Arrays.copyOf(primePowers, len * 2);
				primePowers[len] = (int)temp;
				len++;
			}
		}
		primePowers = Arrays.copyOf(primePowers, len);
		Arrays.sort(primePowers);
		
		// Build array of smallest prime factors
		smallestPrimeFactor = new int[LIMIT + 1];
		for (int p : primes) {
			smallestPrimeFactor[p] = p;
			if ((long)p * p <= LIMIT) {
				for (int i = p * p; i <= LIMIT; i += p) {
					if (smallestPrimeFactor[i] == 0)
						smallestPrimeFactor[i] = p;
				}
			}
		}
		
		// Build array of orders
		orders = new int[primePowers.length];
		for (int i = 0; i < primePowers.length; i++) {
			int p = primePowers[i];
			int ord = totient(p);
			int remainingFactors = ord;
			while (remainingFactors > 1) {
				int q = smallestPrimeFactor[remainingFactors];
				if (Library.powMod(10, ord / q, p) == 1)
					ord /= q;
				remainingFactors /= q;
			}
			orders[i] = ord;
		}
		
		long sum = 0;
		for (int i = 3; i <= LIMIT; i++)
			sum += getCycleLength(i);
		return Long.toString(sum);
	}
	
	
	private int getCycleLength(int n) {
		// Remove factors of 2 and 5
		while ((n & 1) == 0)
			n >>>= 1;
		while (n % 5 == 0)
			n /= 5;
		if (n == 1)
			return 0;
		
		int result = 1;
		while (n > 1) {
			int p = smallestPrimeFactor[n];
			int q = p;
			for (n /= p; n % p == 0; n /= p)
				q *= p;
			// q is p^k, where p is the smallest prime that divides n (primary criteria) and k is maximal (secondary criteria)
			int i = Arrays.binarySearch(primePowers, q);
			result = lcm(orders[i], result);
		}
		return result;
	}
	
	
	private int totient(int n) {
		int result = 1;
		while (n > 1) {
			int p = smallestPrimeFactor[n];
			result *= p - 1;
			for (n /= p; n % p == 0; n /= p)
				result *= p;
		}
		return result;
	}
	
	
	private static int lcm(int x, int y) {
		return x / Library.gcd(x, y) * y;
	}
	
}
