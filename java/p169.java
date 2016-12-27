/* 
 * Solution to Project Euler problem 169
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class p169 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p169().run());
	}
	
	
	private static final BigInteger NUMBER = BigInteger.TEN.pow(25);
	
	public String run() {
		return countWays(NUMBER, NUMBER.bitLength() - 1, 2).toString();
	}
	
	
	/* 
	 * ways(n, i, j) is the number of ways that the number n can be expressed as
	 * an unordered sum of powers of 2 such that all these conditions are true:
	 * - The highest possible power is 2^i
	 * - The 2^i term is used between 0 and j times
	 * - All lower powers of 2 are used no more than 2 times
	 */
	
	// Memoization
	private Map<List<BigInteger>,BigInteger> ways = new HashMap<>();
	
	private BigInteger countWays(BigInteger number, int exponent, int repetitions) {
		List<BigInteger> key = Arrays.asList(number, BigInteger.valueOf(exponent), BigInteger.valueOf(repetitions));
		if (ways.containsKey(key))
			return ways.get(key);
		
		BigInteger result;
		if (exponent < 0)
			result = number.equals(BigInteger.ZERO) ? BigInteger.ONE : BigInteger.ZERO;
		else {
			result = countWays(number, exponent - 1, 2);
			BigInteger pow = BigInteger.ONE.shiftLeft(exponent);
			BigInteger upper = pow.multiply(BigInteger.valueOf(repetitions + 2));
			if (repetitions > 0 && pow.compareTo(number) <= 0 && number.compareTo(upper) < 0)
				result = result.add(countWays(number.subtract(pow), exponent, repetitions - 1));
		}
		ways.put(key, result);
		return result;
	}
	
}
