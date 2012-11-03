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
	
	
	private static final int DIGITS = 10;
	
	private int[] primes;
	
	
	public String run() {
		primes = Library.listPrimes((int)Library.sqrt(pow(10, DIGITS)));
		
		long total = 0;
		for (int digit = 0; digit < 10; digit++) {  // For each repeating digit
			for (int rep = DIGITS; rep >= 0; rep--) {  // Search by the number of repetitions in decreasing order
				long sum = 0;
				int[] digits = new int[DIGITS];
				long count = pow(9, DIGITS - rep);
				level2:
				for (long i = 0; i < count; i++) {  // Try all possibilities for filling the non-repeating digits
					// Build initial array. For example, if DIGITS=7, digit=5, rep=4, i=123, then the array will be filled with 5,5,5,5,1,4,7.
					Arrays.fill(digits, 0, rep, digit);
					long temp = i;
					for (int j = 0; j < DIGITS - rep; j++) {
						int d = (int)(temp % 9);
						if (d >= digit)  // Skip the repeating digit
							d++;
						if (j > 0 && d > digits[DIGITS - j])  // If this is true, then after sorting, the array will be in an already-tried configuration
							continue level2;
						digits[DIGITS - 1 - j] = d;
						temp /= 9;
					}
					Arrays.sort(digits);  // Start at lowest permutation
					
					do {  // Go through all permutations
						if (digits[0] > 0) {  // Skip if the number has a leading zero, which means it has less than DIGIT digits
							long num = toInteger(digits);
							if (isPrime(num))
								sum += num;
						}
					} while (Library.nextPermutation(digits));
				}
				
				if (sum > 0) {  // Primes found; skip all lesser repetitions
					total += sum;
					break;
				}
			}
		}
		return Long.toString(total);
	}
	
	
	// Only valid if 1 < n <= 10^DIGITS
	private boolean isPrime(long n) {
		for (int p : primes) {
			if (n % p == 0)
				return false;
		}
		return true;
	}
	
	
	private static long toInteger(int[] digits) {
		long result = 0;
		for (int x : digits)
			result = result * 10 + x;
		return result;
	}
	
	
	private static long pow(int x, int y) {
		long z = 1;
		for (int i = 0; i < y; i++)
			z *= x;
		return z;
	}
	
}
