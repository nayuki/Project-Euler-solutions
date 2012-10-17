/* 
 * Shared code for solutions to Project Euler problems
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


final class Library {
	
	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
	
	
	public static boolean isPalindrome(int x) {
		return isPalindrome(Integer.toString(x));
	}
	
	
	public static boolean isPalindrome(String s) {
		return s.equals(reverse(s));
	}
	
	
	public static int sqrt(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		int y = 0;
		for (int i = 32768; i != 0; i >>>= 1) {
			y |= i;
			if (y > 46340 || y * y > x)
				y ^= i;
		}
		return y;
	}
	
	
	public static long sqrt(long x) {
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
	
	
	// Does not check for overflow
	public static int pow(int x, int y) {
		if (y < 0)
			throw new IllegalArgumentException("Negative exponent");
		int z = 1;
		for (int i = 0; i < y; i++)
			z *= x;
		return z;
	}
	
	
	public static BigInteger binomial(int n, int k) {
		return factorial(n).divide(factorial(n - k).multiply(factorial(k)));
	}
	
	
	public static BigInteger factorial(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Factorial of negative number");
		BigInteger prod = BigInteger.ONE;
		for (int i = 2; i <= n; i++)
			prod = prod.multiply(BigInteger.valueOf(i));
		return prod;
	}
	
	
	public static int gcd(int x, int y) {
		while (y != 0) {
			int z = x % y;
			x = y;
			y = z;
		}
		return x;
	}
	
	
	public static boolean isPrime(int x) {
		if (x < 0)
			throw new IllegalArgumentException();
		if (x == 0 || x == 1)
			return false;
		else if (x == 2)
			return true;
		else {
			if (x % 2 == 0)
				return false;
			for (int i = 3, end = sqrt(x); i <= end; i += 2) {
				if (x % i == 0)
					return false;
			}
			return true;
		}
	}
	
	
	public static boolean[] listPrimality(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		boolean[] prime = new boolean[n + 1];
		if (n >= 2)
			prime[2] = true;
		for (int i = 3; i <= n; i += 2)
			prime[i] = true;
		// Sieve of Eratosthenes
		for (int i = 3, end = sqrt(n); i <= end; i += 2) {
			if (prime[i] && (long)i * i <= n) {
				for (int j = i * i; j <= n; j += i << 1)
					prime[j] = false;
			}
		}
		return prime;
	}
	
	
	public static int totient(int x) {
		if (x <= 0)
			throw new IllegalArgumentException("Totient of non-positive integer");
		int p = 1;
		for (int i = 2, end = Library.sqrt(x); i <= end; i++) {  // Trial division
			if (x % i == 0) {  // Found a factor
				p *= i - 1;
				x /= i;
				while (x % i == 0) {
					p *= i;
					x /= i;
				}
				end = Library.sqrt(x);
			}
		}
		if (x != 1)
			p *= x - 1;
		return p;
	}
	
	
	public static int[] listTotients(int n) {
		int[] totients = new int[n + 1];
		for (int i = 0; i <= n; i++)
			totients[i] = i;
		
		for (int i = 2; i <= n; i++) {
			if (totients[i] == i) {  // i is prime
				for (int j = i; j <= n; j += i)
					totients[j] = totients[j] / i * (i - 1);
			}
		}
		return totients;
	}
	
	
	public static BigInteger multiply(BigInteger x, BigInteger y) {
		final int CUTOFF = 1536;
		if (x.bitLength() <= CUTOFF || y.bitLength() <= CUTOFF) {  // Base case
			return x.multiply(y);
			
		} else {  // Karatsuba fast multiplication
			int n = Math.max(x.bitLength(), y.bitLength());
			int half = (n + 32) / 64 * 32;
			BigInteger mask = BigInteger.ONE.shiftLeft(half).subtract(BigInteger.ONE);
			BigInteger xlow = x.and(mask);
			BigInteger ylow = y.and(mask);
			BigInteger xhigh = x.shiftRight(half);
			BigInteger yhigh = y.shiftRight(half);
			
			BigInteger a = multiply(xhigh, yhigh);
			BigInteger b = multiply(xlow.add(xhigh), ylow.add(yhigh));
			BigInteger c = multiply(xlow, ylow);
			BigInteger d = b.subtract(a).subtract(c);
			return a.shiftLeft(half).add(d).shiftLeft(half).add(c);
		}
	}
	
	
	public static boolean nextPermutation(int[] a) {
		int i, n = a.length;
		for (i = n - 2; ; i--) {
			if (i < 0)
				return false;
			if (a[i] < a[i + 1])
				break;
		}
		for (int j = 1; i + j < n - j; j++) {
			int tp = a[i + j];
			a[i + j] = a[n - j];
			a[n - j] = tp;
		}
		int j;
		for (j = i + 1; a[j] <= a[i]; j++);
		int tp = a[i];
		a[i] = a[j];
		a[j] = tp;
		return true;
	}
	
}
