/* 
 * Solution to Project Euler problem 55
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p055 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p055().run());
	}
	
	
	public String run() {
		int count = 0;
		for (int i = 0; i < 10000; i++) {
			if (isLychrel(i))
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private static boolean isLychrel(int n) {
		BigInteger temp = BigInteger.valueOf(n);
		for (int i = 0; i < 49; i++) {
			temp = temp.add(new BigInteger(Library.reverse(temp.toString())));
			if (Library.isPalindrome(temp.toString()))
				return false;
		}
		return true;
	}
	
}
