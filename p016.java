/* 
 * Solution to Project Euler problem 16
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p016 {
	
	public static void main(String[] args) {
		String temp = BigInteger.ONE.shiftLeft(1000).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++)
			sum += temp.charAt(i) - '0';
		System.out.println(sum);
	}
	
}
