/* 
 * Solution to Project Euler problem 119
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;


public final class p119 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p119().run());
	}
	
	
	/* 
	 * Candidates have the form n^k, where n >= 2, k >= 2, n^k >= 10, and isDigitSumPower(n^k) == true.
	 * We also impose n^k < limit. If there are at least 30 candidates under 'limit',
	 * then the 30th smallest candidate is the answer. Otherwise we raise the limit and search again.
	 * 
	 * We only need to try the exponents k until 2^k exceeds the limit.
	 * We only need to try the bases n until the power of the digit sum is too small to match n^k.
	 * The power of the digit sum is digitSum(n^k)^k, which is at most (9 * digitLength(n^k))^k.
	 */
	
	private static final int INDEX = 30;  // 1-based
	
	public String run() {
		for (BigInteger limit = BigInteger.ONE; ; limit = limit.shiftLeft(8)) {
			SortedSet<BigInteger> candidates = new TreeSet<>();
			for (int k = 2; BigInteger.valueOf(1).shiftLeft(k).compareTo(limit) < 0; k++) {
				for (int n = 2; ; n++) {
					BigInteger pow = BigInteger.valueOf(n).pow(k);
					if (pow.compareTo(limit) >= 0 && pow.toString().length() * 9 < n)
						break;
					if (pow.compareTo(BigInteger.TEN) >= 0 && isDigitSumPower(pow))
						candidates.add(pow);
				}
			}
			if (candidates.size() >= INDEX)
				return new ArrayList<>(candidates).get(INDEX - 1).toString();
		}
	}
	
	
	// Returns true iff there exists k >= 2 such that x = digitSum(x)^k.
	private static boolean isDigitSumPower(BigInteger x) {
		int digitSum = digitSum(x);
		if (digitSum == 1)  // Powers of 10 are never a power of 1
			return false;
		
		BigInteger base = BigInteger.valueOf(digitSum);
		BigInteger pow = base;
		while (pow.compareTo(x) < 0)
			pow = pow.multiply(base);
		return pow.equals(x);
	}
	
	
	private static int digitSum(BigInteger x) {
		if (x.signum() < 1)
			throw new IllegalArgumentException("Only for positive integers");
		int sum = 0;
		for (char c : x.toString().toCharArray())
			sum += c - '0';
		return sum;
	}
	
}
