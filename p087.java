/* 
 * Solution to Project Euler problem 87
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p087 {
	
	public static void main(String[] args) {
		boolean[] isPrime = Library.listPrimality(7071);  // sqrt(50 000 000)
		
		// Squares
		boolean[] possible = new boolean[50000000 + 1];
		for (int i = 0; i < isPrime.length && i * i < possible.length; i++) {
			if (!isPrime[i])
				continue;
			possible[i * i] = true;
		}
		
		// Cubes
		boolean[] nextPossible = new boolean[possible.length];
		for (int i = 0; i < isPrime.length && (long)i * i * i < possible.length; i++) {
			if (!isPrime[i])
				continue;
			int prod = i * i * i;
			for (int j = possible.length - 1 - prod; j >= 0; j--)
				nextPossible[j + prod] |= possible[j];
		}
		possible = nextPossible;
		
		// Fourth power
		nextPossible = new boolean[possible.length];
		for (int i = 0; i < isPrime.length && (long)i * i * i * i < possible.length; i++) {
			if (!isPrime[i])
				continue;
			int prod = i * i * i * i;
			for (int j = possible.length - 1 - prod; j >= 0; j--)
				nextPossible[j + prod] |= possible[j];
		}
		possible = nextPossible;
		
		int count = 0;
		for (boolean x : possible) {
			if (x)
				count++;
		}
		System.out.println(count);
	}
	
}
