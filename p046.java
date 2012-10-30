/* 
 * Solution to Project Euler problem 46
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p046 implements EulerSolution {
	
	private static boolean[] isPrime = Library.listPrimality(10000);
	
	
	public static void main(String[] args) {
		System.out.println(new p046().run());
	}
	
	
	public String run() {
		outer:
		for (int i = 3; i < isPrime.length; i += 2) {
			if (!isPrime[i]) {
				for (int j = 1; 2 * j * j <= i; j++) {
					if (isPrime[i - 2 * j * j])
						continue outer;
				}
				return Integer.toString(i);
			}
		}
		throw new RuntimeException("Not found");
	}
	
}
