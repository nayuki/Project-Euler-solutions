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
		int[] smallestPrimeFactor = new int[LIMIT + 1];
		for (int i = 2; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == 0) {
				smallestPrimeFactor[i] = i;
				if ((long)i * i <= LIMIT) {
					for (int j = i * i; j <= LIMIT; j += i) {
						if (smallestPrimeFactor[j] == 0)
							smallestPrimeFactor[j] = i;
					}
				}
			}
		}
		
		long sum = 0;
		for (int i = 1; i <= LIMIT; i++) {
			// Factorization as coprime prime powers
			// e.g. 360 = {2^3, 3^2, 5^1}
			List<Integer> factorization = new ArrayList<Integer>();
			int j = i;
			while (j != 1) {
				int p = smallestPrimeFactor[j];
				int q = 1;
				do {
					j /= p;
					q *= p;
				} while (j % p == 0);
				factorization.add(q);
			}
			sum += maxSolution(factorization, 0, 0, 1);
		}
		
		return Long.toString(sum);
	}
	
	
	private static int maxSolution(List<Integer> factorization, int i, int x, int mod) {
		if (i == factorization.size())
			return x;
		else {
			int factor = factorization.get(i);
			return Math.max(
					maxSolution(factorization, i + 1, chineseRemainderTheorem(0, factor, x, mod), factor * mod),
					maxSolution(factorization, i + 1, chineseRemainderTheorem(1, factor, x, mod), factor * mod));
		}
	}
	
	
	private static int chineseRemainderTheorem(int a, int p, int b, int q) {
		return (a + (int)((long)(b - a + q) * reciprocalMod(p % q, q) % q) * p) % (p * q);
	}
	
	
	private static int reciprocalMod(int x, int m) {
		if (m < 0 || x < 0 || x >= m)
			throw new IllegalArgumentException();
		
		// Based on a simplification of the extended Euclidean algorithm
		int y = x;
		x = m;
		int a = 0;
		int b = 1;
		while (y != 0) {
			int z = x % y;
			int c = a - x / y * b;
			x = y;
			y = z;
			a = b;
			b = c;
		}
		if (x == 1)
			return (a + m) % m;
		else
			throw new IllegalArgumentException("Reciprocal does not exist");
	}
	
}
