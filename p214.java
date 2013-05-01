/* 
 * Solution to Project Euler problem 214
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p214 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p214().run());
	}
	
	
	private static final int LIMIT = 40000000;
	
	
	// Requires at least 320 MB of memory
	public String run() {
		int[] totient = Library.listTotients(LIMIT);
		int[] totientChainLength = new int[LIMIT + 1];
		totientChainLength[0] = 0;
		long sum = 0;
		// Fill table in ascending order because totient chains are strictly decreasing
		for (int i = 1; i < LIMIT; i++) {
			int chainlen = totientChainLength[totient[i]] + 1;
			totientChainLength[i] = chainlen;
			if (chainlen == 25 && totient[i] == i - 1)  // i is prime iff totient(i) = i-1
				sum += i;
		}
		return Long.toString(sum);
	}
	
}
