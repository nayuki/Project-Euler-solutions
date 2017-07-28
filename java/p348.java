/* 
 * Solution to Project Euler problem 348
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p348 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p348().run());
	}
	
	
	/* 
	 * Given a range of integers [0, n), we can bulk-calculate how many ways
	 * each integer can be expressed as a sum of a cube and a square.
	 * 
	 * Start by initializing an array of length n with all zeros.
	 * Next, write a two-level loop to explore all the cubes i^3
	 * and squares j^2 such that their sum is less than n:
	 * 
	 *   ways = new int[n];  // Initially all zero
	 *   for (i = 2; i^3 < n; i++) {
	 *     for (j = 2; i^3 + j^2 < n; j++) {
	 *       ways[i^3 + j^2]++;
	 *     }
	 *   }
	 * 
	 * The array creation takes O(n) time. The outer loop runs O(n^(1/3)) iterations,
	 * the inner loop runs O(n^(1/2)) iterations per outer loop, hence the inner loop body
	 * runs O(n^(5/6)) iterations. Thus the whole process runs in O(n) time and memory.
	 * 
	 * Finally we iterate forward through the array, selecting numbers that
	 * are palindromes and can be summed in the target number of ways.
	 * 
	 * If the answer is not found in our range [0, n), then we increase the range and search
	 * again. If we multiply n by some factor (say 2 or 10), then the geometric series ensures
	 * that the total work we do is O(n) with respect to the magnitude of the final answer.
	 */
	
	private static final int TARGET_WAYS = 4;
	private static final int TARGET_COUNT = 5;
	static {
		assert 0 <= TARGET_COUNT;
		assert 0 <= TARGET_WAYS && TARGET_WAYS <= Byte.MAX_VALUE - 1;
	}
	
	public String run() {
		for (long limit = 1; ; limit *= 10) {
			if (limit > Integer.MAX_VALUE)
				throw new AssertionError("Overflow");
			long answer = trySearch((int)limit);
			if (answer != -1)
				return Long.toString(answer);
		}
	}
	
	
	// Examines all integers in the range [0, limit), and returns the sum of the lowest
	// TARGET_COUNT integers each with the property that it is a palidrome in base 10
	// and it can be expressed in exactly TARGET_COUNT ways as a sum of a perfect square
	// greater than 1 and a perfect cube greater than 1. If fewer than TARGET_COUNT integers
	// in [0, limit) have the desired property, then -1 is returned. Note that if
	// trySearch(n) == k != -1, then for every m > n, trySearch(m) == k also holds.
	private static long trySearch(int limit) {
		// ways[i] is exactly the number of ways that i can be expressed as a sum of a
		// square greater than 1 and a cube greater than 1. However, the counting of ways
		// saturates at TARGET_COUNT+1 to prevent potentially unboundedly large values.
		byte[] ways = new byte[limit];
		
		// Count both loops downward, so that the end
		// value is a constant instead of a variable
		for (int i = cbrt(limit - 1); i > 1; i--) {
			int cube = i * i * i;
			for (int j = Library.sqrt(limit - 1 - cube); j > 1; j--) {
				int index = cube + j * j;
				ways[index] = (byte)Math.min(ways[index] + 1, TARGET_WAYS + 1);
			}
		}
		
		// Examine which numbers are palindromes and have the target number of ways
		long result = 0;
		int count = 0;
		for (int i = 0; i < ways.length; i++) {
			if (ways[i] == TARGET_WAYS && Library.isPalindrome(i)) {
				result += i;
				count++;
				if (count == TARGET_COUNT)
					return result;
			}
		}
		return -1;  // Not found
	}
	
	
	// Returns floor(cbrt(x)) for x >= 0.
	private static int cbrt(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Not implemented");
		int y = 0;
		for (int i = 1 << 10; i != 0; i >>>= 1) {
			y |= i;
			if (y > 1290 || y * y * y > x)
				y ^= i;
		}
		return y;
	}
	
}
