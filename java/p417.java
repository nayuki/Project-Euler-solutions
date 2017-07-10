/* 
 * Solution to Project Euler problem 417
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p417 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p417().run());
	}
	
	
	/* 
	 * To calculate the decimal expansion of 1/n digit by digit, we emulate the long division algorithm.
	 * We use a sequence of variables x_0, x_1, etc. to denote the numerical state of the algorithm, starting with x_0 = 1.
	 * To generate the i'th digit (0-based) after the decimal point, we compute floor(10 * x_{i-1} / n).
	 * (However, we for the purposes of this problem we are not interested in generating digits.)
	 * To calculate the next state, we compute x_{i+1} = (x_i * 10) mod n. (This is what we will analyze.)
	 * 
	 * We can see that with x_0 = 1, the i'th state is x_i = 10^i mod n.
	 * The decimal expansion cycles if and only if the states are the same at two indexes - in other words,
	 * there are some natural numbers i and j such that x_i = (10^i mod n) = (10^j mod n) = x_j.
	 * Because there are only n possible states, clearly the states must cycle with a period length <= n.
	 * 
	 * First, consider the simple case where n is coprime with 10, so 10 is invertible modulo n.
	 * Then taking our condition for cycling and dividing by 10^j on both sides, we get 10^{i-j} = 1 mod n.
	 * By Euler's theorem, 10^totient(n) = 1 mod n, so the decimal expansion is guaranteed to cycle
	 * after every totient(n) digits. The actual period is defined to be the smallest positive integer k
	 * such that 10^k = 1 mod n. From group theory, we know that k must divide totient(n);
	 * in other words, k is a factor of totient(n). How to find this factor will be discussed later.
	 * 
	 * Otherwise, the harder case is where n is not coprime with 10. Factorize out all the 2's and 5's
	 * so that n = 2^a * 5^b * m, where a and b are as large as possible, thus m is coprime with 10.
	 * If we get m = 1, then the decimal expansion will terminate, so we exit this case with a period of 0.
	 * Else, for the first max(a, b) states of x_i, the state necessarily does not repeat because x_i keeps gaining
	 * a factor of {2 or 5 or both} mod n, which it never loses afterward. After this, for i >= max(a, b), we always have
	 * x_i = 0 mod 2^a and x_i = 0 mod 5^b. So for i >= max(a, b), when trying to find cycles, we only need
	 * to consider the behavior of 10^i mod m, which reduces back to the simple case. Therefore, we divide out
	 * all the factors of 2 and 5 from n, and then we apply the same logic as the simple case on m instead of n.
	 * 
	 * To find the smallest positive k such that 10^k = 1 mod n, already knowing that 10^totient(n) = 1 mod n,
	 * first let's take k = totient(n). Now take some p > 1 (not necessarily prime) that is a factor of k.
	 * If 10^(k/p) = 1 mod n, then set k/p as the new k. We keep removing factors from k as long as the equation
	 * 10^k = 1 mod n still holds. When all the remaining factors are essential (i.e. removing any one of them
	 * will break the equation), we will have found the smallest satisfactory number k. This is the period.
	 */
	
	private static final int LIMIT = Library.pow(10, 8);
	
	public String run() {
		// All the prime numbers we need to consider
		int[] primes = Library.listPrimes(LIMIT);
		
		// smallestPrimeFactor[i] is the smallest (prime) factor of i
		int[] smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT);  // Requires 400 MB
		
		// A sorted array of almost all prime powers less than or equal to LIMIT.
		// Also, each prime power is associated with its totient.
		// For each i, primePowersAndTotients[i] = ((p^k) << 32) | (totient(p^k))
		// where p is some prime except 2 and 5, k >= 1 is an integer, and p^k <= LIMIT.
		// The sequence of p^k values begins with 3, 7, 9, 11, 13, 17, 19, 23, 27, 29, 31, 37, 41, 43, 47, 49, 53, ... .
		// (Before sorting, it would be 3^1, 3^2, ..., 3^16, 7^1, ..., 7^9, 11^1, ..., 11^7, ... .)
		long[] primePowersAndTotients = calcPrimePowersAndTotients(primes);
		
		// The decimal expansion periods for each 1/p^k where p^k is a prime power (p != 2, 5).
		// ppp[i] is the smallest positive integer such that 10^ppp[i] = 1 mod primePowers[i].
		int[] primePowerPeriods = calcPrimePowerPeriods(primePowersAndTotients, smallestPrimeFactor);
		smallestPrimeFactor = null;  // Garbage collection voodoo
		
		// Compute periods for each number that is coprime with 2 and 5,
		// based on the prime power decomposition of the number
		int[] periods = calcPeriods(primePowersAndTotients, primePowerPeriods);
		
		// Compute the period for all numbers (esp. those with factors of 2 and 5) and add 'em up
		return Long.toString(sumAllPeriods(periods));
	}
	
	
	private static long[] calcPrimePowersAndTotients(int[] primes) {
		LongList temp = new LongList(primes.length * 2);
		for (int p : primes) {
			if (p == 2 || p == 5)
				continue;
			for (long pow = p, tot = p - 1; pow <= LIMIT; pow *= p, tot *= p)
				temp.append(pow << 32 | tot);
		}
		long[] result = temp.toArray();
		Arrays.sort(result);
		return result;
	}
	
	
	private static int[] calcPrimePowerPeriods(long[] primePowersAndTotients, int[] smallestPrimeFactor) {
		int[] result = new int[primePowersAndTotients.length];
		for (int i = 0; i < primePowersAndTotients.length; i++) {
			long ppt = primePowersAndTotients[i];
			int primePow = (int)(ppt >>> 32);
			int period = (int)ppt;  // Start with some multiple of the true period
			
			// Remove unnecessary factors from the period
			int remainingFactors = period;
			while (remainingFactors > 1) {
				int q = smallestPrimeFactor[remainingFactors];
				if (Library.powMod(10, period / q, primePow) == 1)
					period /= q;
				remainingFactors /= q;
			}
			result[i] = period;
		}
		return result;
	}
	
	
	private static int[] calcPeriods(long[] primePowersAndTotients, int[] primePowerPeriods) {
		int[] result = new int[LIMIT + 1];
		result[1] = 1;  // Starter value for accumulating LCM
		for (int i = 0; i < primePowersAndTotients.length; i++) {
			int ppow = (int)(primePowersAndTotients[i] >>> 32);
			int period = primePowerPeriods[i];
			for (int j = 0, end = LIMIT / ppow; j <= end; j++) {
				if (result[j] != 0)
					result[j * ppow] = lcm(result[j], period);
			}
		}
		result[1] = 0;  // The true value, because 1/1 has a terminating decimal expansion
		return result;
	}
	
	
	private static long sumAllPeriods(int[] periods) throws AssertionError {
		long sum = 0;
		for (int i = 3; i <= LIMIT; i++) {
			int n = i;
			n >>>= Integer.numberOfTrailingZeros(n);  // Remove all factors of 2
			while (n % 5 == 0)  // Remove all factors of 5
				n /= 5;
			if (n > 1 && periods[n] == 0)
				throw new AssertionError();
			sum += periods[n];
		}
		return sum;
	}
	
	
	private static int lcm(int x, int y) {
		return x / Library.gcd(x, y) * y;
	}
	
	
	
	// A packed, limited-functionality version of ArrayList<Long>.
	private static final class LongList {
		
		private long[] data;
		private int length;
		
		
		public LongList(int initCapacity) {
			if (initCapacity < 1)
				throw new IllegalArgumentException();
			data = new long[initCapacity];
			length = 0;
		}
		
		
		public void append(long x) {
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
