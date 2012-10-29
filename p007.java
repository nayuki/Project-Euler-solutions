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
		boolean[] isPrime = Library.listPrimality(300000);
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				j++;
				if (j == 10001)
					return Integer.toString(i);
			}
		}
		throw new RuntimeException("Not found");
	}
	
}
