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
	
	
	public String run() {
		int sum = 0;
		int sum2 = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
			sum2 += i * i;
		}
		return Integer.toString(sum * sum - sum2);
	}
	
}
