/* 
 * Solution to Project Euler problem 60
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;
import java.util.BitSet;


public final class p060 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p060().run());
	}
	
	
	private static final int PRIME_LIMIT = 100000;  // Arbitrary initial cutoff
	
	private int[] primes = Library.listPrimes(PRIME_LIMIT);
	
	// Memoization
	private BitSet isConcatPrimeKnown;
	private BitSet isConcatPrime;
	
	
	public String run() {
		isConcatPrimeKnown = new BitSet(primes.length * primes.length);
		isConcatPrime      = new BitSet(primes.length * primes.length);
		
		int sumLimit = PRIME_LIMIT;
		while (true) {
			int sum = findSetSum(new int[]{}, 5, sumLimit - 1);
			if (sum == -1)  // No smaller sum found
				return Integer.toString(sumLimit);
			sumLimit = sum;
		}
	}
	
	
	/* 
	 * Tries to find any suitable set and return its sum, or -1 if none is found.
	 * A set is suitable if it contains only primes, its size is 'targetSize',
	 * its sum is less than or equal to 'sumLimit', and each pair concatenates to a prime.
	 * 'prefix' is an array of ascending indices into the 'primes' array,
	 * which describes the set found so far.
	 * The function blindly assumes that each pair of primes in 'prefix' concatenates to a prime.
	 * 
	 * For example, findSetSum(new int[]{1, 3, 28}, 5, 10000) means "find the sum of any set
	 * where the set has size 5, consists of primes with the lowest elements being {3, 7, 109},
	 * has sum 10000 or less, and has each pair concatenating to form a prime".
	 */
	private int findSetSum(int[] prefix, int targetSize, int sumLimit) {
		if (prefix.length == targetSize) {
			int sum = 0;
			for (int i : prefix)
				sum += primes[i];
			return sum;
			
		} else {
			int i;
			if (prefix.length == 0)
				i = 0;
			else
				i = prefix[prefix.length - 1] + 1;
			
			outer:
			for (; i < primes.length && primes[i] <= sumLimit; i++) {
				for (int j : prefix) {
					if (!isConcatPrime(i, j) || !isConcatPrime(j, i))
						continue outer;
				}
				
				int[] appended = Arrays.copyOf(prefix, prefix.length + 1);
				appended[appended.length - 1] = i;
				int sum = findSetSum(appended, targetSize, sumLimit - primes[i]);
				if (sum != -1)
					return sum;
			}
			return -1;
		}
	}
	
	
	// Tests whether parseInt(toString(x) + toString(y)) is prime.
	private boolean isConcatPrime(int x, int y) {
		int index = x * primes.length + y;
		if (isConcatPrimeKnown.get(index))
			return isConcatPrime.get(index);
		
		x = primes[x];
		y = primes[y];
		int mult = 1;
		for (int temp = y; temp != 0; temp /= 10)
			mult *= 10;
		
		boolean result = isPrime((long)x * mult + y);
		isConcatPrimeKnown.set(index);
		isConcatPrime.set(index, result);
		return result;
	}
	
	
	private boolean isPrime(long x) {
		if (x < 0)
			throw new IllegalArgumentException();
		else if (x == 0 || x == 1)
			return false;
		else {
			long end = Library.sqrt(x);
			for (int p : primes) {
				if (p > end)
					break;
				if (x % p == 0)
					return false;
			}
			for (long i = primes[primes.length - 1] + 2; i <= end; i += 2) {
				if (x % i == 0)
					return false;
			}
			return true;
		}
	}
	
}
