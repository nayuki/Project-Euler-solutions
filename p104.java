/* 
 * Solution to Project Euler problem 104
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public class p104 {
	
	public static void main(String[] args) {
		int i = 0;
		int a = 0;
		int b = 1;
		while (!isFound(i, a)) {
			int c = (a + b) % 1000000000;
			a = b;
			b = c;
			i++;
		}
		System.out.println(i);
	}
	
	
	private static boolean isFound(int n, int fibMod) {
		if (!is1To9Pandigital(Integer.toString(fibMod)))
			return false;
		BigInteger fib = fibonacci(n);
		if (fib.mod(BigInteger.valueOf(1000000000)).intValue() != fibMod)
			throw new AssertionError();
		return is1To9Pandigital(fib.toString().substring(0, 9));
	}
	
	
	private static boolean is1To9Pandigital(String s) {
		if (s.length() != 9)
			return false;
		for (char d = '1'; d <= '9'; d++) {
			if (s.indexOf(d) == -1)
				return false;
		}
		return true;
	}
	
	
	private static BigInteger fibonacci(int n) {
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		int m = 0;
		for (int i = 31; i >= 0; i--) {
			// Loop invariant: a = f(m), b = f(m+1)
			
			// Double it
			BigInteger d = Library.multiply(a, b.shiftLeft(1).subtract(a));
			BigInteger e = Library.multiply(a, a).add(Library.multiply(b, b));
			a = d;
			b = e;
			m *= 2;
			
			// Advance by one conditionally
			if (((1 << i) & n) != 0) {
				BigInteger c = a.add(b);
				a = b;
				b = c;
				m++;
			}
		}
		return a;
	}
	
}
