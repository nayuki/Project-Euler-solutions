/* 
 * Solution to Project Euler problem 249
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p249 {
	
	private static int LIMIT = 5000;
	private static long MODULUS = 10000000000000000L;
	
	
	public static void main(String[] args) {
		boolean[] isPrime = Library.listPrimality(LIMIT * LIMIT / 2);
		long[] numSubsets = new long[LIMIT * LIMIT / 2];  // numSubsets[i] is the number of subsets with sum i, mod 10^16
		numSubsets[0] = 1;
		int maxIndex = 0;
		
		for (int i = 0; i < LIMIT; i++) {
			if (!isPrime[i])
				continue;
			maxIndex += i;
			for (int j = maxIndex; j >= i; j--)
				numSubsets[j] = (numSubsets[j] + numSubsets[j - i]) % MODULUS;
		}
		
		long sum = 0;
		for (int i = 0; i < numSubsets.length; i++) {
			if (isPrime[i])
				sum = (sum + numSubsets[i]) % MODULUS;
		}
		System.out.println(sum);
	}
	
}
