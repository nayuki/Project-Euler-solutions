/* 
 * Solution to Project Euler problem 301
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p301 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p301().run());
	}
	
	
	public String run() {
		long count = 0;
		for (int i = 1; i <= (1 << 30); i++) {
			if ((i ^ (i * 2) ^ (i * 3)) == 0)
				count++;
		}
		return Long.toString(count);
	}
	
}
