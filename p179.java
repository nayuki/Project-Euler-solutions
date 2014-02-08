/* 
 * Solution to Project Euler problem 179
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p179 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p179().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 7);
	
	
	public String run() {
		int[] smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT);
		
		int[] numDivisors = new int[LIMIT + 1];
		numDivisors[1] = 1;
		for (int i = 2; i < numDivisors.length; i++) {
			int p = smallestPrimeFactor[i];
			int exp = 0;
			int j = i;
			for (; j % p == 0; j /= p, exp++);
			numDivisors[i] = (exp + 1) * numDivisors[j];
		}
		
		int count = 0;
		for (int i = 2; i < LIMIT; i++) {
			if (numDivisors[i] == numDivisors[i + 1])
				count++;
		}
		return Integer.toString(count);
	}
	
}
