/* 
 * Solution to Project Euler problem 43
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p043 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p043().run());
	}
	
	
	private static int[] DIVISIBILITY_TESTS = {2, 3, 5, 7, 11, 13, 17};  // First 7 primes
	
	
	public String run() {
		long sum = 0;
		int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		outer:
		do {
			for (int i = 0; i < DIVISIBILITY_TESTS.length; i++) {
				if (toInteger(digits, i + 1, 3) % DIVISIBILITY_TESTS[i] != 0)
					continue outer;
			}
			sum += toInteger(digits, 0, digits.length);
		} while (Library.nextPermutation(digits));
		return Long.toString(sum);
	}
	
	
	private static long toInteger(int[] digits, int off, int len) {
		long result = 0;
		for (int i = off; i < off + len; i++)
			result = result * 10 + digits[i];
		return result;
	}
	
}
