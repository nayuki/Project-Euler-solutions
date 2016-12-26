/* 
 * Solution to Project Euler problem 518
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p518 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p518().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	public String run() {
		boolean[] isPrime = Library.listPrimality(LIMIT - 1);
		int[] primes = Library.listPrimes(LIMIT - 1);
		
		long sum = 0;
		for (int i = 0; i < primes.length; i++) {
			int a = primes[i];
			int x = a + 1;
			long end = (long)x * isPrime.length;
			
			for (int j = i + 1; j < primes.length; j++) {
				int b = primes[j];
				int y = b + 1;
				long temp = (long)y * y;
				if (temp >= end)
					break;
				
				if (temp % x == 0) {
					int z = (int)(temp / x);
					int c = z - 1;
					if (isPrime[c]) {
						sum += a + b + c;
					}
				}
			}
		}
		return Long.toString(sum);
	}
	
}
