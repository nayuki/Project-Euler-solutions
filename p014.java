/* 
 * Solution to Project Euler problem 14
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p014 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p014().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 6);
	private static final BigInteger CACHE_SIZE = BigInteger.valueOf(LIMIT);
	
	
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
	
	
	// Memoization
	private int[] collatzChainLength = new int[CACHE_SIZE.intValue()];
	
	private int collatzChainLength(BigInteger n) {
		if (n.signum() < 0)
			throw new IllegalArgumentException();
		
		int index;
		if (n.compareTo(CACHE_SIZE) < 0)
			index = n.intValue();
		else
			index = -1;
		
		if (index != -1 && collatzChainLength[index] != 0)
			return collatzChainLength[index];
		
		int result;
		if (n.equals(BigInteger.ONE))
			result = 1;
		else if (!n.testBit(0))  // If n is even
			result = collatzChainLength(n.shiftRight(1)) + 1;
		else  // Else n is odd
			result = collatzChainLength(n.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE)) + 1;
		
		if (index != -1)
			collatzChainLength[index] = result;
		
		return result;
	}
	
}
