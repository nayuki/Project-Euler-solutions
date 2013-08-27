/* 
 * Solution to Project Euler problem 9
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p009 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p009().run());
	}
	
	
	public String run() {
		for (int a = 1; a < 1000; a++) {
			for (int b = a + 1; b < 1000; b++) {
				int c = 1000 - a - b;
				if (a * a + b * b == c * c)  // Note: This implies b < c
					return Integer.toString(a * b * c);
			}
		}
		throw new AssertionError("Not found");
	}
	
}
