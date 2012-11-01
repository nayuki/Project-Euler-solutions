/* 
 * Solution to Project Euler problem 149
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.List;


public final class p149 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p149().run());
	}
	
	
	private int[][] grid;
	
	
	public String run() {
		grid = new int[2000][2000];
		LfgRandom rand = new LfgRandom();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++)
				grid[y][x] = rand.next();
		}
		
		int max = 0;
		for (int x = 0; x < 2000; x++) {  // Top edge
			max = Math.max(getMaxSubstringSum(x, 0,  0, +1), max);  // Vertical
			max = Math.max(getMaxSubstringSum(x, 0, +1, +1), max);  // Diagonal
			max = Math.max(getMaxSubstringSum(x, 0, -1, +1), max);  // Anti-diagonal
		}
		for (int y = 0; y < 2000; y++) {  // Left edge
			max = Math.max(getMaxSubstringSum(0, y,  0, +1), max);  // Horizontal
			max = Math.max(getMaxSubstringSum(0, y, +1, +1), max);  // Diagonal
		}
		for (int y = 0; y < 2000; y++) {  // Right edge
			max = Math.max(getMaxSubstringSum(1999, y,  0, +1), max);  // Horizontal
			max = Math.max(getMaxSubstringSum(1999, y, -1, +1), max);  // Anti-diagonal
		}
		return Integer.toString(max);
	}
	
	
	private int getMaxSubstringSum(int x, int y, int dx, int dy) {
		List<Integer> list = new ArrayList<Integer>();
		for (; 0 <= x && x < 2000 && 0 <= y && y < 2000; x += dx, y += dy) {
			list.add(grid[y][x]);
		}
		
		int max = 0;
		int cur = 0;
		for (int item : list) {
			cur = Math.max(cur + item, 0);
			max = Math.max(cur, max);
		}
		return max;
	}
	
	
	
	// Lagged Fibonacci generator
	private static final class LfgRandom {
		
		private int k;
		
		private int[] history;  // Circular buffer
		private int index;
		
		
		public LfgRandom() {
			k = 1;
			history = new int[55];
			index = 0;
		}
		
		
		public int next() {
			int result;
			if (k <= 55) result = (int)((100003L - 200003L*k + 300007L*k*k*k) % 1000000) - 500000;
			else result = (getHistory(24) + getHistory(55) + 1000000) % 1000000 - 500000;
			k++;
			
			history[index] = result;
			index++;
			if (index == history.length)
				index = 0;
			
			return result;
		}
		
		
		private int getHistory(int n) {
			int i = index - n;
			if (i < 0)
				i += history.length;
			return history[i];
		}
		
	}
	
}
