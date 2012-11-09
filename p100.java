/* 
 * Solution to Project Euler problem 100
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p100 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p100().run());
	}
	
	
	public String run() {
		// Initialization
		for (long i = 0; i < isSquare0.length; i++) isSquare0[(int)(i * i % isSquare0.length)] = true;
		for (long i = 0; i < isSquare1.length; i++) isSquare1[(int)(i * i % isSquare1.length)] = true;
		for (long i = 0; i < isSquare2.length; i++) isSquare2[(int)(i * i % isSquare2.length)] = true;
		for (long i = 0; i < isSquare3.length; i++) isSquare3[(int)(i * i % isSquare3.length)] = true;
		for (long i = 0; i < isSquare4.length; i++) isSquare4[(int)(i * i % isSquare4.length)] = true;
		
		// Test candidates
		long n = 1000000000000L;
		int n0 = (int)(n % isSquare0.length);  // Always equal to n mod isSquare0.length
		int n1 = (int)(n % isSquare1.length);  // Always equal to n mod isSquare1.length
		while (true) {
			if (isCandidate(n, n0, n1)) {
				BigInteger x = BigInteger.valueOf(n);
				BigInteger temp = x.shiftLeft(1).subtract(BigInteger.valueOf(2)).multiply(x).add(BigInteger.ONE);
				BigInteger sqrt = sqrt(temp);
				if (sqrt.testBit(0) && sqrt.multiply(sqrt).equals(temp))
					return Long.toString((sqrt.longValue() + 1) / 2);
			}
			
			n++;
			n0++;
			n1++;
			if (n0 == isSquare0.length)
				n0 = 0;
			if (n1 == isSquare1.length)
				n1 = 0;
		}
	}
	
	
	private boolean[] isSquare0 = new boolean[3 * 5 * 7 * 11 * 13];
	private boolean[] isSquare1 = new boolean[17 * 19 * 23];
	private boolean[] isSquare2 = new boolean[29 * 31 * 37 * 41 * 43];
	private boolean[] isSquare3 = new boolean[47 * 53 * 59 * 61];
	private boolean[] isSquare4 = new boolean[67 * 71 * 73 * 79];
	
	
	private boolean isCandidate(long n, int n0, int n1) {
		if (!isSquare0[((2 * n0 - 2) * n0 + 1) % isSquare0.length])
			return false;
		
		if (!isSquare1[((2 * n1 - 2) * n1 + 1) % isSquare1.length])
			return false;
		
		long x = n % isSquare2.length;
		x = (2 * x - 2) * x + 1;
		if (!isSquare2[(int)(x % isSquare2.length)])
			return false;
		
		x = n % isSquare3.length;
		x = (2 * x - 2) * x + 1;
		if (!isSquare3[(int)(x % isSquare3.length)])
			return false;
		
		x = n % isSquare4.length;
		x = (2 * x - 2) * x + 1;
		if (!isSquare4[(int)(x % isSquare4.length)])
			return false;
		
		return true;
	}
	
	
	// Returns floor(sqrt(x))
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
