/* 
 * Solution to Project Euler problem 323
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p323 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p323().run());
	}
	
	
	/* 
	 * Suppose that n 32-bit integers have been OR'd together.
	 * For any arbitrary digit:
	 *   The probability that it is 0 is 1/2^n.
	 *   The probability that it is 1 is 1 - 1/2^n.
	 * Thus for the entire number:
	 *   The probability that all digits are 1 is (1 - 1/2^n)^32.
	 *     This is the cumulative distribution function that we want.
	 *   The probability that some digit is 0 is 1 - (1 - 1/2^n)^32.
	 * 
	 * The probability density function is simply pdf(n) = cdf(n) - cdf(n-1).
	 * So the expected value of the index where the number becomes all 1's is
	 * sum(n * pdf(n) for n = 0 to infinity).
	 */
	public String run() {
		// Computes an approximate answer using floating-point, not guaranteed to be correct.
		// However, the Mathematica version of the solution is exact.
		double sum = 0;
		for (int n = 1; ; n++) {
			double p = cdf(n) - cdf(n - 1);
			if (p < 1e-20)  // Truncate the series by ignoring insignificant contributions to the sum
				break;
			sum += n * p;
		}
		return String.format("%.10f", sum);
	}
	
	
	private static double cdf(int n) {
		if (n >= 0)
			return Math.pow(1 - Math.pow(2, -n), 32);
		else
			return 0;
	}
	
}
