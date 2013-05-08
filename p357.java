/* 
 * Solution to Project Euler problem 357
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p357 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p357().run());
	}
	
	
	private static final int LIMIT = 100000000;
	
	
	// Because the maximum value of (i + n/i) is (n + 1)
	private boolean[] isPrime = Library.listPrimality(LIMIT + 1);
	
	
	public String run() {
		long sum = 0;
		for (int n = 0; n <= LIMIT; n++) {
			if (isPrimeGenerating(n))
				sum += n;
		}
		return Long.toString(sum);
	}
	
	
	private boolean isPrimeGenerating(int n) {
		for (int i = 1, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				if (!isPrime[i + n / i])
					return false;
			}
		}
		return true;
	}
	
}
