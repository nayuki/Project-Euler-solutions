/* 
 * Solution to Project Euler problem 48
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p048 {
	
	public static void main(String[] args) {
		BigInteger modulus = BigInteger.valueOf(10).pow(10);
		BigInteger sum = BigInteger.ZERO;
		for (int i = 1; i <= 1000; i++)
			sum = sum.add(BigInteger.valueOf(i).modPow(BigInteger.valueOf(i), modulus)).mod(modulus);
		System.out.println(sum);
	}
	
}
