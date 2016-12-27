/* 
 * Solution to Project Euler problem 271
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class p271 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p271().run());
	}
	
	
	/* 
	 * First we observe that the modulus 13082761331670030 can be factorized as
	 * 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37 * 41 * 43,
	 * which happens to be the product of the first 14 prime numbers.
	 * 
	 * Due to the laws of modular arithmetic, if x^3 = 1 mod (n*k),
	 * then it is also true that x^3 = 1 mod n and x^3 = 1 mod k.
	 * Hence the problem statement is equivalent to finding all x in the range (1, n) such that
	 * (x^3 = 1 mod 2) and (x^3 = 1 mod 3) and (x^3 = 1 mod 5) and ... and (x^3 = 1 mod 43).
	 * 
	 * When the main congruence of x^3 = 1 mod 13082761331670030 is broken up into a set of sub-congruences,
	 * because the sub-congruences have tiny moduli they are easy to solve by brute force. That is to say,
	 * for x^3 = 1 mod n, we can find all the solutions for x in the range [1, k) just by testing all possibilities.
	 * For example:
	 * - x^3 = 1 mod  2 has the solutions (for 1 <= x <  2) of {1}.
	 * - x^3 = 1 mod  7 has the solutions (for 1 <= x <  7) of {1, 2, 4}.
	 * - x^3 = 1 mod 11 has the solutions (for 1 <= x < 11) of {1}.
	 * - x^3 = 1 mod 43 has the solutions (for 1 <= x < 43) of {1, 6, 36}.
	 * 
	 * By the Chinese remainder theorem, the set of solutions for x^3 = 1 mod (n*k) has a bijection with
	 * the set of ordered pairs of solutions for (x^3 = 1 mod n, x^3 = 1 mod k). Furthermore, if we know
	 * all solutions of (x^3 = 1 mod n) and (x^3 = 1 mod k), then we can apply the CRT on these numbers to
	 * compute all the solutions of x^3 = 1 mod (n*k). Using this fact, we build up the full set of solutions
	 * from the smallest factor to the largest factor.
	 */
	
	private static final int[] FACTORS = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
	
	private List<Set<Integer>> factorSolutions = new ArrayList<>();
	
	
	public String run() {
		for (int fact : FACTORS) {
			Set<Integer> sols = new HashSet<>();
			for (int j = 1; j < fact; j++) {
				if (Library.powMod(j, 3, fact) == 1)
					sols.add(j);
			}
			factorSolutions.add(sols);
		}
		
		BigInteger sum = buildAndSumSolutions(0, BigInteger.ZERO, BigInteger.ONE);
		// Note: The recursive algorithm generates all solutions, but the problem statement excludes 1
		return sum.subtract(BigInteger.ONE).toString();
	}
	
	
	private BigInteger buildAndSumSolutions(int factorIndex, BigInteger x, BigInteger m) {
		if (factorIndex == FACTORS.length)
			return x;
		else {
			BigInteger result = BigInteger.ZERO;
			BigInteger fact = BigInteger.valueOf(FACTORS[factorIndex]);
			for (int sol : factorSolutions.get(factorIndex)) {
				BigInteger newx = chineseRemainderTheorem(x, m, BigInteger.valueOf(sol), fact);
				BigInteger temp = buildAndSumSolutions(factorIndex + 1, newx, m.multiply(fact));
				result = result.add(temp);
			}
			return result;
		}
	}
	
	
	// Assuming that p and q are coprime, 0 <= a < p, and 0 <= b < q, this returns the unique
	// integer x in the range [0, p*q) such that x satisfies (x = a mod p) and (x = b mod q).
	private static BigInteger chineseRemainderTheorem(BigInteger a, BigInteger p, BigInteger b, BigInteger q) {
		// (a + (b - a) * reciprocalMod(p, q) * p) mod (p * q)
		return a.add(b.subtract(a).multiply(p.modInverse(q)).multiply(p)).mod(p.multiply(q));
	}
	
}
