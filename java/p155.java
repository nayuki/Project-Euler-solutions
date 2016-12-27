/* 
 * Solution to Project Euler problem 155
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public final class p155 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p155().run());
	}
	
	
	private static final int SIZE = 18;
	
	// Warning: Running this solution requires about 600 MiB of memory
	public String run() {
		@SuppressWarnings("unchecked")
		// possible[i] holds all the possible capacitance values of a series/parallel
		// capacitor network that uses exactly i capacitors of 60 uF each
		Set<FastFraction>[] possible = new Set[SIZE + 1];
		Set<FastFraction> all = new HashSet<>();  // Union of every possible[i]
		possible[0] = new HashSet<>();
		possible[1] = new HashSet<>();
		possible[1].add(new FastFraction(60, 1));
		all.addAll(possible[1]);
		
		for (int i = 2; i <= SIZE; i++) {
			Set<FastFraction> poss = new HashSet<>();
			for (int j = 1; j <= i - j; j++) {
				for (FastFraction a : possible[j]) {
					for (FastFraction b : possible[i - j]) {
						poss.add(a.add(b));  // Parallel
						poss.add(a.reciprocalAdd(b));  // Series
					}
				}
			}
			possible[i] = poss;
			all.addAll(poss);
		}
		return Integer.toString(all.size());
	}
	
	
	
	// A fraction that uses int for storage and long for computation, but switches to BigInteger when necessary.
	private static final class FastFraction {
		
		public final int numerator;
		public final int denominator;
		public final Fraction bigFraction;
		
		
		public FastFraction(long num, long den) {
			if (den <= 0)
				throw new IllegalArgumentException();
			int n = (int)num;
			int d = (int)den;
			if (n == num && d == den) {
				int gcd = Library.gcd(n, d);
				if (gcd > 1) {
					n /= gcd;
					d /= gcd;
				}
				numerator = n;
				denominator = d;
				bigFraction = null;
			} else {
				FastFraction temp = new FastFraction(new Fraction(BigInteger.valueOf(num), BigInteger.valueOf(den)));
				numerator = temp.numerator;
				denominator = temp.denominator;
				bigFraction = temp.bigFraction;
			}
		}
		
		
		public FastFraction(Fraction frac) {
			if (frac.numerator.bitLength() <= 31 && frac.denominator.bitLength() <= 31) {
				numerator = frac.numerator.intValue();
				denominator = frac.denominator.intValue();
				bigFraction = null;
			} else {
				numerator = 0;
				denominator = 0;
				bigFraction = frac;
			}
		}
		
		
		public Fraction toFraction() {
			if (bigFraction == null)
				return new Fraction(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
			else
				return bigFraction;
		}
		
		
		public FastFraction add(FastFraction other) {
			if (bigFraction == null && other.bigFraction == null) {
				long num = (long)numerator * other.denominator + (long)other.numerator * denominator;
				long den = (long)denominator * other.denominator;
				return new FastFraction(num, den);
			} else
				return new FastFraction(toFraction().add(other.toFraction()));
		}
		
		
		// Returns 1 / (1/this + 1/other), also equal to (this * other) / (this + other).
		public FastFraction reciprocalAdd(FastFraction other) {
			if (bigFraction == null && other.bigFraction == null) {
				long num = (long)numerator * other.numerator;
				long den = (long)numerator * other.denominator + (long)other.numerator * denominator;
				return new FastFraction(num, den);
			} else {
				Fraction x = this.toFraction();
				Fraction y = other.toFraction();
				return new FastFraction(x.multiply(y).divide(x.add(y)));
			}
		}
		
		
		public boolean equals(Object obj) {
			if (!(obj instanceof FastFraction))
				return false;
			FastFraction other = (FastFraction)obj;
			if (bigFraction == null && other.bigFraction == null)
				return numerator == other.numerator && denominator == other.denominator;
			else
				return toFraction().equals(other.toFraction());
		}
		
		
		public int hashCode() {
			if (bigFraction == null)
				return numerator + denominator * 1204805;  // Some arbitrary constant to spread around the bits
			else
				return bigFraction.hashCode();
		}
		
	}
	
}
