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
				if (binomial(n, c).compareTo(MILLION) > 0)
					count++;
			}
		}
		System.out.println(count);
	}
	
	
	private static BigInteger binomial(int n, int k) {
		return factorial(n).divide(factorial(n - k).multiply(factorial(k)));
	}
	
	
	private static BigInteger factorial(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		BigInteger prod = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			prod = prod.multiply(BigInteger.valueOf(i));
		return prod;
	}
	
}
