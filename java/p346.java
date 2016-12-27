/* 
 * Solution to Project Euler problem 346
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public final class p346 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p346().run());
	}
	
	
	/* 
	 * 1 is a strong repunit because in every base b >= 2, its representation is "1", which is a repunit.
	 * 2 is not a strong repunit because in base 2 it is "10", but in every base b >= 3 it is "2".
	 * 
	 * As for other numbers, first assume that n is an arbitrary integer at least 3.
	 * It is trivially a repunit in base b = n - 1 (which is at least 2), where its representation is "11".
	 * For this n to be a strong repunit, it needs to be a repunit in at least one other base.
	 * Obviously it can't be "11" in another base. So it must be {"111",
	 * "1111", "11111", or some longer string} in some base smaller than b.
	 * 
	 * Phrased differently, if an integer n >= 3 has the representation {"111", "1111", or some longer string}
	 * in some base b >= 2, then it is automatically a strong repunit because firstly, its value is
	 * at least 7 ("111" in base 2), and secondly it is equal to "11" in some base b' >= 2.
	 * 
	 * Hence all we need to do is for each repunit length 3, 4, 5, etc., we generate the string (e.g. "111"),
	 * then evaluate its value at base 2, 3, etc. as long as the value stays within the limit,
	 * and add these values to the set of known strong repunits (to catch possible duplicates).
	 * 
	 * Note that the longest repunit length we need to test is at most the bit length of the limit.
	 * For example, because the limit is 10^12 = "1110100011010100101001010001000000000000" (base 2),
	 * any repunit longer than "1111111111111111111111111111111111111111" is guaranteed
	 * to exceed the limit in every base.
	 */
	
	private static final long LIMIT = 1_000_000_000_000L;
	
	public String run() {
		// Collect all generated numbers to eliminate duplicates
		Set<Long> strongRepunits = new HashSet<>();
		strongRepunits.add(1L);  // Special case
		
		// For each possible length of strong repunits (ignoring the trivial length of 2)
		for (int length = 3; length <= BigInteger.valueOf(LIMIT).bitLength(); length++) {
			
			// For each base to evaluate the repunit in, until the value exceeds the limit
			middle:
			for (int base = 2; ; base++) {
				
				// Evaluate value = base^(length-1) + base^(length-2) + ... + base^1 + base^0
				long value = 0;
				for (int i = 0; i < length; i++) {
					// Carefully check for arithmetic overflow
					if (Long.MAX_VALUE / base < value)
						break middle;
					value *= base;
					if (value + 1 < value)
						break middle;
					value++;
				}
				if (value >= LIMIT)
					break;
				strongRepunits.add(value);
			}
		}
		
		// Sum all the numbers generated
		long sum = 0;
		for (long x : strongRepunits) {
			if (sum + x < sum)
				throw new ArithmeticException("Overflow");
			sum += x;
		}
		return Long.toString(sum);
	}
	
}
