/* 
 * Solution to Project Euler problem 111
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p111 implements EulerSolution {
	
	
	public static void main(String[] args) {
		System.out.println(new p111().run());
	}
	
	
	private static final int BLOCK_SIZE = 10000000;
	
	private int[] primes = Library.listPrimes((int)Library.sqrt(9999999999L));
	
	
	public String run() {
		// Classify each 9-digit prime number for each digit value
		long[][] classSums = new long[10][11];  // classSums[i][j] is the sum of all the prime numbers where digit i occurs exactly j times
		for (long i = 1000000000; i < 10000000000L; i += BLOCK_SIZE) {
			boolean[] isPrime = sievePrimes(i, BLOCK_SIZE);
			for (int j = 0; j < isPrime.length; j++) {
				if (isPrime[j]) {
					int[] digitCounts = getDigitCounts(i + j);
					for (int k = 0; k < digitCounts.length; k++)
						classSums[k][digitCounts[k]] += i + j;
				}
			}
		}
		
		long sum = 0;
		for (int i = 0; i < classSums.length; i++) {
			for (int j = classSums[i].length - 1; ; j--) {
				if (classSums[i][j] > 0) {
					sum += classSums[i][j];
					break;
				}
			}
		}
		return Long.toString(sum);
	}
	
	
	private boolean[] sievePrimes(long start, int n) {
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		for (int p : primes) {
			for (int i = (p - (int)(start % p)) % p; i < isPrime.length; i += p)
				isPrime[i] = false;
		}
		return isPrime;
	}
	
	
	// Returns the number of times each decimal digit occurred, excluding leading zeros.
	private static int[] getDigitCounts(long n) {
		int[] result = new int[10];
		for (; n != 0; n /= 10)
			result[(int)(n % 10)]++;
		return result;
	}
	
}
