/* 
 * Solution to Project Euler problem 117
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p117 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p117().run());
	}
	
	
	/* 
	 * How many ways can a row n units long be filled with:
	 * - Black squares 1 unit long
	 * - Red tiles 2 units long
	 * - Green tiles 3 units long
	 * - Blue tiles 4 units long
	 * Denote this quantity as ways[n].
	 * 
	 * Compute n = 0 manually as a base case.
	 * Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
	 * - Black square (n>=1): Rest of the row can be anything of length n-1. Add ways[n-1].
	 * - Red tile     (n>=2): Rest of the row can be anything of length n-2. Add ways[n-2].
	 * - Green tile   (n>=3): Rest of the row can be anything of length n-3. Add ways[n-3].
	 * - Blue tile    (n>=4): Rest of the row can be anything of length n-4. Add ways[n-4].
	 */
	
	private static final int LENGTH = 50;
	
	public String run() {
		// Dynamic programming
		long[] ways = new long[LENGTH + 1];
		ways[0] = 1;
		for (int n = 1; n <= LENGTH; n++) {
			for (int k = 1; k <= 4 && k <= n; k++)
				ways[n] += ways[n - k];
		}
		return Long.toString(ways[LENGTH]);
	}
	
}
