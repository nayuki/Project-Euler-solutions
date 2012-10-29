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
	
	
	public String run() {
		long n = 600851475143L;
		while (true) {
			long k = smallestFactor(n);
			if (k < n)
				n /= k;
			else
				break;
		}
		return Long.toString(n);
	}
	
	
	private static long smallestFactor(long n) {
		for (long i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return i;
		}
		return n;  // Prime
	}
	
}
