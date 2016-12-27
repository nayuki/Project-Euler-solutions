/* 
 * Solution to Project Euler problem 214
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p214 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p214().run());
	}
	
	
	private static final int LIMIT = 40000000;
	
	
	// Requires at least 320 MB of memory
	public String run() {
		int[] totient = Library.listTotients(LIMIT - 1);
		int[] totientChainLength = new int[totient.length];
		totientChainLength[0] = 0;
		long sum = 0;
		// Fill table in ascending order because totient chains are strictly decreasing
		for (int i = 1; i < totient.length; i++) {
			int chainlen = totientChainLength[totient[i]] + 1;
			totientChainLength[i] = chainlen;
			if (chainlen == 25 && totient[i] == i - 1)  // i is prime iff totient(i) = i-1
				sum += i;
		}
		return Long.toString(sum);
	}
	
}
