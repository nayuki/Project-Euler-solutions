/* 
 * Solution to Project Euler problem 31
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p031 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p031().run());
	}
	
	
	/* 
	 * We use the standard dynamic programming algorithm to solve the subset sum problem over integers.
	 * The order of the coin values does not matter, but the values need to be unique.
	 */
	
	private static final int TOTAL = 200;
	private static int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200};
	
	public String run() {
		// ways[i][j] is the number of ways to use any copies of
		// the first i coin values to form an unordered sum of j
		int[][] ways = new int[COINS.length + 1][TOTAL + 1];
		ways[0][0] = 1;
		for (int i = 0; i < COINS.length; i++) {
			int coin = COINS[i];
			for (int j = 0; j <= TOTAL; j++)
				ways[i + 1][j] = ways[i][j] + (j >= coin ? ways[i + 1][j - coin] : 0);
		}
		return Integer.toString(ways[COINS.length][TOTAL]);
	}
	
}
