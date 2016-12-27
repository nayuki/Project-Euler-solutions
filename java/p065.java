/* 
 * Solution to Project Euler problem 65
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p065 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p065().run());
	}
	
	
	public String run() {
		BigInteger n = BigInteger.ONE;
		BigInteger d = BigInteger.ZERO;
		for (int i = 99; i >= 0; i--) {
			BigInteger temp = BigInteger.valueOf(continuedFractionTerm(i)).multiply(n).add(d);
			d = n;
			n = temp;
		}
		
		int sum = 0;
		while (!n.equals(BigInteger.ZERO)) {
			BigInteger[] divrem = n.divideAndRemainder(BigInteger.TEN);
			sum += divrem[1].intValue();
			n = divrem[0];
		}
		return Integer.toString(sum);
	}
	
	
	private static int continuedFractionTerm(int i) {
		if (i == 0)
			return 2;
		else if (i % 3 == 2)
			return i / 3 * 2 + 2;
		else
			return 1;
	}
	
}
