/* 
 * Shared code for solutions to Project Euler problems
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


final class Library {
	
	// Returns the reverse of the given string.
	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
	
	
	// Tests whether the given string is a palindrome.
	public static boolean isPalindrome(String s) {
		return s.equals(reverse(s));
	}
	
	
	// Tests whether the given integer is a palindrome in decimal (base 10).
	public static boolean isPalindrome(int x) {
		return isPalindrome(Integer.toString(x));
	}
	
	
	// Returns floor(sqrt(x)), for x >= 0.
	public static int sqrt(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		int y = 0;
		for (int i = 1 << 15; i != 0; i >>>= 1) {
			y |= i;
			if (y > 46340 || y * y > x)
				y ^= i;
		}
		return y;
	}
	
	
	// Returns floor(sqrt(x)), for x >= 0.
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
	
	
	// Returns floor(sqrt(x)), for x >= 0.
	public static BigInteger sqrt(BigInteger x) {
		if (x.signum() == -1)
			throw new IllegalArgumentException("Square root of negative number");
		BigInteger y = BigInteger.ZERO;
		for (int i = (x.bitLength() - 1) / 2; i >= 0; i--) {
			y = y.setBit(i);
			if (y.multiply(y).compareTo(x) > 0)
				y = y.clearBit(i);
		}
		return y;
	}
	
	
	// Tests whether x is a perfect square, for any value x.
	public static boolean isSquare(int x) {
		if (x < 0)
			return false;
		int y = Library.sqrt(x);
		return y * y == x;
	}
	
	
	// Returns x to the power of y, throwing an exception if the result overflows an int.
	public static int pow(int x, int y) {
		if (x < 0)
			throw new IllegalArgumentException("Negative base not supported");
		if (y < 0)
			throw new IllegalArgumentException("Negative exponent");
		int z = 1;
		for (int i = 0; i < y; i++) {
			if (Integer.MAX_VALUE / z < x)
				throw new ArithmeticException("Overflow");
			z *= x;
		}
		return z;
	}
	
	
	// Returns x^y mod m.
	public static int powMod(int x, int y, int m) {
		if (x < 0)
			throw new IllegalArgumentException("Negative base not supported");
		if (y < 0)
			throw new IllegalArgumentException("Modular reciprocal not supported");
		if (m <= 0)
			throw new IllegalArgumentException("Modulus must be positive");
		if (m == 1)
			return 0;
		
		// Exponentiation by squaring
		int z = 1;
		for (; y != 0; y >>>= 1) {
			if ((y & 1) != 0)
				z = (int)((long)z * x % m);
			x = (int)((long)x * x % m);
		}
		return z;
	}
	
	
	// Returns x^-1 mod m, where the result is in the range [0, m).
	// Note that (x * x^-1) mod m = (x^-1 * x) mod m = 1.
	public static int reciprocalMod(int x, int m) {
		if (!(0 <= x && x < m))
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
			return a >= 0 ? a : a + m;
		else
			throw new IllegalArgumentException("Reciprocal does not exist");
	}
	
	
	// Returns n!.
	public static BigInteger factorial(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Factorial of negative number");
		BigInteger prod = BigInteger.ONE;
		for (int i = 2; i <= n; i++)
			prod = prod.multiply(BigInteger.valueOf(i));
		return prod;
	}
	
	
	// Returns n choose k.
	public static BigInteger binomial(int n, int k) {
		if (k < 0 || k > n)
			throw new IllegalArgumentException();
		BigInteger product = BigInteger.ONE;
		for (int i = 0; i < k; i++)
			product = product.multiply(BigInteger.valueOf(n - i));
		return product.divide(factorial(k));
	}
	
	
	// Returns the largest non-negative integer that divides both x and y.
	public static int gcd(int x, int y) {
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("Negative number");
		while (y != 0) {
			int z = x % y;
			x = y;
			y = z;
		}
		return x;
	}
	
	
	// Tests whether the given non-negative integer is prime.
	public static boolean isPrime(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Negative number");
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
	
	
	// Returns a Boolean array 'isPrime' where isPrime[i] indicates whether i is prime, for 0 <= i <= n.
	// For a large batch of queries, this is faster than calling isPrime() for each integer.
	// For example: listPrimality(100) = {false, false, true, true, false, true, false, true,
	// false, false, false, true, false, true, false, false, false, true, ...} (array length 101).
	public static boolean[] listPrimality(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Negative array size");
		boolean[] result = new boolean[n + 1];
		if (n >= 2)
			result[2] = true;
		for (int i = 3; i <= n; i += 2)
			result[i] = true;
		// Sieve of Eratosthenes
		for (int i = 3, end = sqrt(n); i <= end; i += 2) {
			if (result[i]) {
				// Note: i * i does not overflow
				for (int j = i * i, inc = i * 2; j <= n; j += inc)
					result[j] = false;
			}
		}
		return result;
	}
	
	
	// Returns all the prime numbers less than or equal to n, in ascending order.
	// For example: listPrimes(97) = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, ..., 83, 89, 97}.
	public static int[] listPrimes(int n) {
		boolean[] isPrime = listPrimality(n);
		int count = 0;
		for (boolean b : isPrime) {
			if (b)
				count++;
		}
		
		int[] result = new int[count];
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				result[j] = i;
				j++;
			}
		}
		return result;
	}
	
	
	// Returns an array spf where spf[k] is the smallest prime factor of k, valid for 2 <= k <= n.
	// For example: listSmallestPrimeFactors(10) = {0, 0, 2, 3, 2, 5, 2, 7, 2, 3, 2}.
	public static int[] listSmallestPrimeFactors(int n) {
		int[] result = new int[n + 1];
		int limit = sqrt(n);
		for (int i = 2; i < result.length; i++) {
			if (result[i] == 0) {
				result[i] = i;
				if (i <= limit) {
					// Note: i * i does not overflow
					for (int j = i * i; j <= n; j += i) {
						if (result[j] == 0)
							result[j] = i;
					}
				}
			}
		}
		return result;
	}
	
	
	// Returns the number of integers in the range [1, n] that are coprime with n.
	// For example, totient(12) = 4 because these integers are coprime with 12: 1, 5, 7, 11.
	public static int totient(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("Totient of non-positive integer");
		int p = 1;
		for (int i = 2, end = Library.sqrt(n); i <= end; i++) {  // Trial division
			if (n % i == 0) {  // Found a factor
				p *= i - 1;
				n /= i;
				while (n % i == 0) {
					p *= i;
					n /= i;
				}
				end = Library.sqrt(n);
			}
		}
		if (n != 1)
			p *= n - 1;
		return p;
	}
	
	
	// Returns an array 'totients' where totients[i] == totient(i), for 0 <= i <= n.
	// For a large batch of queries, this is faster than calling totient() for each integer.
	public static int[] listTotients(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Negative array size");
		int[] result = new int[n + 1];
		for (int i = 0; i <= n; i++)
			result[i] = i;
		
		for (int i = 2; i <= n; i++) {
			if (result[i] == i) {  // i is prime
				for (int j = i; j <= n; j += i)
					result[j] -= result[j] / i;
			}
		}
		return result;
	}
	
	
	// Attempts to advance the given sequence to the next permutation in lexicographical order.
	// Returns true if the sequence was successfully permuted, or returns false if the sequence
	// was already at the last possible permutation (a non-ascending sequence).
	// Explanation: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	// For example:
	// - nextPermutation({0,0,1}) changes the argument array to {0,1,0} and returns true.
	// - nextPermutation({1,0,0}) leaves the argument array unchanged and returns false.
	public static boolean nextPermutation(int[] arr) {
		int i = arr.length - 1;
		for (; i > 0 && arr[i - 1] >= arr[i]; i--);
		if (i <= 0)
			return false;
		{
			int j = arr.length - 1;
			for (; arr[j] <= arr[i - 1]; j--);
			int temp = arr[i - 1];
			arr[i - 1] = arr[j];
			arr[j] = temp;
		}
		for (int j = arr.length - 1; i < j; i++, j--) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		return true;
	}
	
}



// Immutable unlimited precision fraction
final class Fraction implements Comparable<Fraction> {
	
	public static final Fraction ZERO = new Fraction(BigInteger.ZERO);
	
	
	public final BigInteger numerator;    // Always coprime with denominator
	public final BigInteger denominator;  // Always positive
	
	
	public Fraction(BigInteger numer) {
		numerator = numer;
		denominator = BigInteger.ONE;
	}
	
	
	public Fraction(BigInteger numer, BigInteger denom) {
		if (denom.signum() == 0)
			throw new ArithmeticException("Division by zero");
		
		// Reduce to canonical form
		if (denom.signum() == -1) {
			numer = numer.negate();
			denom = denom.negate();
		}
		BigInteger gcd = numer.gcd(denom);
		if (!gcd.equals(BigInteger.ONE)) {
			numer = numer.divide(gcd);
			denom = denom.divide(gcd);
		}
		
		numerator = numer;
		denominator = denom;
	}
	
	
	public Fraction add(Fraction other) {
		return new Fraction(
			numerator.multiply(other.denominator).add(other.numerator.multiply(denominator)),
			denominator.multiply(other.denominator));
	}
	
	
	public Fraction subtract(Fraction other) {
		return new Fraction(
			numerator.multiply(other.denominator).subtract(other.numerator.multiply(denominator)),
			denominator.multiply(other.denominator));
	}
	
	
	public Fraction multiply(Fraction other) {
		return new Fraction(
			numerator.multiply(other.numerator),
			denominator.multiply(other.denominator));
	}
	
	
	public Fraction divide(Fraction other) {
		return new Fraction(
			numerator.multiply(other.denominator),
			denominator.multiply(other.numerator));
	}
	
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Fraction))
			return false;
		Fraction other = (Fraction)obj;
		return numerator.equals(other.numerator)
			&& denominator.equals(other.denominator);
	}
	
	
	public int compareTo(Fraction other) {
		return numerator.multiply(other.denominator)
			.compareTo(other.numerator.multiply(denominator));
	}
	
	
	public int hashCode() {
		return numerator.hashCode() + denominator.hashCode();
	}
	
	
	public String toString() {
		return numerator + "/" + denominator;
	}
	
}
