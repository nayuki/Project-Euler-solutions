/* 
 * Solution to Project Euler problem 549
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p549 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p549().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	private int[] smallestPrimeFactor;
	
	
	public String run() {
		smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT);
		long sum = 0;
		for (int i = 2; i <= LIMIT; i++)
			sum += smallestDivisibleFactorial(i);
		return Long.toString(sum);
	}
	
	
	// Note: For all n >= 2, we have 2 <= smallestDivisibleFactorial(n) <= n.
	private int smallestDivisibleFactorial(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		if (n == 1)
			return 0;
		
		int result = 0;
		while (n > 1) {
			int prime = smallestPrimeFactor[n];
			int power = 0;
			for (; n % prime == 0; n /= prime, power++);
			
			int m = prime;
			for (int count = 0; ; m += prime) {
				for (int temp = m; temp % prime == 0; temp /= prime)
					count++;
				if (count >= power)
					break;
			}
			result = Math.max(m, result);
		}
		return result;
	}
	
}
