/* 
 * Solution to Project Euler problem 20
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p020 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p020().run());
	}
	
	
	/* 
	 * We do a straightforward product with help from Java's BigInteger type.
	 */
	public String run() {
		String temp = Library.factorial(100).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++)
			sum += temp.charAt(i) - '0';
		return Integer.toString(sum);
	}
	
}
