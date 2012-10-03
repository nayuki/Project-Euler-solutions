/* 
 * Solution to Project Euler problem 123
 * By Nayuki Minase
 */


public class p123 {
	
	private static final int PRIME_LIMIT = 1000000;
	
	private static int[] primes;
	
	static {
		boolean[] isPrime = Library.listPrimality(PRIME_LIMIT);
		int count = 0;
		for (boolean b : isPrime) {
			if (b)
				count++;
		}
		primes = new int[count];
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				primes[j] = i;
				j++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		long THRESHOLD = 10000000000L;
		for (int n = 5; n <= primes.length; n += 2) {
			// Using the result from Project Euler #120, we know that (a-1)^n + (a+1)^n mod a^2 = if n is even then 2 else 2an. Since 2 is tiny, we can skip the n is even case.
			// a is the n'th (1-based) prime number, so a > n. In fact for n >= 5, we have a > 2n, so we can take 2an directly without moduloing it by a^2.
			long rem = (long)n * primes[n - 1] * 2;
			if (rem > THRESHOLD) {
				System.out.println(n);
				return;
			}
		}
		throw new AssertionError("Not found");
	}
	
}
