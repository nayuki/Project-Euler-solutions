/* 
 * Solution to Project Euler problem 187
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p187 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p187().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8) - 1;
	
	
	/* 
	 * LIMIT is the highest number that we will test for being semiprime.
	 * Make a list of primes: 2, 3, 5, 7, ... . Stop beyond LIMIT/2, because one of the prime factors in the semiprime is at least 2.
	 * For each prime p in the list, look at the set of numbers q such that q >= p and pq <= LIMIT.
	 * Actually, we can stop when p^2 > LIMIT, as we'll see later.
	 * In this algorithm, we find the index 'end' such that primes[i] * primes[end] > LIMIT.
	 * So for that p, we have (end - i) different choices for q. Since q >= p, all these pairs are unique.
	 * Furthermore, by the fundamental theorem of arithmetic, all the products pq are unique.
	 */
	public String run() {
		int count = 0;
		int[] primes = Library.listPrimes(LIMIT / 2);
		for (int i = 0, sqrt = Library.sqrt(LIMIT); i < primes.length && primes[i] <= sqrt; i++) {
			int end = Arrays.binarySearch(primes, LIMIT / primes[i]);
			if (end >= 0)
				end++;
			else
				end = -end - 1;
			count += end - i;
		}
		return Integer.toString(count);
	}
	
}
