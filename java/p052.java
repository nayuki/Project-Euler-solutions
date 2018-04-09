/* 
 * Solution to Project Euler problem 52
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p052 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p052().run());
	}
	
	
	public String run() {
		for (int i = 1; ; i++) {
			if (i > Integer.MAX_VALUE / 6)
				throw new ArithmeticException("Overflow");
			if (multiplesHaveSameDigits(i))
				return Integer.toString(i);
		}
	}
	
	
	private static boolean multiplesHaveSameDigits(int x) {
		for (int i = 2; i <= 6; i++) {
			if (!Arrays.equals(toSortedDigits(x), toSortedDigits(i * x)))
				return false;
		}
		return true;
	}
	
	
	private static char[] toSortedDigits(int x) {
		char[] result = Integer.toString(x).toCharArray();
		Arrays.sort(result);
		return result;
	}
	
}
