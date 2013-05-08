/* 
 * Solution to Project Euler problem 271
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
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
		int[] factors = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
		List<Integer>[] factorSolutions = new List[factors.length];
		for (int i = 0; i < factors.length; i++) {
			List<Integer> sols = new ArrayList<Integer>();
			for (int j = 0; j < factors[i]; j++) {
				if (Library.powMod(j, 3, factors[i]) == 1)
					sols.add(j);
			}
			factorSolutions[i] = sols;
		}
		
		buildAndAddSolutions(factors, factorSolutions, 0, BigInteger.ZERO, BigInteger.ONE);
		return sum.toString();
	}
	
	
	// The recursive algorithm generates all solutions, but the problem statement excludes 1.
	private BigInteger sum = BigInteger.valueOf(-1);
	
	
	// Try all possibilities recursively
	private void buildAndAddSolutions(int[] factors, List<Integer>[] factorSols, int i, BigInteger x, BigInteger m) {
		if (i == factors.length)
			sum = sum.add(x);
		else {
			for (int sol : factorSols[i]) {
				BigInteger factor = BigInteger.valueOf(factors[i]);
				buildAndAddSolutions(factors, factorSols, i + 1, chineseRemainderTheorem(x, m, BigInteger.valueOf(sol), factor), m.multiply(factor));
			}
		}
	}
	
	
	/*
	 * Given that for an unknown x, x = a mod p and x = b mod q (where p and q are coprime),
	 * returns an integer 0 <= x < pq satisfying these congruences.
	 */
	private static BigInteger chineseRemainderTheorem(BigInteger a, BigInteger p, BigInteger b, BigInteger q) {
		return a.add(b.subtract(a).multiply(p.modInverse(q)).multiply(p)).mod(p.multiply(q));
	}
	
}
