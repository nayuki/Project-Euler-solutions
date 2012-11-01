/* 
 * Solution to Project Euler problem 77
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p077 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p077().run());
	}
	
	
	private static final int LIMIT = 1000;
	
	private int[] primes = Library.listPrimes(LIMIT);
	
	
	public String run() {
		primePartitions = new int[LIMIT][LIMIT];
		for (int[] array : primePartitions)
			Arrays.fill(array, -1);
		
		for (int i = 2; i < LIMIT; i++) {
			if (primePartitions(i, i) > 5000)
				return Integer.toString(i);
		}
		throw new RuntimeException("Not found");
	}
	
	
	private int[][] primePartitions;  // Memoization
	
	private int primePartitions(int n, int max) {
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
