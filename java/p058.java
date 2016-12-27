/* 
 * Solution to Project Euler problem 58
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p058 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p058().run());
	}
	
	
	/* 
	 * From the diagram, let's observe the four corners of an n * n square (where n is odd).
	 * It's not hard to convince yourself that:
	 * - The bottom right corner always has the value n^2.
	 * Working clockwise (backwards):
	 * - The bottom left corner has the value n^2 - (n - 1).
	 * - The top left corner has the value n^2 - 2(n - 1).
	 * - The top right has the value n^2 - 3(n - 1).
	 * 
	 * Furthermore, the number of elements on the diagonal is 2n - 1.
	 */
	public String run() {
		int numPrimes = 0;
		for (int n = 1; ; n += 2) {
			for (int i = 0; i < 4; i++) {
				if (Library.isPrime(n * n - i * (n - 1)))
					numPrimes++;
			}
			if (n > 1 && numPrimes * 10 < n * 2 - 1)
				return Integer.toString(n);
		}
		
	}
	
}
