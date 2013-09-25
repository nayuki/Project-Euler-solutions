/* 
 * Solution to Project Euler problem 164
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p164 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p164().run());
	}
	
	
	private static final int BASE = 10;
	private static final int DIGITS = 20;
	private static final int CONSECUTIVE = 3;
	private static final int MAX_SUM = 9;
	
	public String run() {
		BigInteger[][] ways = new BigInteger[DIGITS + CONSECUTIVE + 1][Library.pow(BASE, CONSECUTIVE)];
		
		ways[0][0] = BigInteger.ONE;
		for (int prefix = 1; prefix < ways[0].length; prefix++)
			ways[0][prefix] = BigInteger.ZERO;
		
		for (int digits = 1; digits < ways.length; digits++) {
			for (int prefix = 0; prefix < ways[digits].length; prefix++) {
				BigInteger sum = BigInteger.ZERO;
				if (digitSum(prefix) <= MAX_SUM) {
					for (int nextDigit = 0; nextDigit < BASE; nextDigit++)
						sum = sum.add(ways[digits - 1][prefix % Library.pow(BASE, CONSECUTIVE - 1) * BASE + nextDigit]);
				}
				ways[digits][prefix] = sum;
			}
		}
		
		return ways[DIGITS + CONSECUTIVE][0].subtract(ways[DIGITS + CONSECUTIVE - 1][0]).toString();
	}
	
	
	private static int digitSum(int n) {
		int sum = 0;
		for (; n != 0; n /= 10)
			sum += n % 10;
		return sum;
	}
	
}
