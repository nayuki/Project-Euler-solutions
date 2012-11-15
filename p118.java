/* 
 * Solution to Project Euler problem 118
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p118 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p118().run());
	}
	
	
	private int[] isPrime;
	
	
	public String run() {
		listPrimality(Library.pow(10, 9) - 1);
		
		// Try all permutations, and try all splits within each permutation
		int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		countBySize = new int[10];
		do countPrimeSets(digits, 0, 0);
		while (Library.nextPermutation(digits));
		
		// Eliminate permutation duplicates
		int count = 0;
		for (int i = 0; i < countBySize.length; i++)
			count += countBySize[i] / FACTORIAL[i];
		return Integer.toString(count);
	}
	
	
	private int[] countBySize;
	
	
	private void countPrimeSets(int[] digits, int start, int size) {
		if (start == digits.length)
			countBySize[size]++;
		else {
			for (int split = start + 1; split <= digits.length; split++) {
				if (isPrime(toInteger(digits, start, split)))
					countPrimeSets(digits, split, size + 1);
			}
		}
	}
	
	
	// Do prime sieve but store bitwise (instead of the usual Boolean array)
	private void listPrimality(int n) {
		isPrime = new int[n / 32 + 1];
		Arrays.fill(isPrime, ~0);
		isPrime[0] &= ~3;  // 0 and 1 are not prime
		
		// Sieve of Eratosthenes
		for (int i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (isPrime(i)) {
				for (int j = i * i; j <= n; j += i)
					isPrime[j >>> 5] &= ~(1 << (j & 0x1F));
			}
		}
	}
	
	
	private boolean isPrime(int x) {
		return (isPrime[x >>> 5] >>> (x & 0x1F) & 1) != 0;
	}
	
	
	private static int toInteger(int[] digits, int start, int end) {
		int result = 0;
		for (int i = start; i < end; i++)
			result = result * 10 + digits[i];
		return result;
	}
	
	
	// Hard-coded values for factorial(0), factorial(1), ..., factorial(9)
	private static int[] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
	
}
