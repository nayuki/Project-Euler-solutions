/* 
 * Solution to Project Euler problem 80
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p080 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p080().run());
	}
	
	
	public String run() {
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			BigInteger x = BigInteger.valueOf(i);
			x = x.multiply(BigInteger.TEN.pow(100 * 2));  // Shift left so that we can obtain 100 digits after the decimal point
			BigInteger y = sqrt(x);
			if (!y.multiply(y).equals(x)) {  // Skip perfect squares
				// Strip rightmost digits so that we have exactly 100 decimal digits (some are before the decimal point)
				String s = y.toString().substring(0, 100);
				for (int j = 0; j < s.length(); j++)
					sum += s.charAt(j) - '0';
			}
		}
		return Integer.toString(sum);
	}
	
	
	private static BigInteger sqrt(BigInteger x) {
		// Find leftmost position
		int i = 0;
		while (BigInteger.TEN.pow(i * 2).compareTo(x) <= 0)
			i++;
		
		// Extract square root from left to right using an algorithm like long division
		BigInteger y = BigInteger.ZERO;
		for (; i >= 0; i--) {
			// Try every value for next digit
			int j;
			BigInteger delta = null;
			for (j = 9; j >= 0; j--) {
				BigInteger temp = BigInteger.valueOf(j).multiply(BigInteger.TEN.pow(i));
				delta = y.shiftLeft(1).add(temp).multiply(temp);
				if (delta.compareTo(x) <= 0)
					break;
			}
			if (j < 0)
				throw new AssertionError();
			
			x = x.subtract(delta);  // Adjust the remainder
			y = y.add(BigInteger.valueOf(j).multiply(BigInteger.TEN.pow(i)));  // Add the new digit
		}
		
		return y;
	}
	
}
