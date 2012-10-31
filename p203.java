/* 
 * Solution to Project Euler problem 203
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.HashSet;
import java.util.Set;


public final class p203 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p203().run());
	}
	
	
	private long[] PRIMES_SQUARED = listPrimesSquared(1 << 24);
	
	
	public String run() {
		Set<Long> numbers = new HashSet<Long>();
		long[] row = new long[51];
		row[0] = 1;
		for (int i = 0; i <= 50; i++) {  // Row number
			middle:
			for (long x : row) {
				if (x == 0)
					break;
				for (long p : PRIMES_SQUARED) {
					if (p > x)
						break;
					if (x % p == 0)
						continue middle;
				}
				numbers.add(x);
			}
			
			for (int j = row.length - 2; j >= 1; j--)  // Compute next row
				row[j] += row[j - 1];
		}
		
		long sum = 0;
		for (long x : numbers)
			sum += x;
		return Long.toString(sum);
	}
	
	
	// Lists primes up to n (inclusive) and squares them
	private static long[] listPrimesSquared(int n) {
		boolean[] isPrime = Library.listPrimality(n);
		int count = 0;
		for (boolean b : isPrime) {
			if (b)
				count++;
		}
		long[] primes = new long[count];
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				primes[j] = (long)i * i;
				j++;
			}
		}
		return primes;
	}
	
}
