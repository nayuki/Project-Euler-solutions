/* 
 * Solution to Project Euler problem 77
 * By Nayuki Minase
 */


import java.util.Arrays;


public class p077 {
	
	private static int LIMIT = 1000;
	
	
	public static void main(String[] args) {
		for (int i = 2; i < LIMIT; i++) {
			if (primePartitions(i, i) > 5000) {
				System.out.println(i);
				break;
			}
		}
	}
	
	
	private static int[] primes;  // 2, 3, 5, 7, 11, 13, 17, 19, ...
	
	private static int[][] primePartitions;  // Memoization
	
	static {
		boolean[] isPrime = Library.listPrimality(LIMIT);
		int[] tempPrimes = new int[isPrime.length];
		int primesLen = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				tempPrimes[primesLen] = i;
				primesLen++;
			}
		}
		primes = Arrays.copyOf(tempPrimes, primesLen);
		
		primePartitions = new int[LIMIT][LIMIT];
		for (int[] array : primePartitions)
			Arrays.fill(array, -1);
	}
	
	
	private static int primePartitions(int n, int max) {
		if (primePartitions[n][max] != -1)
			return primePartitions[n][max];
		
		else {
			int result;
			if (n == 0)
				result = 1;
			else {
				result = 0;
				for (int p : primes) {
					if (p <= n && p <= max)
						result += primePartitions(n - p, p);
				}
			}
			
			primePartitions[n][max] = result;
			return result;
		}
	}
	
}
