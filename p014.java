/* 
 * Solution to Project Euler problem 14
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p014 {
	
	public static void main(String[] args) {
		int maxArg = -1;
		int maxChain = 0;
		for (int i = 1; i < 1000000; i++) {
			int chainLen = collatzChainLength(BigInteger.valueOf(i));
			if (chainLen > maxChain) {
				maxArg = i;
				maxChain = chainLen;
			}
		}
		System.out.println(maxArg + " " + maxChain);
	}
	
	
	private static int collatzChainLength(BigInteger n) {
		if (n.signum() < 0)
			throw new IllegalArgumentException();
		
		int count = 1;
		while (true) {
			if (n.equals(BigInteger.ONE))
				return count;
			else {
				if (!n.testBit(0))  // Test if it's even
					n = n.shiftRight(1);
				else
					n = n.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
				count++;
			}
		}
	}
	
}
