/* 
 * Solution to Project Euler problem 108
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p108 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p108().run());
	}
	
	
	/* 
	 * Rewrite the equation with x = n+i, y = n+j, and manipulate it:
	 *   1/n = 1/x + 1/y
	 *       = 1/(n+i) + 1/(n+j)
	 *       = (2n+i+j) / ((n+i)(n+j)).
	 *   n(2n+i+j) = (n+i)(n+j).
	 *   2n^2 + ni + nj = n^2 + ni + nj + ij.
	 *   n^2 = ij.
	 * Hence i and j are divisors of n^2. To ensure unique solutions,
	 * we impose that x <= y, so i <= j. Also, i > 0, otherwise no j exists.
	 * We have i <= j = n^2 / i, thus i^2 <= n^2. With i being positive, we get that i <= n.
	 * Therefore the number of solutions for i is the number of divisors of n^2 in the range [1, n].
	 * n^2 always has an odd number of divisors. One of them is n. As for the remainder of them, half of them are below n
	 * and half of them are above n. So if n^2 has m divisors, then we want (m+1)/2 of them as solutions for i.
	 */
	public String run() {
		for (int n = 1; ; n++) {
			if ((countDivisorsSquared(n) + 1) / 2 > 1000)
				return Integer.toString(n);
		}
	}
	
	
	// Returns the number of divisors of n^2
	private static int countDivisorsSquared(int n) {
		int count = 1;
		for (int i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				int j = 0;
				do {
					n /= i;
					j++;
				} while (n % i == 0);
				count *= j * 2 + 1;
				end = Library.sqrt(n);
			}
		}
		if (n != 1)  // Remaining largest prime factor
			count *= 3;
		return count;
	}
	
}
