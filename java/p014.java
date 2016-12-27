/* 
 * Solution to Project Euler problem 14
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p014 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p014().run());
	}
	
	
	/* 
	 * We compute the Collatz chain length for every integer in the range according to the iteration rule.
	 * Also, we cache the Collatz value for small integer arguments to speed up the computation.
	 */
	private static final int LIMIT = Library.pow(10, 6);
	
	public String run() {
		int maxArg = -1;
		int maxChain = 0;
		for (int i = 1; i < LIMIT; i++) {
			int chainLen = collatzChainLength(BigInteger.valueOf(i));
			if (chainLen > maxChain) {
				maxArg = i;
				maxChain = chainLen;
			}
		}
		return Integer.toString(maxArg);
	}
	
	
	// Can be set to any non-negative number, but there are diminishing returns as you go larger
	private static final BigInteger CACHE_SIZE = BigInteger.valueOf(LIMIT);
	
	// Memoization
	private int[] collatzChainLength = new int[CACHE_SIZE.intValue()];
	
	// Returns the Collatz chain length of the given integer with automatic caching.
	private int collatzChainLength(BigInteger n) {
		if (n.signum() < 0)
			throw new IllegalArgumentException();
		
		if (n.compareTo(CACHE_SIZE) >= 0)  // Caching not available
			return collatzChainLengthDirect(n);
		
		int index = n.intValue();  // Index in the cache
		if (collatzChainLength[index] == 0)
			collatzChainLength[index] = collatzChainLengthDirect(n);
		return collatzChainLength[index];
	}
	
	
	// Returns the Collatz chain length of the given integer, with the
	// first step uncached but the remaining steps using automatic caching.
	private int collatzChainLengthDirect(BigInteger n) {
		if (n.equals(BigInteger.ONE))  // Base case
			return 1;
		else if (!n.testBit(0))  // If n is even
			return collatzChainLength(n.shiftRight(1)) + 1;
		else  // Else n is odd
			return collatzChainLength(n.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE)) + 1;
	}
	
}
