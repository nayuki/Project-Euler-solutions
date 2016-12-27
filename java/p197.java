/* 
 * Solution to Project Euler problem 197
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p197 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p197().run());
	}
	
	
	private static long ITERATIONS = 1000000000000L;
	
	
	public String run() {
		// Floyd's cycle-finding algorithm
		double x = -1;
		double y = -1;
		long i = 0;
		for (; i < ITERATIONS; i++) {
			// Here at the top of the loop, x = f^i(-1) and y = f^{2i}(-1)
			
			if (i > 0 && x == y)  // This means index i is part of the cycle, and (2i - i) = i is some multiple of the true cycle length
				break;
			
			// Advance the states at different speeds
			x = f(x);
			y = f(f(y));
		}
		
		// Advance by many multiples of the cycle length, then deal with the remaining iterations
		long remain = (ITERATIONS - i) % i;
		for (; remain > 0; remain--)
			x = f(x);
		double answer = x + f(x);
		answer = Math.floor(answer * 1e9) / 1e9;  // Truncate to 9 digits after the decimal point
		return String.format("%.9f", answer);
	}
	
	
	private static double f(double x) {
		return Math.floor(Math.pow(2, 30.403243784 - x * x)) / 1e9;
	}
	
}
