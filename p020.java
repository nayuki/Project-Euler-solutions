/* 
 * Solution to Project Euler problem 20
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p020 {
	
	public static void main(String[] args) {
		String temp = factorial(100).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++)
			sum += temp.charAt(i) - '0';
		System.out.println(sum);
	}
	
	
	private static BigInteger factorial(int n) {
		BigInteger prod = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			prod = prod.multiply(BigInteger.valueOf(i));
		return prod;
	}
	
}
