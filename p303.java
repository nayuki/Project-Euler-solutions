/* 
 * Solution to Project Euler problem 303
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public final class p303 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p303().run());
	}
	
	
	public String run() {
		BigInteger sum = BigInteger.ZERO;
		for (int n = 1; n <= 10000; n++)
			sum = sum.add(findMinimumMultiple(n).divide(BigInteger.valueOf(n)));
		return sum.toString();
	}
	
	
	private static BigInteger findMinimumMultiple(int n) {
		/* 
		 * isSumFeasible.get(i)[j] indicates whether it is possible to form a sum of j mod n
		 * using i (rightmost) digits, where each digit is between 0 and 2, and leading digits can be 0.
		 * Possible values:
		 * - 0: The sum is impossible
		 * - 1: The sum is possible but all digits are zero
		 * - 2: The sum is possible and at least one digit is non-zero
		 */
		
		// Initialization and base case
		List<byte[]> isSumFeasible = new ArrayList<byte[]>();
		{
			byte[] temp = new byte[n];
			temp[0] = 1;
			isSumFeasible.add(temp);
		}
		
		// Add digits on the left side until a solution exists, using dynamic programming
		int i = 0;  // Loop invariant: i == isSumFeasible.size() - 1
		while (isSumFeasible.get(i)[0] != 2) {
			byte[] prev = isSumFeasible.get(i);
			byte[] cur = new byte[n];
			int digitMod = Library.powMod(10, i, n);
			for (int j = 0; j < n; j++) {
				if (prev[j] > 0) {
					cur[(j + digitMod * 0) % n] = prev[j];
					cur[(j + digitMod * 1) % n] = 2;
					cur[(j + digitMod * 2) % n] = 2;
				}
			}
			isSumFeasible.add(cur);
			i++;
		}
		
		// Construct the smallest solution using the memoized table
		BigInteger solution = BigInteger.ZERO;
		int remainder = 0;
		outer:
		for (i--; i >= 0; i--) {  // Pick digits from left (most significant) to right
			int digitMod = Library.powMod(10, i, n);
			for (int j = (i == isSumFeasible.size() - 2 ? 1 : 0); j <= 2; j++) {  // Pick current digit
				int newRem = (remainder - digitMod * j % n + n) % n;
				if (isSumFeasible.get(i)[newRem] > 0) {
					solution = solution.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(j));
					remainder = newRem;
					continue outer;
				}
			}
			throw new AssertionError();
		}
		return solution;
	}
	
}
