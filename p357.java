/* 
 * Solution to Project Euler problem 357
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p357 {
	
	private static boolean[] isPrime = Library.listPrimality(100000001);
	
	
	public static void main(String[] args) {
		long sum = 0;
		for (int i = 0; i <= 100000000; i++) {
			if (isPrimeGenerating(i))
				sum += i;
		}
		System.out.println(sum);
	}
	
	
	private static boolean isPrimeGenerating(int n) {
		for (int i = 1, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				if (!isPrime[i + n / i])
					return false;
			}
		}
		return true;
	}
	
}
