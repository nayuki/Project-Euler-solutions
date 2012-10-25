/* 
 * Solution to Project Euler problem 87
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p087 {
	
	private static final int LIMIT = 50000000;
	
	
	public static void main(String[] args) {
		int[] primes = Library.listPrimes(Library.sqrt(LIMIT));
		
		// Squares
		boolean[] possible = new boolean[LIMIT + 1];
		for (int p : primes) {
			if (p * p >= LIMIT + 1)
				break;
			possible[p * p] = true;
		}
		
		// Cubes
		boolean[] nextPossible = new boolean[LIMIT + 1];
		for (int p : primes) {
			if ((long)p * p * p > LIMIT)
				break;
			int prod = p * p * p;
			for (int i = LIMIT - prod; i >= 0; i--)
				nextPossible[i + prod] |= possible[i];
		}
		possible = nextPossible;
		
		// Fourth power
		nextPossible = new boolean[LIMIT + 1];
		for (int p : primes) {
			if ((long)p * p * p * p > LIMIT)
				break;
			int prod = p * p * p * p;
			for (int i = LIMIT - prod; i >= 0; i--)
				nextPossible[i + prod] |= possible[i];
		}
		possible = nextPossible;
		
		// Count sums
		int count = 0;
		for (boolean x : possible) {
			if (x)
				count++;
		}
		System.out.println(count);
	}
	
}
