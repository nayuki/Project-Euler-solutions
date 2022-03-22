/* 
 * Solution to Project Euler problem 114
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p114 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p114().run());
	}
	
	
	/* 
	 * How many ways can a row n units long be filled? Denote this quantity as ways[n].
	 * Compute n = 0, 1, 2 manually as base cases.
	 * 
	 * Now assume n >= 3. Look at the leftmost item and sum up the possibilities.
	 * - If the item is a grey square, then the rest of the row is allowed
	 *   to be anything of length n-1. Add ways[n-1].
	 * - If the item is a red block with length k where k >= 3, then:
	 *   - If k = n, then the whole row is filled by this red block. Add 1.
	 *   - Otherwise k < n, this red block is followed by a grey square, then followed
	 *     by anything of length n-k-1. So add ways[n-4] + ways[n-5] + ... + ways[0].
	 */
	
	private static final int LENGTH = 50;
	
	public String run() {
		// Dynamic programming
		long[] ways = new long[LENGTH + 1];
		ways[0] = 1;
		ways[1] = 1;
		ways[2] = 1;
		for (int n = 3; n <= LENGTH; n++) {
			long sum = ways[n - 1] + 1;
			for (int k = 3; k < n; k++)
				sum += ways[n - k - 1];
			ways[n] = sum;
		}
		return Long.toString(ways[LENGTH]);
	}
	
}
