/* 
 * Solution to Project Euler problem 129
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p129 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p129().run());
	}
	
	
	/* 
	 * Let n >= 1 be arbitrary but assume that it is coprime with 10.
	 * We want to find the smallest k such that R(k) = 0 mod n, and we'll show that 1 <= k <= n.
	 * 
	 * Let "the sequence" of n values be (R(1) mod n, R(2) mod n, R(3) mod n, ..., R(n) mod n).
	 * For the sake of contradiction, assume that none of the values in the sequence are 0.
	 * 
	 * Each number in the sequence is an integer in the range [1, n).
	 * The range has n - 1 elements, but there are n elements in the sequence.
	 * Hence by the pigeonhole principle, there exist two distinct indexes
	 * in the sequence where the elements have the same value.
	 * 
	 * Suppose the two distinct indexes (1-based) are i and j.
	 * So the two values in question are R(i) mod n and R(j) mod n.
	 * Suppose WLOG that j > i. Then clearly R(j) - R(i) = 0 mod n,
	 * and so R(j) - R(i) = 1...10...0 = R(j - i) * 10^i = 0 mod n.
	 * 
	 * Since 10 is coprime with n, 10 (and its powers) are invertible modulo n.
	 * Multiply everything in the equation by 10^-i, and we get R(j - i) = 1...1 = 0 mod n.
	 * 
	 * We know 1 <= j - i <= n - 1. Then R(i - j) mod n, which is 0, is in the sequence.
	 * This contradicts our assumption that none of (R(1), R(2), ... R(n)) is 0 mod n.
	 * 
	 * Therefore if we want to find an n whose solution k is such that
	 * k > 1000000, then we need to have n > 1000000.
	 */
	
	private static final int LIMIT = Library.pow(10, 6);
	
	public String run() {
		for (int n = LIMIT; ; n++) {
			if (findLeastDivisibleRepunit(n) > LIMIT)
				return Integer.toString(n);
		}
	}
	
	
	// Returns the smallest k such that R(k) is divisible by n.
	private static int findLeastDivisibleRepunit(int n) {
		if (n % 2 == 0 || n % 5 == 0)
			return 0;
		if (n > Integer.MAX_VALUE / 10)
			throw new IllegalArgumentException("Arithmetic overflow");
		
		int sum = 1;  // Equal to R(k) mod n
		int pow = 1;  // Equal to 10^k mod n
		int k = 1;
		while (sum % n != 0) {
			k++;
			pow = pow * 10 % n;
			sum = (sum + pow) % n;
		}
		return k;
	}
	
}
