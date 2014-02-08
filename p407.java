/* 
 * Solution to Project Euler problem 407
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.List;


public final class p407 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p407().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 7);
	
	
	/* 
	 * If a^2 = a mod n, then this is also true for any m that divides n.
	 * Let's focus on the moduli that are prime powers, p^k.
	 * 
	 * Claim: The only solutions of a^2 = a mod p^k are a = 0, 1 mod p^k.
	 * Proof:
	 *   First note that a = 0 mod p^k is always a solution. Now consider the case of 0 < a < p^k.
	 *   Let a = b * p^j, where 0 < b < p^j and b is coprime with p (thus j is as large as possible).
	 *   Then (b p^j)^2 = b p^j mod p^k, expanding to b^2 p^2j = b p^j mod p^k.
	 *   Divide all of the equation (including the modulus) by p^j, giving b^2 p^j = b mod p^(k-j).
	 *   b is coprime with p (and therefore p^(k-j)), so b^-1 exists.
	 *   Multiply both sides by b^-2 to get b^-1 = p^j mod p^(k-j).
	 *   b is coprime with p, so b is not a power of p unless j = 0, i.e. p^j = 1 = b.
	 *   So when a != 0, a = 1 is the only solution.
	 * 
	 * If we factor n as a product of prime powers, i.e. n = p0^k0 * p1^k1 * ... where
	 * all the p's are distinct (and thus all the k's are as large as possible), then we have
	 * a system of congruences {a = 0,1 mod p0^k0; a = 0,1 mod p1^k1; ...}.
	 * Using the Chinese remainder theorem, we can solve these congruences to obtain the
	 * 2^N distinct solutions (where N is the number of distinct prime factors of n).
	 * The largest solution among these is what we want for the M() function.
	 */
	public String run() {
		int[] smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT);
		
		// Maximum size of set of prime factors where the product of the set <= LIMIT.
		// This is important because the number of solutions for n is 2^N,
		// where N is the number of distinct prime factors of n.
		int maxNumPrimeFactors = 0;
		for (int i = 2, prod = 1; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == i) {  // i is prime
				if (LIMIT / prod < i)
					break;
				prod *= i;
				maxNumPrimeFactors++;
			}
		}
		
		long sum = 0;
		// Temporary arrays
		int[] solns    = new int[1 << maxNumPrimeFactors];
		int[] newsolns = new int[1 << maxNumPrimeFactors];
		for (int i = 1; i <= LIMIT; i++) {
			// Compute factorization as coprime prime powers. e.g. 360 = {2^3, 3^2, 5^1}
			List<Integer> factorization = new ArrayList<Integer>();
			for (int j = i; j != 1; ) {
				int p = smallestPrimeFactor[j];
				int q = 1;
				do {
					j /= p;
					q *= p;
				} while (j % p == 0);
				factorization.add(q);
			}
			
			solns[0] = 0;
			int solnslen = 1;
			int modulus = 1;
			for (int q : factorization) {
				// Use Chinese remainder theorem; cache parts of it
				int recip = Library.reciprocalMod(q % modulus, modulus);
				int newmod = q * modulus;
				
				int newsolnslen = 0;
				for (int j = 0; j < solnslen; j++) {
					newsolns[newsolnslen++] = ((0 + (int)((long)(solns[j] - 0 + modulus) * recip % modulus) * q) % newmod);
					newsolns[newsolnslen++] = ((1 + (int)((long)(solns[j] - 1 + modulus) * recip % modulus) * q) % newmod);
				}
				
				solnslen = newsolnslen;
				modulus = newmod;
				
				// Flip buffers
				int[] temp = solns;
				solns = newsolns;
				newsolns = temp;
			}
			
			int max = 0;
			for (int j = 0; j < solnslen; j++)
				max = Math.max(solns[j], max);
			sum += max;
		}
		return Long.toString(sum);
	}
	
}
