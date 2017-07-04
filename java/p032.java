/* 
 * Solution to Project Euler problem 32
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p032 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p032().run());
	}
	
	
	/* 
	 * For contradiction suppose a candidate (x, y, z) has z >= 10000.
	 * Then x*y consumes at least 5 digits. With the 4 (or fewer)
	 * remaining digits, even the upper bound of x=99 and y=99
	 * produces a product of x*y < 10000, which is unequal to z.
	 * 
	 * Therefore we need the product z < 10000 to be able to find
	 * possible x and y values.
	 */
	public String run() {
		int sum = 0;
		for (int i = 1; i < 10000; i++) {
			if (hasPandigitalProduct(i))
				sum += i;
		}
		return Integer.toString(sum);
	}
	
	
	private static boolean hasPandigitalProduct(int n) {
		// Find and examine all factors of n
		for (int i = 1; i <= n; i++) {
			if (n % i == 0 && isPandigital("" + n + i + n/i))
				return true;
		}
		return false;
	}
	
	
	private static boolean isPandigital(String s) {
		if (s.length() != 9)
			return false;
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp).equals("123456789");
	}
	
}
