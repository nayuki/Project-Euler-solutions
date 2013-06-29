/* 
 * Solution to Project Euler problem 172
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public final class p172 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p172().run());
	}
	
	
	private static final int LENGTH = 18;
	private static final int MAX_COUNT = 3;
	private static final int BASE = 10;
	
	
	private BigInteger totalWays = BigInteger.ZERO;
	
	public String run() {
		partitionAndCount(LENGTH, MAX_COUNT, new ArrayList<Integer>());
		
		BigInteger BASE_BI = BigInteger.valueOf(BASE);
		totalWays = totalWays.multiply(BASE_BI.subtract(BigInteger.ONE));
		if (totalWays.mod(BASE_BI).signum() != 0)
			throw new AssertionError();
		totalWays = totalWays.divide(BASE_BI);
		
		return totalWays.toString();
	}
	
	
	private void partitionAndCount(int sum, int max, List<Integer> terms) {
		if (terms.size() == BASE) {
			if (sum == 0)
				countWays(terms);
		} else {
			for (int i = Math.min(max, sum); i >= 0; i--) {
				terms.add(i);
				partitionAndCount(sum - i, i, terms);
				terms.remove(terms.size() - 1);
			}
		}
	}
	
	
	private void countWays(List<Integer> freqs) {
		int[] histogram = new int[MAX_COUNT + 1];
		for (int x : freqs)
			histogram[x]++;
		
		BigInteger ways = Library.factorial(BASE);
		for (int x : histogram)
			ways = ways.divide(Library.factorial(x));
		
		ways = ways.multiply(Library.factorial(LENGTH));
		for (int x : freqs)
			ways = ways.divide(Library.factorial(x));
		totalWays = totalWays.add(ways);
	}
	
}
