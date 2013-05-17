/* 
 * Solution to Project Euler problem 66
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class p066 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p066().run());
	}
	
	
	/* 
	 * Based on this insane theorem: Suppose D > 1 is an integer, non-perfect-square.
	 * 
	 * Express sqrt(D) as the continued fraction (a0, a1, ..., a_{n-1}, (b0, b1, ..., b_{m-1})),
	 * where the sequence of b's is the periodic part.
	 * 
	 * Let p/q (in lowest terms) = (a0, a1, ..., a_{n-1}, b0, b1, ..., b_{m-2}).
	 * (This is a truncation of the continued fraction with only one period minus the last term.)
	 * 
	 * Then the minimum solution (x, y) for Pell's equation is given by:
	 * - (p, q) if m is even
	 * - (p^2 + D q^2, 2pq) if m is odd
	 */
	public String run() {
		int minN = -1;
		BigInteger maxX = BigInteger.ZERO;
		for (int n = 2; n <= 1000; n++) {
			if (Library.isSquare(n))
				continue;
			BigInteger x = smallestSolutionX(n);
			if (x.compareTo(maxX) > 0) {
				minN = n;
				maxX = x;
			}
		}
		return Integer.toString(minN);
	}
	
	
	// Returns the smallest x such that x > 0 and there exists some y such that x^2 - n y^2 = 1.
	// Requires n to not be a perfect square.
	private static BigInteger smallestSolutionX(int n) {
		List<BigInteger>[] contFrac = sqrtToContinuedFraction(n);
		
		List<BigInteger> temp = new ArrayList<BigInteger>();
		temp.addAll(contFrac[0]);
		temp.addAll(contFrac[1].subList(0, contFrac[1].size() - 1));
		
		Fraction val = new Fraction(temp.get(temp.size() - 1), BigInteger.ONE);
		for (int i = temp.size() - 2; i >= 0; i--)
			val = new Fraction(val.denominator, val.numerator).add(new Fraction(temp.get(i), BigInteger.ONE));
		
		if (contFrac[1].size() % 2 == 0)
			return val.numerator;
		else
			return val.numerator.pow(2).add(val.denominator.pow(2).multiply(BigInteger.valueOf(n)));
	}
	
	
	// Returns the periodic continued fraction of sqrt(n). Requires n to not be a perfect square.
	// result[0] is the minimal non-periodic prefix, and result[1] is the minimal periodic tail.
	@SuppressWarnings("unchecked")
	private static List<BigInteger>[] sqrtToContinuedFraction(int n) {
		List<BigInteger> terms = new ArrayList<BigInteger>();
		Map<QuadraticSurd,Integer> seen = new HashMap<QuadraticSurd,Integer>();
		QuadraticSurd val = new QuadraticSurd(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.valueOf(n));
		do {
			seen.put(val, seen.size());
			BigInteger flr = val.floor();
			terms.add(flr);
			val = val.subtract(new QuadraticSurd(flr, BigInteger.ZERO, BigInteger.ONE, val.d)).reciprocal();
		} while (!seen.containsKey(val));
		return new List[]{terms.subList(0, seen.get(val)), terms.subList(seen.get(val), terms.size())};
	}
	
	
	
	// Represents (a + b * sqrt(d)) / c. d must not be a perfect square.
	private static class QuadraticSurd {
		
		public final BigInteger a, b, c, d;
		
		
		public QuadraticSurd(BigInteger a, BigInteger b, BigInteger c, BigInteger d) {
			if (c.signum() == 0)
				throw new IllegalArgumentException();
			
			// Simplify
			if (c.signum() == -1) {
				a = a.negate();
				b = b.negate();
				c = c.negate();
			}
			BigInteger gcd = a.gcd(b).gcd(c);
			if (!gcd.equals(BigInteger.ONE)) {
				a = a.divide(gcd);
				b = b.divide(gcd);
				c = c.divide(gcd);
			}
			
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
		
		
		public QuadraticSurd subtract(QuadraticSurd other) {
			if (!d.equals(other.d))
				throw new IllegalArgumentException();
			return new QuadraticSurd(a.multiply(other.c).subtract(other.a.multiply(c)), b.multiply(other.c).subtract(other.b.multiply(c)), c.multiply(other.c), d);
		}
		
		
		public QuadraticSurd reciprocal() {
			return new QuadraticSurd(a.multiply(c).negate(), b.multiply(c), b.multiply(b).multiply(d).subtract(a.multiply(a)), d);
		}
		
		
		public BigInteger floor() {
			BigInteger temp = sqrt(b.multiply(b).multiply(d));
			if (b.signum() == -1)
				temp = temp.add(BigInteger.ONE).negate();
			temp = temp.add(a);
			if (temp.signum() == -1)
				temp = temp.subtract(c.subtract(BigInteger.ONE));
			return temp.divide(c);
		}
		
		
		public boolean equals(Object obj) {
			if (!(obj instanceof QuadraticSurd))
				return false;
			else {
				QuadraticSurd other = (QuadraticSurd)obj;
				return a.equals(other.a) && b.equals(other.b) && c.equals(other.c) && d.equals(other.d);
			}
		}
		
		
		public int hashCode() {
			return a.hashCode() + b.hashCode() + c.hashCode() + d.hashCode();
		}
		
		
		public String toString() {
			return String.format("(%d + %d*sqrt(%d)) / %d", a, b, d, c);
		}
		
		
		private static BigInteger sqrt(BigInteger x) {
			BigInteger y = BigInteger.ZERO;
			for (int i = (x.bitLength() - 1) / 2; i >= 0; i--) {
				y = y.setBit(i);
				if (y.multiply(y).compareTo(x) > 0)
					y = y.clearBit(i);
			}
			return y;
		}
		
	}
	
}
