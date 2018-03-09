/* 
 * Solution to Project Euler problem 106
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p106 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p106().run());
	}
	
	
	private static final int SET_SIZE = 12;
	
	public String run() {
		BigInteger ans = BigInteger.ZERO;
		for (int i = 2; i * 2 <= SET_SIZE; i++) {
			BigInteger x = Library.binomial(SET_SIZE, i * 2);
			BigInteger y = Library.binomial(i * 2, i).shiftRight(1);
			BigInteger z = Library.binomial(i * 2, i).divide(BigInteger.valueOf(i + 1));  // Catalan number
			ans = ans.add(x.multiply(y.subtract(z)));
		}
		return ans.toString();
	}
	
}
