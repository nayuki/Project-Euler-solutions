/* 
 * Solution to Project Euler problem 74
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.HashSet;
import java.util.Set;


public final class p074 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p074().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 6);
	
	public String run() {
		int count = 0;
		for (int i = 0; i < LIMIT; i++) {
			if (getChainLength(i) == 60)
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private static int getChainLength(int n) {
		Set<Integer> seen = new HashSet<Integer>();
		do {
			seen.add(n);
			n = factorialize(n);
		} while (!seen.contains(n));
		return seen.size();
	}
	
	
	// Hard-coded values for factorial(0), factorial(1), ..., factorial(9)
	private static int[] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
	
	private static int factorialize(int n) {
		int sum = 0;
		for (; n != 0; n /= 10)
			sum += FACTORIAL[n % 10];
		return sum;
	}
	
}
