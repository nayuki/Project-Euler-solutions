/* 
 * Solution to Project Euler problem 53
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p053 {
	
	public static void main(String[] args) {
		BigInteger MILLION = BigInteger.TEN.pow(6);
		int count = 0;
		for (int n = 1; n <= 100; n++) {
			for (int c = 0; c <= n; c++) {
				if (Library.binomial(n, c).compareTo(MILLION) > 0)
					count++;
			}
		}
		System.out.println(count);
	}
	
}
