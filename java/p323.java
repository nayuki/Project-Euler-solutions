/* 
 * Solution to Project Euler problem 323
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p323 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p323().run());
	}
	
	
	/* 
	 * Define a sequence of random variables:
	 * - Let X0 be 0. (This will become apparent when we define X_n.)
	 * - Let X1 to represent how many trials it takes for a uniformly random bit to see
	 *   the first 1 (a positive integer). For example, if the random bit experiences
	 *   the sequence (1, 0, ...), then X1 = 1 because it took 1 trial. For example,
	 *   the sequence (0, 0, 0, 1, 1, 0, 1, ...) implies X1 = 4, because it took 4 trials.
	 * - For any integer n >= 1, let X_n represent how many trials it takes for a sequence
	 *   of n uniformly random bits to see at least one 1 in each bit. For example, if
	 *   two random bits experience the sequence (01, 01, 10, 00, 11, ...), then X2 = 3
	 *   because it took 3 trials for the running bitwise OR to become 11.
	 * 
	 * Now consider the expected value of each random variable:
	 * - E[X0] is obviously 0.
	 * - E[X1] is the expected value of the geometric distribution with p=0.5, with a
	 *   well-known answer of 2. But we can go through a more elementary derivation
	 *   by considering a single trial of the single bit:
	 *   - It has a half chance of being 0. In this case, the expected value equals the
	 *     probability (half) times {one plus the same unknown expected value} (because we
	 *     performed one trial, ended up in the same state, and the process is memoryless).
	 *   - It has a half chance of being 1. In this case, the expected value equals the
	 *     probability (half) multiplied by one (because we performed one trial).
	 *   Altogether, we have that E[X1] = 1/2 * (1 + E[X1]) + 1/2 * 1 = 1 + E[X1]/2.
	 *   Rearrange to get E[X1]/2 = 1, thus E[X1] = 2 as wanted.
	 * 
	 * - As for E[X2], look at what happens in a single trial of the two bits:
	 *   - If none of the bits are 1, then we have performed one trial and are back to
	 *     the same situation. There is a 1 in 4 chance of this happening (bit string 00).
	 *   - If one of the bits is 1, then we have performed one trial and the remaining calculation
	 *     equals E[X1]. There is a 2 in 4 chance of this happening (bit strings 01 and 10).
	 *   - If both of the bits are 1, then we have performed one trial and the remaining calculation
	 *     equals E[X0] which is 0. There is a 1 in 4 chance of this happening (bit string 11).
	 *   All in all, we have that E[X2] = 1/4 * (1 + E[X2]) + 1/2 * (1 + E[X1]) + 1/4 * (1 + E[X0]).
	 *   Simplifying, we get 3/4 * E[X2] = 1 + 1/2 * E[X1] + 1/4 * E[X0].
	 *   Since we know the values of E[X0] and E[X1], we can solve that E[X2] = 8/3.
	 * - In general for E[X_n], performing one trial results in k (0 <= k <= n) bits
	 *   being set to 1, with probability (n choose k) / 2^n, and the rest of the
	 *   expected value calculation reduces to the value of E[X_{n-k}]. Therefore:
	 *   E[X_n] = sum((n choose k) * (1 + E[X_{n-k}]) / 2^n for k in [0, n]).
	 *   Simplifying further so that E[X_n] only appears on the left side, we get:
	 *   (2^n - 1) * E[X_n] = 2^n + sum((n choose k) * E[X_{n-k}] for k in [0, n-1]).
	 *   E[X_n] = (2^n + sum((n choose k) * E[X_{n-k}] for k in [0, n-1])) / (2^n - 1).
	 * 
	 * Finally, E[X32] is the number that we want as the answer.
	 * Note that this solution algorithm carefully uses entirely integer arithmetic,
	 * even though it is tempting to use floating-point numbers as a shortcut.
	 */
	
	private static final int SIZE = 32;
	private static final int DECIMALS = 10;
	static {
		assert SIZE >= 0;
		assert DECIMALS >= 0;
	}
	
	@SuppressWarnings("unused")
	public String run() {
		// Calculate the answer
		Fraction[] expect = new Fraction[SIZE + 1];
		expect[0] = Fraction.ZERO;
		for (int n = 1; n < expect.length; n++) {
			Fraction sum = Fraction.ZERO;
			for (int k = 0; k < n; k++) {
				BigInteger binom = Library.binomial(n, k);
				Fraction term = new Fraction(expect[k].numerator.multiply(binom), expect[k].denominator);
				sum = sum.add(term);
			}
			BigInteger twoPowN = BigInteger.ONE.shiftLeft(n);
			Fraction temp = sum.add(new Fraction(twoPowN));
			expect[n] = new Fraction(temp.numerator, temp.denominator.multiply(twoPowN.subtract(BigInteger.ONE)));
		}
		
		// Round the fraction properly. This is the pedantically correct version of doing
		// String.format("%.10f", ans.numerator.doubleValue() / ans.denominator.doubleValue())
		Fraction ans = expect[SIZE];
		assert ans.compareTo(Fraction.ZERO) >= 0;
		
		
		Fraction scaled = new Fraction(ans.numerator.multiply(BigInteger.TEN.pow(DECIMALS)), ans.denominator);
		BigInteger[] quotRem = scaled.numerator.divideAndRemainder(scaled.denominator);
		BigInteger whole = quotRem[0];
		Fraction frac = new Fraction(quotRem[1], scaled.denominator);
		assert frac.compareTo(Fraction.ZERO) >= 0;
		assert frac.compareTo(new Fraction(BigInteger.ONE)) < 0;
		Fraction HALF = new Fraction(BigInteger.ONE, BigInteger.valueOf(2));
		if (frac.compareTo(HALF) > 0 || frac.equals(HALF) && whole.testBit(0))
			whole = whole.add(BigInteger.ONE);
		String temp = whole.toString();
		if (DECIMALS == 0)
			return whole.toString();
		while (temp.length() < DECIMALS + 1)
			temp = "0" + temp;
		int index = temp.length() - DECIMALS;
		return String.format("%s.%s", temp.substring(0, index), temp.substring(index));
	}
	
}
