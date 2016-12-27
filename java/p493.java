/* 
 * Solution to Project Euler problem 493
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;


public final class p493 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p493().run());
	}
	
	
	private static final int NUM_COLORS = 7;
	private static final int BALLS_PER_COLOR = 10;
	private static final int NUM_PICKED = 20;
	
	
	private BigInteger numerator = BigInteger.ZERO;
	
	public String run() {
		explore(NUM_PICKED, BALLS_PER_COLOR, new Stack<Integer>());
		BigInteger denominator = Library.binomial(NUM_COLORS * BALLS_PER_COLOR, NUM_PICKED);
		BigDecimal num = new BigDecimal(numerator);
		BigDecimal den = new BigDecimal(denominator);
		return num.divide(den, 9, RoundingMode.HALF_EVEN).toString();
	}
	
	
	private void explore(int remain, int limit, Stack<Integer> history) {
		if (remain == 0) {
			int[] hist = new int[NUM_COLORS];
			for (int i = 0; i < history.size(); i++)
				hist[i] = history.get(i);
			
			int[] histogram = new int[BALLS_PER_COLOR + 1];
			for (int x : hist)
				histogram[x]++;
			
			BigInteger count = Library.factorial(NUM_COLORS);
			for (int x : histogram)
				count = divideExactly(count, Library.factorial(x));
			
			for (int x : hist)
				count = count.multiply(Library.binomial(BALLS_PER_COLOR, x));
			
			int distinctColors = history.size();
			numerator = numerator.add(count.multiply(BigInteger.valueOf(distinctColors)));
			
		} else if (history.size() < NUM_COLORS) {
			for (int i = Math.min(limit, remain); i > 0; i--){
				history.push(i);
				explore(remain - i, i, history);
				history.pop();
			}
		}
	}
	
	
	private static BigInteger divideExactly(BigInteger x, BigInteger y) {
		BigInteger[] temp = x.divideAndRemainder(y);
		if (temp[1].signum() != 0)
			throw new IllegalArgumentException("Not divisible");
		return temp[0];
	}
	
}
