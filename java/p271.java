/* 
 * Solution to Project Euler problem 271
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public final class p271 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p271().run());
	}
	
	
	@SuppressWarnings("unchecked")
	public String run() {
		// Note: 13082761331670030 = product of lowest 14 primes
		// Find solutions to x^3 = 1 mod p, for each prime factor p
		int[] FACTORS = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
		List<Integer>[] factorSolutions = new List[FACTORS.length];
		for (int i = 0; i < FACTORS.length; i++) {
			List<Integer> sols = new ArrayList<>();
			for (int j = 0; j < FACTORS[i]; j++) {
				if (Library.powMod(j, 3, FACTORS[i]) == 1)
					sols.add(j);
			}
			factorSolutions[i] = sols;
		}
		
		BigInteger sum = buildAndSumSolutions(FACTORS, factorSolutions, 0, BigInteger.ZERO, BigInteger.ONE);
		return sum.subtract(BigInteger.ONE).toString();  // The recursive algorithm generates all solutions, but the problem statement excludes 1
	}
	
	
	// Try all possibilities recursively
	private BigInteger buildAndSumSolutions(int[] FACTORS, List<Integer>[] factorSols, int i, BigInteger x, BigInteger m) {
		if (i == FACTORS.length)
			return x;
		else {
			BigInteger result = BigInteger.ZERO;
			for (int sol : factorSols[i]) {
				BigInteger factor = BigInteger.valueOf(FACTORS[i]);
				BigInteger newx = chineseRemainderTheorem(x, m, BigInteger.valueOf(sol), factor);
				BigInteger temp = buildAndSumSolutions(FACTORS, factorSols, i + 1, newx, m.multiply(factor));
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
