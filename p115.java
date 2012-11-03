/* 
 * Solution to Project Euler problem 115
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p115 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p115().run());
	}
	
	
	public String run() {
		for (int n = 1; ; n++) {
			if (fillCount(50, n) > 1000000)
				return Long.toString(n);
		}
	}
	
	
	/* 
	 * How many ways can a row n units long be filled, where red blocks are
	 * at least m units long? Denote this quantity as ways[n].
	 * Compute n = 0 manually as a base case.
	 * 
	 * Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
	 * - If the item is a black square, then the rest of the row is allowed
	 *   to be anything of length n-1. Add ways[n-1].
	 * - If the item is a red block with length k where k >= m, then:
	 *   - If k = n, then the whole row is filled by this red block. Add 1.
	 *   - Otherwise k < n, this red block is followed by a black square, then followed
	 *     by anything of length n-k-1. So add ways[n-m-1] + ways[n-m-2] + ... + ways[0].
	 */
	private static long fillCount(int m, int N) {
		// Dynamic programming
		long[] ways = new long[N + 1];
		ways[0] = 1;
		for (int n = 1; n <= N; n++) {
			long sum = ways[n - 1];
			for (int k = m; k < n; k++)
				sum += ways[n - k - 1];
			if (n >= m)
				sum++;
			ways[n] = sum;
		}
		return ways[N];
	}
	
}
