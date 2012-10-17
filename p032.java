/* 
 * Solution to Project Euler problem 32
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public class p032 {
	
	public static void main(String[] args) {
		// A candidate has at most 4 digits. This is because if it has 4 digits, then expressing it as a product of two numbers uses at least 4 digits.
		int sum = 0;
		for (int i = 1; i < 10000; i++) {
			if (hasPandigitalProduct(i))
				sum += i;
		}
		System.out.println(sum);
	}
	
	
	private static boolean hasPandigitalProduct(int n) {
		// Find and examine all factors of n
		for (int i = 1; i <= n; i++) {
			if (n % i == 0 && isPandigital("" + n + i + n/i))
				return true;
		}
		return false;
	}
	
	
	private static boolean isPandigital(String s) {
		if (s.length() != 9)
			return false;
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp).equals("123456789");
	}
	
}
