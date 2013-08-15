/* 
 * Solution to Project Euler problem 77
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p077 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p077().run());
	}
	
	
	private static final int TARGET = 5000;
	
	public String run() {
		for (int limit = 1; ; limit *= 2) {
			int result = search(limit);
			if (result != -1)
				return Integer.toString(result);
		}
	}
	
	
	private static int search(int limit) {
		int[] primes = Library.listPrimes(limit);
		// partitions[i][j] is the number of ways (with upper saturation at TARGET)
		// that j can be written as an unordered sum with terms drawn from the first i prime numbers
		int[][] partitions = new int[primes.length + 1][limit + 1];
		partitions[0][0] = 1;
		for (int i = 0; i < primes.length; i++) {
			int p = primes[i];
			for (int j = 0; j <= limit; j++) {
				int sum = partitions[i][j];
				if (j >= p)
					sum += partitions[i + 1][j - p];
				partitions[i + 1][j] = Math.min(sum, TARGET);  // Saturate to prevent overflow
			}
		}
		for (int i = 0; i <= limit; i++) {
			if (partitions[primes.length][i] >= TARGET)
				return i;
		}
		return -1;
	}
	
}
