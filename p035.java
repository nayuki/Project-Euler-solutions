/* 
 * Solution to Project Euler problem 35
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p035 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p035().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 6);
	
	private boolean[] isPrime = Library.listPrimality(LIMIT - 1);
	
	
	public String run() {
		int count = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isCircularPrime(i))
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private boolean isCircularPrime(int n) {
		String s = Integer.toString(n);
		for (int i = 0; i < s.length(); i++) {
			if (!isPrime[Integer.parseInt(s.substring(i) + s.substring(0, i))])
				return false;
		}
		return true;
	}
	
}
