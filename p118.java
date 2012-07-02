/* 
 * Solution to Project Euler problem 114
 * By Nayuki Minase
 */

import java.util.Arrays;


public class p118 {
	
	private static int[] isPrime;
	
	
	public static void main(String[] args) {
		// Do prime sieve but store bitwise (instead Boolean array)
		int n = 999999999;
		isPrime = new int[(n + 31) / 32];
		Arrays.fill(isPrime, 0xFFFFFFFF);
		isPrime[0] &= ~3;  // 0 and 1 are not prime
		
		// Sieve of Eratosthenes
		for (int i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (isPrime(i) && (long)i * i <= n) {
				for (int j = i * i; j <= n; j += i){
					isPrime[j >>> 5] &= ~(1 << (j & 0x1F));}
			}
		}
		
		int[] digits = new int[9];
		for (int i = 0; i < digits.length; i++)
			digits[i] = i + 1;
		
		countBySize = new int[10];
		do countPrimeSets(digits, 0, 0);
		while (Library.nextPermutation(digits));
		
		// Eliminate permutation duplicates
		int count = 0;
		for (int i = 0; i < countBySize.length; i++)
			count += countBySize[i] / factorial(i);
		System.out.println(count);
	}
	
	
	private static int[] countBySize;
	
	
	private static void countPrimeSets(int[] digits, int start, int size) {
		if (start == digits.length)
			countBySize[size]++;
		else {
			for (int split = start + 1; split <= digits.length; split++) {
				if (isPrime(toInteger(digits, start, split)))
					countPrimeSets(digits, split, size + 1);
			}
		}
	}
	
	
	private static int toInteger(int[] digits, int start, int end) {
		int result = 0;
		for (int i = start; i < end; i++)
			result = result * 10 + digits[i];
		return result;
	}
	
	
	private static int factorial(int n) {
		int product = 1;
		for (int i = 2; i <= n; i++)
			product *= i;
		return product;
	}
	
	
	private static boolean isPrime(int x) {
		return (isPrime[x >>> 5] >>> (x & 0x1F) & 1) != 0;
	}
	
}
