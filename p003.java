/* 
 * Solution to Project Euler problem 3
 * By Nayuki Minase
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
		for (long i = 2, end = sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return i;
		}
		return n;  // Prime
	}
	
	
	private static long sqrt(long x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		long y = 0;
		for (long i = 1L << 31; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y;
	}
	
}
