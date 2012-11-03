/* 
 * Solution to Project Euler problem 6
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p006 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p006().run());
	}
	
	
	private static final int N = 100;
	
	
	public String run() {
		int sum = 0;
		int sum2 = 0;
		for (int i = 1; i <= N; i++) {
			sum += i;
			sum2 += i * i;
		}
		/* 
		 * For the mathematically inclined:
		 *   sum  = N(N + 1) / 2.
		 *   sum2 = N(N + 1)(2N + 1) / 6.
		 */
		return Integer.toString(sum * sum - sum2);
	}
	
}
