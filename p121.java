/* 
 * Solution to Project Euler problem 121
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p121 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p121().run());
	}
	
	
	/* 
	 * At the beginning of turn number k (0-based), there are k + 2 discs to choose from.
	 * Hence a game that has n turns has (n+1) * n * ... * 1 = (n + 1)! outcomes.
	 * 
	 * Let f(k, b) be the number of ways to accumulate exactly b blue discs after k turns.
	 * We can see that:
	 * - f(0, 0) = 1.
	 * - f(0, b) = 0, for b > 0.
	 * - f(k, 0) = k * f(k - 1, 0), for k > 0.
	 *   (Add a red disc, where there are k ways)
	 * - f(k, b) = f(k - 1, b - 1) + k * f(k - 1, b), for k > 0, b > 0.
	 *   (Add a blue disc (1 way) or add a red disc (k ways))
	 * 
	 * Next, we calculate the sum f(n, j) + f(n, j+1) + ... + f(n, n),
	 * where j is the smallest number of blue discs accumulated that exceeds
	 * the number of red discs accumulated (which is n - j). So j = ceil((n + 1) / 2).
	 * 
	 * Finally, the probability of winning is that sum divided by (n + 1)!.
	 * For any game where the cost of playing is 1 and the probability of winning is p,
	 * the maximum sustainable prize is 1 / p, therefore the maximum sustainable integer prize is floor(1 / p).
	 */
	
	private static final int TURNS = 15;
	
	public String run() {
		// Dynamic programming
		BigInteger[][] ways = new BigInteger[TURNS + 1][];
		ways[0] = new BigInteger[]{BigInteger.ONE};
		for (int i = 1; i <= TURNS; i++) {
			ways[i] = new BigInteger[i + 1];
			for (int j = 0; j <= i; j++) {
				BigInteger temp = BigInteger.ZERO;
				if (j < i)
					temp = ways[i - 1][j].multiply(BigInteger.valueOf(i));
				if (j > 0)
					temp = temp.add(ways[i - 1][j - 1]);
				ways[i][j] = temp;
			}
		}
		
		BigInteger numer = BigInteger.ZERO;
		for (int i = TURNS / 2 + 1; i <= TURNS; i++)
			numer = numer.add(ways[TURNS][i]);
		BigInteger denom = Library.factorial(TURNS + 1);
		return denom.divide(numer).toString();
	}
	
}
