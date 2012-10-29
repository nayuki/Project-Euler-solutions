/* 
 * Solution to Project Euler problem 116
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p116 {
	
	public static void main(String[] args) {
		System.out.println(countWays(50, 2) + countWays(50, 3) + countWays(50, 4));
	}
	
	
	private static long countWays(int length, int coloredTileLength) {
		long[] ways = new long[length + 1];  // Memoization
		ways[0] = 1;
		for (int i = 1; i <= length; i++) {
			// Dynamic programming
			ways[i] += ways[i - 1];
			if (i >= coloredTileLength)
				ways[i] += ways[i - coloredTileLength];  
		}
		return ways[length] - 1;
	}
	
}
