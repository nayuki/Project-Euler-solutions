/* 
 * Solution to Project Euler problem 5
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p005 {
	
	public static void main(String[] args) {
		BigInteger allLcm = BigInteger.ONE;
		for (int i = 1; i <= 20; i++)
			allLcm = lcm(BigInteger.valueOf(i), allLcm);
		System.out.println(allLcm);
	}
	
	
	private static BigInteger lcm(BigInteger x, BigInteger y) {
		return x.divide(x.gcd(y)).multiply(y);
	}
	
}
