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
	
	
	public String run() {
		int[] primes = Library.listPrimes(LIMIT);
		
		// Compute all smallest prime factors
		int[] smallestPrimeFactor = new int[LIMIT + 1];  // Requires 400 MB
		for (int p : primes) {
			smallestPrimeFactor[p] = p;
			if ((long)p * p <= LIMIT) {
				for (int i = p * p; i <= LIMIT; i += p) {
					if (smallestPrimeFactor[i] == 0)
						smallestPrimeFactor[i] = p;
				}
			}
		}
		
		// Build sorted array of prime powers.
		// primePowersAndTotients[i] = ((p^k) << 32) | (totient(p^k)), for some prime p and some integer k >= 1
		// p is all the primes in 'primes' except 2 and 5. k is such that all p^k <= LIMIT.
		// The array is sorted in ascending order of p^k. The sequence of p^k begins with 3, 7, 9, 11, 13, 17, 19, 23, 27, 29, ... .
		DynamicArray ppTotTemp = new DynamicArray(primes.length * 2);
		for (int p : primes) {
			if (p == 2 || p == 5)
				continue;
			for (long temp = p, tot = p - 1; temp <= LIMIT; temp *= p, tot *= p)
				ppTotTemp.add(temp << 32 | tot);
		}
		long[] primePowersAndTotients = ppTotTemp.toArray();
		Arrays.sort(primePowersAndTotients);
		
		// Build array of cycle lengths
		// ppcl[i] is the smallest positive integer such that 10^ppcl[i] = 1 mod primePowers[i]
		int[] primePowerCycleLengths = new int[primePowersAndTotients.length];
		for (int i = 0; i < primePowersAndTotients.length; i++) {
			long ppTot = primePowersAndTotients[i];
			int p = (int)(ppTot >>> 32);
			int ord = (int)ppTot;
			int remainingFactors = ord;
			while (remainingFactors > 1) {
				int q = smallestPrimeFactor[remainingFactors];
				if (Library.powMod(10, ord / q, p) == 1)
					ord /= q;
				remainingFactors /= q;
			}
			primePowerCycleLengths[i] = ord;
		}
		// smallestPrimeFactor is now dead
		
		// Compute cycle lengths from building numbers using prime powers
		int[] cycleLengths = new int[LIMIT + 1];
		cycleLengths[1] = 1;  // Starter value for accumulating LCM
		for (int i = 0; i < primePowersAndTotients.length; i++) {
			int p = (int)(primePowersAndTotients[i] >>> 32);
			int ord = primePowerCycleLengths[i];
			for (int j = 0, end = LIMIT / p; j <= end; j++) {
				if (cycleLengths[j] != 0)
					cycleLengths[j * p] = lcm(cycleLengths[j], ord);
			}
		}
		cycleLengths[1] = 0;  // The true value that we want
		
		// Add up the cycle lengths
		long sum = 0;
		for (int i = 3; i <= LIMIT; i++) {
			// Remove factors of 2 and 5
			int n = i;
			while ((n & 1) == 0)
				n >>>= 1;
			while (n % 5 == 0)
				n /= 5;
			if (n > 1 && cycleLengths[n] == 0)
				throw new AssertionError();
			sum += cycleLengths[n];
		}
		return Long.toString(sum);
	}
	
	
	private static int lcm(int x, int y) {
		return x / Library.gcd(x, y) * y;
	}
	
	
	
	private static class DynamicArray {
		
		private long[] data;
		private int length;
		
		
		public DynamicArray(int initCapacity) {
			if (initCapacity < 1)
				throw new IllegalArgumentException();
			data = new long[initCapacity];
			length = 0;
		}
		
		
		public void add(long x) {
			if (length == data.length)
				data = Arrays.copyOf(data, length * 2);
			data[length] = x;
			length++;
		}
		
		
		public long[] toArray() {
			return Arrays.copyOf(data, length);  // Trim
		}
		
	}
	
}
