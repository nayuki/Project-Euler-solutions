/* 
 * Solution to Project Euler problem 160
 * By Nayuki Minase
 */


public class p160 {
	
	public static void main(String[] args) {
		System.out.println(factorialLast(1000000000000L));
	}
	
	
	// The last 5 digits of n!.
	private static long factorialLast(long n) {
		long twos = countFactors(n, 2) - countFactors(n, 5);  // Always non-negative for every n
		return factorialish(n) * powMod(2, twos, 100000) % 100000;
	}
	
	
	// Equal to n! but with all factors of 2 and 5 removed and then modulo 10^5.
	// The identity f(n) = of(n) * ef(n) (mod 10^5) is true by definition.
	private static long factorialish(long n) {
		return evenFactorialish(n) * oddFactorialish(n) % 100000;
	}
	
	
	// The product of {all even numbers up to and including n}, but with all factors of 2 and 5 removed and then modulo 10^5.
	// For example, ef(9) only considers the numbers {2, 4, 6, 8}. Divide each number by 2 to get {1, 2, 3, 4}. Thus ef(9) = f(4).
	private static long evenFactorialish(long n) {
		if (n == 0)
			return 1;
		else
			return factorialish(n / 2);
	}
	
	
	// The product of {all odd numbers up to and including n}, but with all factors of 2 and 5 removed and then modulo 10^5.
	// By definition, of() never considers any number that has a factor of 2. The product of the numbers that not a multiple of 5 are accumulated by factorialoid(). Those that are a multiple of 5 are handled recursively by of(), noting that they are still odd after dividing by 5.
	private static long oddFactorialish(long n) {
		if (n == 0)
			return 1;
		else
			return oddFactorialish(n / 5) * factorialoid(n) % 100000;
	}
	
	
	// The product of {all numbers coprime with 10 up to and including n}, modulo 10^5.
	// The input argument can be taken modulo 10^5 because factorialoid(10^5) = 1 and the processing behaves the same for each block of 10^5 consecutive numbers.
	private static long factorialoid(long n) {
		n %= 100000;
		long product = 1;
		for (int i = 1; i <= n; i++) {
			if (i % 2 != 0 && i % 5 != 0)
				product = i * product % 100000;
		}
		return product;
	}
	
	
	// Counts the number of factors of n in the set of integers {1, 2, ..., end}.
	// For example, countFactors(25, 5) = 6 because {5, 10, 15, 20} each has one factor of 5, and 25 has two factors of 5.
	private static long countFactors(long end, long n) {
		if (end == 0)
			return 0;
		else
			return end / n + countFactors(end / n, n);
	}
	
	
	private static long powMod(long x, long y, long m) {
		if (y < 0)
			throw new IllegalArgumentException();
		long z = 1;
		for (; y != 0; y >>>= 1, x = x * x % m) {
			if ((y & 1) != 0)
				z = z * x % m;
		}
		return z;
	}
	
}
