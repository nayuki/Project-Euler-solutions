/* 
 * Solution to Project Euler problem 116
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p116 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p116().run());
	}
	
	
	private static final int LENGTH = 50;
	
	public String run() {
		return Long.toString(countWays(LENGTH, 2) + countWays(LENGTH, 3) + countWays(LENGTH, 4));
	}
	
	
	/* 
	 * How many ways can a row n units long be filled with black squares 1 unit long
	 * and colored tiles m units long? Denote this quantity as ways[n].
	 * Compute n = 0 manually as a base case.
	 * 
	 * Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
	 * - If the item is a black square, then the rest of the row
	 *   is allowed to be anything of length n-1. Add ways[n-1].
	 * - If the item is a colored tile of length m where m <= n, then the
	 *   rest of the row can be anything of length n-m. Add ways[n-m].
	 * 
	 * At the end, return ways[length]-1 to exclude the case where the row is all black squares.
	 */
	private static long countWays(int length, int m) {  // m is the length of colored tiles
		// Dynamic programming
		long[] ways = new long[length + 1];
		ways[0] = 1;
		for (int n = 1; n <= length; n++) {
			ways[n] += ways[n - 1];
			if (n >= m)
				ways[n] += ways[n - m];
		}
		return ways[length] - 1;
	}
	
}
