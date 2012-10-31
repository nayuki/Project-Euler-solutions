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
	
	
	public String run() {
		int n = 64000000;
		long[] sigma2 = listSigma2(n);
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			if (isPerfectSquare(sigma2[i]))
				sum += i;
		}
		return Long.toString(sum);
	}
	
	
	private static long[] listSigma2(int n) {
		// Richer version of the sieve of Eratosthenes
		int[] smallestPrimeFactor = new int[n + 1];
		for (int i = 2; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == 0) {
				smallestPrimeFactor[i] = i;
				for (int j = i * 2; j < smallestPrimeFactor.length; j += i) {
					if (smallestPrimeFactor[j] == 0)
						smallestPrimeFactor[j] = i;
				}
			}
		}
		
		long[] sigma2 = new long[n + 1];
		sigma2[1] = 1;
		for (int i = 2; i < sigma2.length; i++) {
			int p = smallestPrimeFactor[i];
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
		for (long i = 2147483648L; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y * y == x;
	}
	
}
