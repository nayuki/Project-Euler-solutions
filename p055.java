/* 
 * Solution to Project Euler problem 55
 * By Nayuki Minase
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
			String rev = new StringBuilder(temp.toString()).reverse().toString();
			temp = temp.add(new BigInteger(rev));
			
			if (isPalindrome(temp.toString()))
				return false;
		}
		return true;
	}
	
	
	private static boolean isPalindrome(String s) {
		String rev = new StringBuilder(s).reverse().toString();
		return s.equals(rev);
	}
	
}
