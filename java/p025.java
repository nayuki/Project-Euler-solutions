/* 
 * Solution to Project Euler problem 25
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p025 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p025().run());
	}
	
	
	/* 
	 * Because the target number is relatively small, we simply compute each Fibonacci number starting
	 * from the beginning until we encounter one with exactly 1000 digits. The Fibonacci sequence grows
	 * exponentially with a base of about 1.618, so the numbers in base 10 will lengthen by one digit
	 * after every log10(1.618) ~= 4.78 steps on average. This means the answer is at index around 4780.
	 */
	
	private static final int DIGITS = 1000;
	
	public String run() {
		BigInteger lowerThres = BigInteger.TEN.pow(DIGITS - 1);
		BigInteger upperThres = BigInteger.TEN.pow(DIGITS);
		BigInteger prev = BigInteger.ONE;
		BigInteger cur = BigInteger.ZERO;
		for (int i = 0; ; i++) {
			// At this point, prev = fibonacci(i - 1) and cur = fibonacci(i)
			if (cur.compareTo(upperThres) >= 0)
				throw new RuntimeException("Not found");
			else if (cur.compareTo(lowerThres) >= 0)
				return Integer.toString(i);
			
			// Advance the Fibonacci sequence by one step
			BigInteger temp = cur.add(prev);
			prev = cur;
			cur = temp;
		}
	}
	
}
