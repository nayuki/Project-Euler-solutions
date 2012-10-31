/* 
 * Solution to Project Euler problem 187
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p187 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p187().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	private int[] smallestPrimeFactor = listSmallestPrimeFactor(LIMIT);
	
	
	public String run() {
		int count = 0;
		for (int i = 2; i < LIMIT; i++) {
			if (isPrime(i / smallestPrimeFactor[i]))
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private static int[] listSmallestPrimeFactor(int n) {
		// Richer version of the sieve of Eratosthenes
		int[] smallestPrimeFactor = new int[n + 1];
		for (int i = 2; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == 0) {
				smallestPrimeFactor[i] = i;
				if ((long)i * i <= n) {
					for (int j = i * i; j <= n; j += i) {
						if (smallestPrimeFactor[j] == 0)
							smallestPrimeFactor[j] = i;
					}
				}
			}
		}
		return smallestPrimeFactor;
	}
	
	
	private boolean isPrime(int n) {
		return smallestPrimeFactor[n] == n;
	}
	
}
