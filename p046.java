/* 
 * Solution to Project Euler problem 46
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p046 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p046().run());
	}
	
	
	public String run() {
		for (int i = 2; ; i++) {
			if (!satisfiesConjecture(i))
				return Integer.toString(i);
		}
	}
	
	
	private boolean satisfiesConjecture(int n) {
		if (n % 2 == 0 || isPrime(n))
			return true;
		
		// Now n is an odd composite number
		for (int i = 1; i * i * 2 <= n; i++) {
			if (isPrime(n - i * i * 2))
				return true;
		}
		return false;
	}
	
	
	private boolean[] isPrime = {};
	
	private boolean isPrime(int n) {
		if (n >= isPrime.length)
			isPrime = Library.listPrimality(n * 2);
		return isPrime[n];
	}
	
}
