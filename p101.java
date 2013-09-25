/* 
 * Solution to Project Euler problem 101
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p101 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p101().run());
	}
	
	
	/* 
	 * The generating function u(n) is a polynomial of degree 10.
	 * OP(k, n) is a polynomial of degree at most k-1, which can be obtained
	 * by the Lagrange interpolating polynomial (or other methods).
	 * Any polynomial P(n) of degree k has at most k roots (i.e. points where P(n) = 0).
	 * The zero polynomial Z(n) = 0 has negative infinite degree, and has roots everywhere.
	 * Now, let E(n) = u(n) - OP(k, n), which is also a polynomial.
	 * 
	 * If k <= 10, then OP(k, n) has degree less than k <= 10, so E(n) has degree 10. So E(n) has at most 10 roots.
	 * By construction, OP(k, n) = u(n) for n = 1, 2, ..., k, thus E(n) already has k roots at {1, 2, ..., k}.
	 * E(n) has at most 10 - k roots remaining, hence among the 11 - k values {k+1, k+2, ..., 11},
	 * there must be an n where E(n) != 0 (i.e. an incorrect term where OP(k, n) != u(n)).
	 * 
	 * If k > 10, E(n) has k roots at {1, 2, ..., k}, and possibly others.
	 * So either E(n) has degree at least k, or it's the zero polynomial.
	 * Now, u(n) has degree 10 and OP(k, n) has degree at most k-1,
	 * so their difference E(n) has at most degree max(10, k-1) = k-1.
	 * This means E(n) does not have degree k, so it is the zero polynomial.
	 * Hence u(n) = OP(k, n), and there are no incorrect terms.
	 * 
	 * In conclusion, BOPs exist for and only for 1 <= k <= 10. For each k in that range,
	 * the first incorrect term (FIT) of OP(k, n) exists for some n in {k+1, k+2, ..., 11}.
	 */
	
	private static final int DEGREE = 10;
	
	public String run() {
		Fraction sum = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		for (int k = 1; k <= DEGREE; k++) {
			for (int n = k + 1; ; n++) {
				if (n == DEGREE + 2)
					throw new AssertionError();
				
				Fraction reference = new Fraction(generatingFunction(n), BigInteger.ONE);
				Fraction term = optimumPolynomial(k, n);
				if (!term.equals(reference)) {
					sum = sum.add(term);
					break;
				}
			}
		}
		if (sum.denominator.equals(BigInteger.ONE))
			return sum.numerator.toString();
		else
			return sum.toString();
	}
	
	
	private static Fraction optimumPolynomial(int k, int n) {
		// Lagrange interpolation
		Fraction sum = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		for (int i = 1; i <= k; i++) {
			Fraction product = new Fraction(generatingFunction(i), BigInteger.ONE);
			for (int j = 1; j <= k; j++) {
				if (j != i)
					product = product.multiply(new Fraction(BigInteger.valueOf(n - j), BigInteger.valueOf(i - j)));
			}
			sum = sum.add(product);
		}
		return sum;
	}
	
	
	private static BigInteger generatingFunction(int n) {
		BigInteger sum = BigInteger.ZERO;
		BigInteger biN = BigInteger.valueOf(-n);
		for (int i = 0; i <= DEGREE; i++)
			sum = sum.add(biN.pow(i));
		return sum;
	}
	
}
