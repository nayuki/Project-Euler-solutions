/* 
 * Solution to Project Euler problem 55
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public class p055 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 10000; i++) {
			if (isLychrel(i))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static boolean isLychrel(int n) {
		BigInteger temp = BigInteger.valueOf(n);
		for (int i = 0; i < 50; i++) {
			temp = temp.add(new BigInteger(Library.reverse(temp.toString())));
			
			if (Library.isPalindrome(temp.toString()))
				return false;
		}
		return true;
	}
	
}
