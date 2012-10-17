/* 
 * Solution to Project Euler problem 3
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p003 {
	
	public static void main(String[] args) {
		long n = 600851475143L;
		while (true) {
			long k = smallestFactor(n);
			if (k < n)
				n /= k;
			else
				break;
		}
		System.out.println(n);
	}
	
	
	private static long smallestFactor(long n) {
		for (long i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return i;
		}
		return n;  // Prime
	}
	
}
