/* 
 * Solution to Project Euler problem 108
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p108 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p108().run());
	}
	
	
	public String run() {
		int n = 1;
		while (numberOfSolutions(n) <= 1000)
			n++;
		return Integer.toString(n);
	}
	
	
	private static int numberOfSolutions(int n) {
		int count = 0;
		for (int x = n + 1; x <= 2 * n; x++) {
			if ((long)x * n % (x - n) == 0)
				count++;
		}
		return count;
	}
	
}
