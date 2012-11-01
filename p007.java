/* 
 * Solution to Project Euler problem 7
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p007 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p007().run());
	}
	
	
	/* 
	 * Sieving primes is more efficient than testing if each number is prime, but we need to give an upper limit ahead of time.
	 * We don't know where the 10001th prime is, so we cannot hard-code an upper limit.
	 * Start with some arbitrary, small upper limit. Sieve for primes up to that limit.
	 * If the 10001th prime is not found below that limit, then increase the upper limit and repeat.
	 * Since there are an infinite number of primes, this procedure is guaranteed to terminate - except for overflow and out-of-memory.
	 */
	public String run() {
		int limit = 1;
		while (true) {
			int answer = nthPrime(10001, limit);
			if (answer != -1)
				return Integer.toString(answer);
			else
				limit *= 4;
		}
	}
	
	
	private static int nthPrime(int n, int limit) {
		boolean[] isPrime = Library.listPrimality(limit);
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				j++;
				if (j == n)
					return i;
			}
		}
		return -1;
	}
	
}
