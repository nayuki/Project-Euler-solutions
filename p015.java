/* 
 * Solution to Project Euler problem 15
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p015 {
	
	public static void main(String[] args) {
		System.out.println(binomial(40, 20));
	}
	
	
	private static BigInteger binomial(int n, int k) {
		return factorial(n).divide(factorial(n - k).multiply(factorial(k)));
	}
	
	
	private static BigInteger factorial(int n) {
		BigInteger prod = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			prod = prod.multiply(BigInteger.valueOf(i));
		return prod;
	}
	
}
