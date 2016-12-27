/* 
 * Solution to Project Euler problem 5
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p005 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p005().run());
	}
	
	
	/* 
	 * The smallest number n that is evenly divisible by every number in a set {k1, k2, ..., k_m}
	 * is also known as the lowest common multiple (LCM) of the set of numbers.
	 * The LCM of two natural numbers x and y is given by LCM(x, y) = x * y / GCD(x, y).
	 * When LCM is applied to a collection of numbers, it is commutative, associative, and idempotent.
	 * Hence LCM(k1, k2, ..., k_m) = LCM(...(LCM(LCM(k1, k2), k3)...), k_m).
	 */
	public String run() {
		BigInteger allLcm = BigInteger.ONE;
		for (int i = 1; i <= 20; i++)
			allLcm = lcm(BigInteger.valueOf(i), allLcm);
		return allLcm.toString();
	}
	
	
	private static BigInteger lcm(BigInteger x, BigInteger y) {
		return x.divide(x.gcd(y)).multiply(y);
	}
	
}
