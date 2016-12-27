/* 
 * Solution to Project Euler problem 9
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p009 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p009().run());
	}
	
	
	/* 
	 * Computers are fast, so we can implement a brute-force search to directly solve the problem.
	 * Note that a^2 + b^2 is bounded above by 2*(1000^2), which fits in a Java int type.
	 */
	private static final int PERIMETER = 1000;
	
	public String run() {
		for (int a = 1; a < PERIMETER; a++) {
			for (int b = a + 1; b < PERIMETER; b++) {
				int c = PERIMETER - a - b;
				if (a * a + b * b == c * c) {
					// It is now implied that b < c, because we have a > 0
					return Integer.toString(a * b * c);
				}
			}
		}
		throw new AssertionError("Not found");
	}
	
}
