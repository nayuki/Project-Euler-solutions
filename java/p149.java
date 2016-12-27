/* 
 * Solution to Project Euler problem 149
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p149 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p149().run());
	}
	
	
	private static final int SIZE = 2000;
	
	private int[][] grid;
	
	
	public String run() {
		// Fill the grid
		grid = new int[SIZE][SIZE];
		LfgRandom rand = new LfgRandom();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++)
				grid[y][x] = rand.next();
		}
		
		// Scan along all line directions and positions
		int max = 0;
		for (int i = 0; i < SIZE; i++) {
			max = Math.max(getMaxSubstringSum(0, i, +1,  0), max);  // Horizontal from left edge
			max = Math.max(getMaxSubstringSum(i, 0,  0, +1), max);  // Vertical from top edge
			max = Math.max(getMaxSubstringSum(0, i, +1, +1), max);  // Diagonal from left edge
			max = Math.max(getMaxSubstringSum(i, 0, +1, +1), max);  // Diagonal from top edge
			max = Math.max(getMaxSubstringSum(i, 0, -1, +1), max);  // Anti-diagonal from top edge
			max = Math.max(getMaxSubstringSum(SIZE - 1, i, -1, +1), max);  // Anti-diagonal from right edge
		}
		return Integer.toString(max);
	}
	
	
	// For the sequence of numbers in the grid at positions (x, y), (x+dx, y+dy), (x+2*dx, y+2*dy), ... until the
	// last in-bounds indices, this function returns the maximum sum among all possible substrings of this sequence.
	private int getMaxSubstringSum(int x, int y, int dx, int dy) {
		int max = 0;
		for (int cur = 0; 0 <= x && x < SIZE && 0 <= y && y < SIZE; x += dx, y += dy) {
			cur = Math.max(cur + grid[y][x], 0);  // Reset the running sum if it goes negative
			max = Math.max(cur, max);  // Keep track of the best seen running sum
		}
		return max;
	}
	
	
	
	// Lagged Fibonacci generator
	private static final class LfgRandom {
		
		// Circular buffer
		private int[] history;
		private int index;
		
		private int k;  // The 1-based index of the next sequence item, but saturates at 56
		
		
		public LfgRandom() {
			k = 1;
			history = new int[55];
			index = 0;
		}
		
		
		public int next() {
			int result;
			if (k <= 55) {
				result = (int)((100003L - 200003L*k + 300007L*k*k*k) % 1000000) - 500000;
				k++;
			} else {
				result = (getHistory(24) + getHistory(55) + 1000000) % 1000000 - 500000;
				// Don't increment k, to prevent overflow
			}
			history[index] = result;
			index = (index + 1) % history.length;
			return result;
		}
		
		
		// Returns the number that was generated n steps ago, where 1 <= n <= history.length.
		private int getHistory(int n) {
			if (n <= 0 || n > history.length)
				throw new IllegalArgumentException();
			return history[(index - n + history.length) % history.length];
		}
		
	}
	
}
