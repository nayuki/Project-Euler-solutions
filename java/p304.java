/* 
 * Solution to Project Euler problem 304
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p304 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p304().run());
	}
	
	
	private static final long BASE = 100000000000000L;
	private static final int SEARCH_RANGE = 10000000;  // Number of candidates starting from BASE to search for primes. Hopefully there are 100 000 primes among here.
	private static final long MODULUS = 1234567891011L;
	private static final BigInteger MODULUS_BI = BigInteger.valueOf(MODULUS);
	
	private boolean[] isComposite;  // isComposite[i] pertains to the number BASE + i
	
	
	public String run() {
		int[] primes = Library.listPrimes((int)Library.sqrt(BASE + SEARCH_RANGE));
		
		// Sieve of Eratosthenes, but starting at BASE
		isComposite = new boolean[SEARCH_RANGE];
		for (int p : primes) {
			for (int i = (int)((BASE + p - 1) / p * p - BASE); i < isComposite.length; i += p)
				isComposite[i] = true;
		}
		
		long sum = 0;
		int p = 0;
		for (int i = 0; i < 100000; i++) {
			p = nextPrime(p);
			sum = (sum + fibonacciMod(BASE + p)) % MODULUS;
		}
		return Long.toString(sum);
	}
	
	
	// Returns p - BASE, where p is the next prime after n + BASE
	private int nextPrime(int n) {
		do {
			n++;
			if (n >= isComposite.length)
				throw new AssertionError("Search range exhausted");
		} while (isComposite[n]);
		return n;
	}
	
	
	private static long fibonacciMod(long n) {
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		for (int i = 63; i >= 0; i--) {
			BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
			BigInteger e = a.pow(2).add(b.pow(2));
			a = d;
			b = e;
			if (((n >>> i) & 1) != 0) {
				BigInteger c = a.add(b);
				a = b;
				b = c;
			}
			a = a.mod(MODULUS_BI);
			b = b.mod(MODULUS_BI);
		}
		return a.longValue();
	}
	
}
