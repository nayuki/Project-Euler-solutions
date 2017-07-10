/* 
 * Solution to Project Euler problem 225
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p225 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p225().run());
	}
	
	
	private static final int INDEX = 124;  // 1-based
	
	public String run() {
		int count = 0;
		for (int i = 1; ; i += 2) {
			if (!hasTribonacciMultiple(i)) {
				count++;
				if (count == INDEX)
					return Integer.toString(i);
			}
		}
	}
	
	
	// Tests whether any term of the Tribonacci sequence [1, 1, 1, 3, 5, 9, 17, 31, ...]
	// is a multiple of 'modulus'. Although the mathematical sequence is infinitely long,
	// this function returns an answer in O(modulus^3) time.
	private static boolean hasTribonacciMultiple(int modulus) {
		// Floyd's cycle-finding algorithm
		int[] slow = {1, 1, 1};
		int[] fast = slow.clone();
		for (boolean head = true; ; head = false) {
			if (slow[0] % modulus == 0)
				return true;
			if (!head && Arrays.equals(slow, fast))
				return false;
			tribonacci(slow, modulus);
			tribonacci(fast, modulus);
			tribonacci(fast, modulus);
		}
	}
	
	
	// Advances the 3-element Tribonacci state vector by one iteration in place.
	private static void tribonacci(int[] state, int mod) {
		int a = state[0];
		int b = state[1];
		int c = state[2];
		state[0] = b;
		state[1] = c;
		state[2] = (a + b + c) % mod;
	}
	
}
