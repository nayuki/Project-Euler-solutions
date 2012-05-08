/* 
 * Shared code for solutions to Project Euler problems
 * By Nayuki Minase
 */

import java.math.BigInteger;


final class Library {
	
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
	
	
	public static int[] listTotients(int n, boolean[] isPrime) {
		int[] totients = new int[n + 1];
		totients[1] = 1;
		for (int i = 2; i <= n; i++) {
			if (isPrime[i])
				totients[i] = i - 1;
			else {
				for (int j = 2; ; j++) {  // This loop will terminate before j > sqrt(n)
					if (isPrime[j] && i % j == 0) {  // Only need to consider prime factors
						int tot = j - 1;
						int temp = i / j;
						while (temp % j == 0) {
							tot *= j;
							temp /= j;
						}
						// temp is now coprime to j
						tot *= totients[temp];
						totients[i] = tot;
						break;
					}
				}
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
