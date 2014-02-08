/* 
 * Solution to Project Euler problem 231
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p231 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p231().run());
	}
	
	
	private static final int N = 20000000;
	private static final int K = 15000000;
	
	public String run() {
		int[] smallestPrimeFactor = Library.listSmallestPrimeFactors(N);
		
		long sum = factorialPrimeFactorSum(N, smallestPrimeFactor);
		sum -= factorialPrimeFactorSum(K, smallestPrimeFactor);
		sum -= factorialPrimeFactorSum(N - K, smallestPrimeFactor);
		return Long.toString(sum);
	}
	
	
	private static long factorialPrimeFactorSum(int n, int[] smallestPrimeFactor) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			int j = i;
			while (j > 1) {
				int p = smallestPrimeFactor[j];
				sum += p;
				j /= p;
			}
		}
		return sum;
	}
	
}
