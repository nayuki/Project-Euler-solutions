/* 
 * Solution to Project Euler problem 187
 * By Nayuki Minase
 */


public class p187 {
	
	private static int[] smallestPrimeFactor = listSmallestPrimeFactor(100000000);
	
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 2; i < 100000000; i++) {
			if (!isPrime(i) && isPrime(i / smallestPrimeFactor[i]))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static int[] listSmallestPrimeFactor(int n) {
		// Richer version of the sieve of Eratosthenes
		int[] smallestPrimeFactor = new int[n + 1];
		for (int i = 2; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == 0) {
				smallestPrimeFactor[i] = i;
				if ((long)i * i <= n) {
					for (int j = i * i; j <= n; j += i) {
						if (smallestPrimeFactor[j] == 0)
							smallestPrimeFactor[j] = i;
					}
				}
			}
		}
		return smallestPrimeFactor;
	}
	
	
	private static boolean isPrime(int n) {
		return smallestPrimeFactor[n] == n;
	}
	
}
