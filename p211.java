/* 
 * Solution to Project Euler problem 211
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p211 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p211().run());
	}
	
	
	private static final int LIMIT = 64000000;
	
	
	// Requires at least 640 MB of memory
	public String run() {
		long[] sigma2 = listSigma2(LIMIT - 1);
		long sum = 0;
		for (int i = 1; i < LIMIT; i++) {
			if (isPerfectSquare(sigma2[i]))
				sum += i;
		}
		return Long.toString(sum);
	}
	
	
	private static long[] listSigma2(int n) {
		// If i has a prime factor p <= sqrt, then quasiPrimeFactor[i] = p.
		// Otherwise i > sqrt must be prime, and quasiPrimeFactor[i] = 0 because i may overflow an int16.
		int sqrt = Library.sqrt(n);
		short[] quasiPrimeFactor = new short[n + 1];
		
		// Richer version of the sieve of Eratosthenes
		for (int i = 2; i <= sqrt; i++) {
			if (quasiPrimeFactor[i] == 0) {
				quasiPrimeFactor[i] = (short)i;
				if ((long)i * i <= n) {
					for (int j = i * i; j <= n; j += i) {
						if (quasiPrimeFactor[j] == 0)
							quasiPrimeFactor[j] = (short)i;
					}
				}
			}
		}
		
		long[] sigma2 = new long[n + 1];
		sigma2[1] = 1;
		for (int i = 2; i < sigma2.length; i++) {
			int p = quasiPrimeFactor[i];
			if (p == 0)
				p = i;
			long sum = 1;
			int j = i;
			long p2 = (long)p * p;
			for (long k = p2; j % p == 0; j /= p, k *= p2)
				sum += k;
			sigma2[i] = sum * sigma2[j];
		}
		return sigma2;
	}
	
	
	private static boolean isPerfectSquare(long x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		
		long y = 0;
		for (long i = 1L << 31; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y * y == x;
	}
	
}
