/* 
 * Solution to Project Euler problem 72
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p072 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p072().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 6);
	
	public String run() {
		long sum = 0;
		int[] totients = Library.listTotients(LIMIT);
		for (int i = 2; i < totients.length; i++)
			sum += totients[i];
		return Long.toString(sum);
	}
	
}
