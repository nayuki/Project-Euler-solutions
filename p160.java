/* 
 * Solution to Project Euler problem 160
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p160 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p160().run());
	}
	
	
	public String run() {
		return Long.toString(factorialSuffix(1000000000000L));
	}
	
	
	// The last 5 digits of n!, excluding trailing zeros.
	private static long factorialSuffix(long n) {
		long twos = countFactors(n, 2) - countFactors(n, 5);  // Always non-negative for every n
		// We can reduce 'twos' because there is a cycle: 2^5 = 2^2505 = 32 mod 100000
		if (twos >= 2505)
			twos = (twos - 5) % 2500 + 5;
		return factorialish(n) * Library.powMod(2, (int)twos, 100000) % 100000;
	}
	
	
	// Equal to n! but with all factors of 2 and 5 removed and then modulo 10^5.
	// The identity factorialIsh(n) = oddFactorialish(n) * evenFactorialish(n) (mod 10^5) is true by definition.
	private static long factorialish(long n) {
		return evenFactorialish(n) * oddFactorialish(n) % 100000;
	}
	
	
	// The product of {all even numbers from 1 to n}, but with all factors of 2 and 5 removed and then modulo 10^5.
	// For example, evenFactorialish(9) only considers the numbers {2, 4, 6, 8}. Divide each number by 2 to get {1, 2, 3, 4}. Thus evenFactorialish(9) = factorialish(4).
	private static long evenFactorialish(long n) {
		if (n == 0)
			return 1;
		else
			return factorialish(n / 2);
	}
	
	
	// The product of {all odd numbers from 1 to n}, but with all factors of 2 and 5 removed and then modulo 10^5.
	// By definition, oddFactorialish() never considers any number that has a factor of 2. The product of the numbers that not a multiple of 5 are accumulated by factorialCoprime().
	// Those that are a multiple of 5 are handled recursively by oddFactorialish(), noting that they are still odd after dividing by 5.
	private static long oddFactorialish(long n) {
		if (n == 0)
			return 1;
		else
			return oddFactorialish(n / 5) * factorialCoprime(n) % 100000;
	}
	
	
	// The product of {all numbers from 1 to n that are coprime with 10}, modulo 10^5.
	// The input argument can be taken modulo 10^5 because factorialoid(10^5) = 1, and each block of 10^5 numbers behaves the same.
	private static long factorialCoprime(long n) {
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
	
}
