/* 
 * Solution to Project Euler problem 24
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p024 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p024().run());
	}
	
	
	/* 
	 * We initialize an array as the lowest permutation of the given digits, which is the sequence
	 * (0,1,2,3,4,5,6,7,8,9). Then we call the next permutation algorithm on it 999 999 times
	 * (because the index in the problem is 1-based), and stringify the resulting sequence.
	 * 
	 * The next permutation algorithm is well-known and a bit long to explain.
	 * See: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	 */
	
	public String run() {
		// Initialize
		int[] array = new int[10];
		for (int i = 0; i < array.length; i++)
			array[i] = i;
		
		// Permute
		for (int i = 0; i < 999999; i++) {
			if (!Library.nextPermutation(array))
				throw new AssertionError();
		}
		
		// Format output
		String ans = "";
		for (int i = 0; i < array.length; i++)
			ans += array[i];
		return ans;
	}
	
}
