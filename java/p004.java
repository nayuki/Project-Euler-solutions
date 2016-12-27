/* 
 * Solution to Project Euler problem 4
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p004 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p004().run());
	}
	
	
	/* 
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 * Note that the maximum product is 999 * 999, which fits in a Java int type.
	 */
	public String run() {
		int maxPalin = -1;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int prod = i * j;
				if (Library.isPalindrome(prod) && prod > maxPalin)
					maxPalin = prod;
			}
		}
		return Integer.toString(maxPalin);
	}
	
}
