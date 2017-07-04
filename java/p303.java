/* 
 * Solution to Project Euler problem 303
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public final class p303 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p303().run());
	}
	
	
	public String run() {
		BigInteger sum = BigInteger.ZERO;
		for (int n = 1; n <= 10000; n++)
			sum = sum.add(findMinimumMultiple(n).divide(BigInteger.valueOf(n)));
		return sum.toString();
	}
	
	
	/* 
	 * This function computes and returns the smallest positive multiple of n such that the result
	 * uses only the digits 0, 1, 2 in base 10. For example, fmm(2) = 2, fmm(3) = 12, fmm(5) = 10.
	 * 
	 * As an overview, the algorithm has two phases:
	 * 0. Determine whether a k-digit solution is possible, for increasing values of k.
	 * 1. Knowing that a k-digit solution exists, construct the minimum solution.
	 * 
	 * Let n >= 1 be an arbitrary integer that will remain constant for the rest of the explanation.
	 * 
	 * When we look at the set of all k-digit numbers using only the digits {0, 1, 2}
	 * (with possible leading zeros), each number will have a particular remainder modulo n.
	 * For example, the set of 3-digit numbers is {000, 001, 002, 010, ..., 120, ..., 221, 222} (having 3^3 = 27 elements).
	 * If one of these numbers is congruent to 0 mod n, then a solution to the original problem exists.
	 * If not, then we prepend the digits 0, 1, 2 to all the numbers to get the set of all 4-digit numbers.
	 * 
	 * The size of the set of k-digit numbers grows exponentially with the length k, but we can avoid constructing and
	 * working with the explicit set of numbers. Instead, we only need to keep track of whether each remainder modulo n has
	 * a number that generates it or not. But we also need to exclude 0 as a solution, even though it is a multiple of n.
	 * 
	 * For 0-digit numbers, the only possible remainder is 0. All other remainders modulo n are impossible.
	 * For 1-digit numbers, we look at all the possible 0-digit number remainders. If a remainder m is possible, then:
	 * - By prepending the digit 0, a remainder of (m + 0*1 mod n) is possible for 1-digit numbers.
	 * - By prepending the digit 1, a remainder of (m + 1*1 mod n) is possible for 1-digit numbers.
	 * - By prepending the digit 2, a remainder of (m + 2*1 mod n) is possible for 1-digit numbers.
	 * We keep iterating this process of tracking possible remainders for k-digit
	 * numbers until the remainder of 0 mod n is possible in a non-zero number.
	 * 
	 * Now we know that a k-digit solution exists, such that the k-digit number consists of only {0, 1, 2},
	 * and the number is congruent to 0 modulo n. To construct the minimum solution, we start at the most significant
	 * digit of the result, choose the lowest possible value, and work backward toward the least significant digit.
	 * 
	 * The leading digit must be 1 or 2, because if it were 0 then it would contradict the fact that
	 * no solution shorter than k digits exists. All subsequent digits can possibly be 0, 1, or 2.
	 * 
	 * At each value place, we choose the lowest digit value out of {0, 1, 2} such that there still
	 * exists a solution for the remaining suffix of the number. When we choose a value at a certain
	 * digit position, say 2 at the 8th place, we subtract 2 * 10^8 mod n from the ongoing remainder.
	 */
	private static BigInteger findMinimumMultiple(int n) {
		// feasible.get(i)[j] indicates whether there exists an i-digit number that consists of
		// only the digits {0, 1, 2} (with possible leading zeros) having a remainder of j modulo n:
		// - 0: No i-digit number can form this remainder
		// - 1: Only zero can form this remainder
		// - 2: Some non-zero number can form this remainder
		
		// Initialization and base case
		List<byte[]> feasible = new ArrayList<>();
		feasible.add(new byte[n]);
		feasible.get(0)[0] = 1;
		
		// Add digits on the left side until a solution exists, using dynamic programming
		for (int i = 0; feasible.get(i)[0] != 2; i++) {  // Unbounded loop
			assert i == feasible.size() - 1;
			byte[] prev = feasible.get(i);
			byte[] cur = new byte[n];
			int digitMod = Library.powMod(10, i, n);
			for (int j = 0; j < n; j++) {  // Run time of O(n)
				if (prev[j] > 0) {
					cur[(j + digitMod * 0) % n] = prev[j];
					cur[(j + digitMod * 1) % n] = 2;
					cur[(j + digitMod * 2) % n] = 2;
				}
			}
			feasible.add(cur);
		}
		
		// Construct the smallest solution using the memoized table
		// Run time of O(feasible.size()) bigint operations
		BigInteger result = BigInteger.ZERO;
		int remainder = 0;  // Modulo n
		outer:  // Pick digit values from left (most significant) to right
		for (int i = feasible.size() - 2; i >= 0; i--) {
			int digitMod = Library.powMod(10, i, n);
			// Leading digit must start searching at 1; subsequent digits start searching at 0
			for (int j = (i == feasible.size() - 2 ? 1 : 0); j <= 2; j++) {
				// A roundabout way to compute (remainder - digitMod * j) mod n, because
				// Java's remainder operator needs extra work when the left side is negative
				int newRem = (remainder - digitMod * j % n + n) % n;
				if (feasible.get(i)[newRem] > 0) {
					result = result.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(j));
					remainder = newRem;
					continue outer;  // Digit value found, skip the assertion error
				}
			}
			throw new AssertionError();
		}
		return result;
	}
	
}
