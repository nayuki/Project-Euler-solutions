/* 
 * Solution to Project Euler problem 7
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p007 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p007().run());
	}
	
	
	public String run() {
		for (int i = 2, count = 0; ; i++) {
			if (Library.isPrime(i)) {
				count++;
				if (count == 10001)
					return Integer.toString(i);
			}
		}
	}
	
}
