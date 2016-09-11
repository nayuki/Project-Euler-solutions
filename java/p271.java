/* 
 * Solution to Project Euler problem 271
 * by Project Nayuki
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
	
	
	private static final int[] FACTORS = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
	
	private List<Set<Integer>> factorSolutions = new ArrayList<>();
	
	
	public String run() {
		// Note: 13082761331670030 = product of lowest 14 primes
		// Find solutions to x^3 = 1 mod p, for each prime factor p
		for (int fact : FACTORS) {
			Set<Integer> sols = new HashSet<>();
			for (int j = 1; j < fact; j++) {
				if (Library.powMod(j, 3, fact) == 1)
					sols.add(j);
			}
			factorSolutions.add(sols);
		}
		
		BigInteger sum = buildAndSumSolutions(0, BigInteger.ZERO, BigInteger.ONE);
		return sum.subtract(BigInteger.ONE).toString();  // The recursive algorithm generates all solutions, but the problem statement excludes 1
	}
	
	
	// Try all possibilities recursively
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
	
	
	/*
	 * Given that for an unknown x, x = a mod p and x = b mod q (where p and q are coprime),
	 * returns an integer 0 <= x < p*q satisfying these congruences.
	 */
	private static BigInteger chineseRemainderTheorem(BigInteger a, BigInteger p, BigInteger b, BigInteger q) {
		// (a + (b - a) * reciprocalMod(p, q) * p) mod (p * q)
		return a.add(b.subtract(a).multiply(p.modInverse(q)).multiply(p)).mod(p.multiply(q));
	}
	
}
