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
	
	
	public String run() {
		long[] ways = new long[51];  // Memoization
		ways[0] = 1;
		ways[1] = 1;
		for (int i = 2; i <= 50; i++) {
			for (int j = 1; j <= 4 && j <= i; j++)
				ways[i] += ways[i - j];  // Dynamic programming
		}
		return Long.toString(ways[50]);
	}
	
}
