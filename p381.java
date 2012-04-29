/* 
 * Solution to Project Euler problem 381
 * By Nayuki Minase
 */


public class p381 {
	
	public static void main(String[] args) {
		boolean[] isPrime = Library.listPrimality(100000000);
		long sum = 4;  // Special-cased for 5
		for (int i = 6; i < isPrime.length; i++) {
			if (isPrime[i])
				sum += s(i);
		}
		System.out.println(sum);
	}
	
	
	private static int s(int p) {
		// By Wilson's theorem, because p is prime, (p-1)! = -1 mod p.
		// This starting point covers the k=1 case.
		long temp = p - 1;
		int sum = (int)temp;
		for (int k = 2; k <= 5; k++) {
			temp = temp * reciprocalMod(p - (k - 1), p) % p;  // Divide by k-1 so that temp becomes congruent to (p-k)!
			sum += temp;
		}
		return sum % p;
	}
	
	
	private static int reciprocalMod(int x, int m) {
		if (m < 0 || x < 0 || x >= m)
			throw new IllegalArgumentException();
		
		// Based on a simplification of the extended Euclidean algorithm
		int y = x;
		x = m;
		int a = 0;
		int b = 1;
		while (y != 0) {
			int z = x % y;
			int c = a - x / y * b;
			x = y;
			y = z;
			a = b;
			b = c;
		}
		if (x == 1)
			return (a + m) % m;
		else
			throw new IllegalArgumentException("Reciprocal does not exist");
	}
	
}
