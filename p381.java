/* 
 * Solution to Project Euler problem 381
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p381 {
	
	public static void main(String[] args) {
		boolean[] isPrime = Library.listPrimality(Library.pow(10, 8));
		long sum = 0;
		for (int i = 5; i < isPrime.length; i++) {
			if (isPrime[i])
				sum += s(i);
		}
		System.out.println(sum);
	}
	
	
	/* 
	 * Note about the mathematical simplification:
	 * (p-5)! + (p-4)! + (p-3)! + (p-2)! + (p-1)!
	 * = (p-5)! * (1 + (p-4) + (p-4)(p-3) + (p-4)(p-3)(p-2) + (p-4)(p-3)(p-2)(p-1))
	 * = (p-5)! * (1 + (-4) + (-4)(-3) + (-4)(-3)(-2) + (-4)(-3)(-2)(-1))
	 * = (p-5)! * (1 + -4 + 12 + -24 + 24)
	 * = (p-5)! * 9
	 * = (p-1)! / ((p-1)(p-2)(p-3)(p-4)) * 9
	 * = (p-1)! / ((-1)(-2)(-3)(-4)) * 9
	 * = (p-1)! / 24 * 9
	 * = (p-1)! * (3 * 3) / (3 * 8)
	 * = (p-1)! * 3 / 8
	 * = -1 * 3 / 8  (by Wilson's theorem)
	 * = -3/8 mod p.
	 * Every part of the equation is modulo a prime p > 4.
	 */
	private static int s(int p) {
		return (int)((long)(p - 3) * reciprocalMod(8 % p, p) % p);
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
