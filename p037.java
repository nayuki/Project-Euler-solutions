/* 
 * Solution to Project Euler problem 37
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p037 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p037().run());
	}
	
	
	public String run() {
		long sum = 0;
		for (int count = 0, n = 10; count < 11; n++) {
			if (isTruncatablePrime(n)) {
				sum += n;
				count++;
			}
		}
		return Long.toString(sum);
	}
	
	
	private static boolean isTruncatablePrime(int n) {
		// Test if left-truncatable
		for (long i = 10; i <= n; i *= 10) {
			if (!Library.isPrime(n % (int)i))
				return false;
		}
		
		// Test if right-truncatable
		for (; n != 0; n /= 10) {
			if (!Library.isPrime(n))
				return false;
		}
		
		return true;
	}
	
}
