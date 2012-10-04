/* 
 * Solution to Project Euler problem 53
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p053 {
	
	private static BigInteger MILLION = BigInteger.valueOf(1000000);
	
	
	public static void main(String[] args) {
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
