/* 
 * Solution to Project Euler problem 150
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p150 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p150().run());
	}
	
	
	private static final int ROWS = 1000;
	
	public String run() {
		// Generate the triangle
		LcgRandom rand = new LcgRandom();
		int[][] triangle = new int[ROWS][];
		for (int i = 0; i < triangle.length; i++) {
			triangle[i] = new int[i + 1];
			for (int j = 0; j <= i; j++)
				triangle[i][j] = rand.next();
		}
		
		// Calculate cumulative sums for each row
		int[][] rowSums = new int[triangle.length][];
		for (int i = 0; i < rowSums.length; i++) {
			rowSums[i] = new int[triangle[i].length + 1];
			rowSums[i][0] = 0;
			for (int j = 0; j <= i; j++)
				rowSums[i][j + 1] = rowSums[i][j] + triangle[i][j];
		}
		
		// Calculate minimum subtriangle sum for each apex position
		long minSum = 0;
		for (int i = 0; i < triangle.length; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				// Apex element selected at triangle[i][j]
				long curSum = 0;
				for (int k = i; k < triangle.length; k++) {  // Ending row (inclusive)
					curSum += rowSums[k][k - i + 1 + j] - rowSums[k][j];
					minSum = Math.min(curSum, minSum);
				}
			}
		}
		return Long.toString(minSum);
	}
	
	
	
	private static final class LcgRandom {
		
		private int state;
		
		
		public LcgRandom() {
			state = 0;
		}
		
		
		public int next() {
			state = (615949 * state + 797807) & ((1 << 20) - 1);
			return state - (1 << 19);
		}
		
	}
	
}
