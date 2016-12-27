/* 
 * Solution to Project Euler problem 78
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p078 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p078().run());
	}
	
	
	private static final int MODULUS = Library.pow(10, 6);
	
	public String run() {
		for (int limit = 1; ; limit *= 2) {
			int result = search(limit);
			if (result != -1)
				return Integer.toString(result);
		}
	}
	
	
	private static int search(int limit) {
		/* 
		 * partitions[i] is {the number of ways i can be written
		 * as an unordered sum of positive integers} mod 10^6.
		 * Note that the partition function P(n, k) can be computed with
		 * dynamic programming using only 1 dimension for memoization.
		 */
		int[] partitions = new int[limit];
		partitions[0] = 1;
		for (int i = 1; i < limit; i++) {
			for (int j = i; j < limit; j++)
				partitions[j] = (partitions[j] + partitions[j - i]) % MODULUS;
		}
		for (int i = 0; i < limit; i++) {
			if (partitions[i] == 0)
				return i;
		}
		return -1;
	}
	
}
