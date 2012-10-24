/* 
 * Solution to Project Euler problem 111
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p111 {
	
	private static int[] primes;
	private static long[] primesSquared;
	
	static {
		boolean[] isPrime = Library.listPrimality((int)Library.sqrt(9999999999L));
		int count = 0;
		for (boolean b : isPrime) {
			if (b)
				count++;
		}
		primes = new int[count];
		primesSquared = new long[count];
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				primes[j] = i;
				primesSquared[j] = (long)i * i;
				j++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		// Classify each 9-digit prime number for each digit value
		long[][] classSums = new long[10][11];  // classSums[i][j] is the sum of all the prime numbers where digit i occurs exactly j times
		for (long i = 1000000001; i <= 9999999999L; i += 2) {
			if (isPrime(i)) {
				int[] digitCounts = getDigitCounts(i);
				for (int j = 0; j < digitCounts.length; j++)
					classSums[j][digitCounts[j]] += i;
			}
		}
		
		long sum = 0;
		for (int i = 0; i < classSums.length; i++) {
			for (int j = classSums[i].length - 1; ; j--) {
				if (classSums[i][j] > 0) {
					sum += classSums[i][j];
					break;
				}
			}
		}
		System.out.println(sum);
	}
	
	
	// Returns the number of times each decimal digit occurred, excluding leading zeros.
	private static int[] getDigitCounts(long n) {
		int[] result = new int[10];
		for (; n != 0; n /= 10)
			result[(int)(n % 10)]++;
		return result;
	}
	
	
	private static boolean isPrime(long x) {
		for (int i = 0; i < primes.length && primesSquared[i] <= x; i++) {
			if (x % primes[i] == 0)
				return false;
		}
		return true;
	}
	
}
