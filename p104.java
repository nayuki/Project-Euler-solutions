/* 
 * Solution to Project Euler problem 104
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.Arrays;


public final class p104 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p104().run());
	}
	
	
	public String run() {
		int i = 0;
		int a = 0;
		int b = 1;
		while (!isFound(i, a)) {
			int c = (a + b) % 1000000000;
			a = b;
			b = c;
			i++;
		}
		return Integer.toString(i);
	}
	
	
	private static boolean isFound(int n, int fibMod) {
		if (!isPandigital(Integer.toString(fibMod)))
			return false;
		BigInteger fib = fibonacci(n);
		if (fib.mod(BigInteger.valueOf(1000000000)).intValue() != fibMod)
			throw new AssertionError();
		return isPandigital(leading9Digits(fib));
	}
	
	
	private static String leading9Digits(BigInteger x) {
		// We know that x.bitLength() = floor(log2(x)) + 1.
		// Now compute an approximate base-10 logarithm, because log10(2) = 0.301... .
		// The computed quantity is no larger than floor(log10(x)).
		int log10 = (x.bitLength() - 1) * 3 / 10;
		
		// Chop off quite a number of rightmost base-10 digits.
		// It is guaranteed that there remains at least 9 digits.
		x = x.divide(BigInteger.TEN.pow(Math.max(log10 + 1 - 9, 0)));
		
		// Deal with the remaining smaller number.
		return x.toString().substring(0, 9);
	}
	
	
	private static boolean isPandigital(String s) {
		if (s.length() != 9)
			return false;
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp).equals("123456789");
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
