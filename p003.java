/* 
 * Solution to Project Euler problem 3
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p003 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p003().run());
	}
	
	
	/* 
	 * Algorithm: Divide out all the smallest prime factors except the last one.
	 * For example, 1596 = 2 * 2 * 3 * 7 * 19. The algorithm ensures that the smallest factors will be found first.
	 * After dividing out the smallest factors, the last factor to be found will be equal to the quotient, so it must be the largest prime factor.
	 */
	public String run() {
		long n = 600851475143L;
		while (true) {
			long p = smallestFactor(n);
			if (p < n)
				n /= p;
			else
				return Long.toString(n);
		}
	}
	
	
	private static long smallestFactor(long n) {
		for (long i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return i;
		}
		return n;  // Prime
	}
	
}
