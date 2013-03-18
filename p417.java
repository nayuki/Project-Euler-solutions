/* 
 * Solution to Project Euler problem 417
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p417 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p417().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	private int[] smallestPrimeFactor;  // Requires 400 MB
	private int[] totients;  // Also requires 400 MB
	
	
	public String run() {
		smallestPrimeFactor = new int[LIMIT + 1];
		for (int i = 2; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == 0) {
				smallestPrimeFactor[i] = i;
				if ((long)i * i <= LIMIT) {
					for (int j = i * i; j <= LIMIT; j += i) {
						if (smallestPrimeFactor[j] == 0)
							smallestPrimeFactor[j] = i;
					}
				}
			}
		}
		
		totients = Library.listTotients(LIMIT);
		
		long sum = 0;
		for (int i = 3; i <= LIMIT; i++) {
			sum += getCycleLength(i);
		}
		return Long.toString(sum);
	}
	
	
	private int getCycleLength(int n) {
		while (n % 2 == 0)
			n /= 2;
		while (n % 5 == 0)
			n /= 5;
		if (n == 1)
			return 0;
		
		int result = totients[n];
		int remainingFactors = result;
		while (remainingFactors > 1) {
			int p = smallestPrimeFactor[remainingFactors];
			if (Library.powMod(10, result / p, n) == 1)
				result /= p;
			remainingFactors /= p;
		}
		return result;
	}
	
}
