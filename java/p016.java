/* 
 * Solution to Project Euler problem 16
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p016 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p016().run());
	}
	
	
	/* 
	 * We implement this solution in a straightforward way with help from BigInteger.
	 */
	public String run() {
		String temp = BigInteger.ONE.shiftLeft(1000).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++)
			sum += temp.charAt(i) - '0';
		return Integer.toString(sum);
	}
	
}
