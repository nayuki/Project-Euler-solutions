/* 
 * Solution to Project Euler problem 2
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p002 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p002().run());
	}
	
	
	/* 
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 * Because the Fibonacci sequence grows exponentially by a factor of 1.618, the sum is
	 * bounded above by a small multiple of 4 million. Thus the answer fits in a Java int type.
	 */
	public String run() {
		int sum = 0;
		int x = 1;  // Represents the current Fibonacci number being processed
		int y = 2;  // Represents the next Fibonacci number in the sequence
		while (x <= 4000000) {
			if (x % 2 == 0)
				sum += x;
			int z = x + y;
			x = y;
			y = z;
		}
		return Integer.toString(sum);
	}
	
}
