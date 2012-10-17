/* 
 * Solution to Project Euler problem 63
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public class p063 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int n = 1; n <= 21; n++) {
			for (int i = 1; i <= 9; i++) {
				if (BigInteger.valueOf(i).pow(n).toString().length() == n)
					count++;
			}
		}
		System.out.println(count);
	}
	
}
