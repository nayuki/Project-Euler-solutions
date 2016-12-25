/* 
 * Solution to Project Euler problem 346
 * by Project Nayuki
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
	
	
	private static final long LIMIT = 1_000_000_000_000L;
	
	public String run() {
		Set<Long> strongRepunits = new HashSet<>();
		strongRepunits.add(1L);
		for (int length = 3; length <= BigInteger.valueOf(LIMIT).bitLength(); length++) {
			for (int base = 2; ; base++) {
				long number = 0;
				long power = 1;
				for (int i = 0; i < length; i++, power *= base)
					number += power;
				// Now number = base^0 + base^1 + ... + base^(length-1).
				if (number >= LIMIT)
					break;
				strongRepunits.add(number);
			}
		}
		
		long sum = 0;
		for (long x : strongRepunits)
			sum += x;
		return Long.toString(sum);
	}
	
}
