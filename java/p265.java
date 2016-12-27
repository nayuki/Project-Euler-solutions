/* 
 * Solution to Project Euler problem 265
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p265 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p265().run());
	}
	
	
	/* 
	 * In this problem we look at 2^n-digit binary strings and the n-digit substrings of these.
	 * We are given that n = 5, so we are looking at windows of 5 bits in 32-bit strings.
	 * 
	 * There are of course 32 possible cyclic windows in a 32-bit string.
	 * We want each of these windows to be a unique 5-bit string. There are exactly 2^5 = 32
	 * possible 5-bit strings, hence the 32 windows must cover the 5-bit space exactly once.
	 * 
	 * The result requires the substring of all zeros to be in the most significant bits.
	 * We argue that the top n bits must be all zeros, because this is one of the cyclic windows
	 * and the value 00...00 must occur once. Furthermore the next and previous bit must be 1 -
	 * because if they're not, then at least one of the adjacent windows are also zero, which
	 * violates the uniqueness requirement.
	 * 
	 * With n = 5, this means every candidate string must start with 000001 and end with 1.
	 * In other words, they are of the form 000001xxxxxxxxxxxxxxxxxxxxxxxxx1.
	 * The middle 25 bits still need to be determined, and we simply search by brute force.
	 */
	
	// Must be in the range [1, 5], otherwise the algorithm overflows
	private static final int N = 5;
	
	// Equal to 2^n. Here it is equal to 32.
	private static final int TWO_POW_N = 1 << N;
	
	public String run() {
		long sum = 0;
		int start = (1 << (TWO_POW_N - N - 1)) + 1;
		int end = 1 << (TWO_POW_N - N);
		for (int i = start; i < end; i += 2) {
			if (checkArrangement(i))
				sum += i;
		}
		return Long.toString(sum);
	}
	
	
	// Equal to n 1's in binary, i.e. 0b11111.
	private static final int MASK = TWO_POW_N - 1;
	
	// Equal to 2^n 1's in binary, i.e. 0b11111111111111111111111111111111.
	private static final int FULL_SET = (int)(1L << TWO_POW_N) - 1;
	
	// Tests whether all the n-bit cyclic windows of the given 2^n-bit number are unique values.
	private static boolean checkArrangement(int digits) {
		int seen = 0;
		long temp = (digits & 0xFFFFFFFFL) | ((digits & 0xFFFFFFFFL) << TWO_POW_N);  // Two copies
		for (int i = 0; i < TWO_POW_N; i++)
			seen |= 1 << ((int)(temp >>> i) & MASK);
		return seen == FULL_SET;
	}
	
}
