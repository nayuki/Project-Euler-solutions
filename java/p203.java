/* 
 * Solution to Project Euler problem 203
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public final class p203 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p203().run());
	}
	
	
	public String run() {
		// Collect unique numbers in Pascal's triangle
		Set<Long> numbers = new HashSet<>();
		long max = 0;
		for (int n = 0; n <= 50; n++) {
			for (int k = 0; k <= n; k++) {
				BigInteger x = Library.binomial(n, k);
				if (x.bitLength() >= 64)
					throw new AssertionError("Number too large to handle");
				numbers.add(x.longValue());
				max = Math.max(x.longValue(), max);
			}
		}
		
		// Prepare list of squared primes
		int[] primes = Library.listPrimes((int)Library.sqrt(max));
		primesSquared = new long[primes.length];
		for (int i = 0; i < primes.length; i++)
			primesSquared[i] = (long)primes[i] * primes[i];
		
		// Sum up the squarefree numbers
		long sum = 0;
		for (long n : numbers) {
			if (isSquarefree(n))
				sum += n;
		}
		return Long.toString(sum);
	}
	
	
	private long[] primesSquared;
	
	private boolean isSquarefree(long n) {
		for (long p2 : primesSquared) {
			if (p2 > n)
				break;
			if (n % p2 == 0)
				return false;
		}
		return true;
	}
	
}
