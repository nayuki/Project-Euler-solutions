/* 
 * Solution to Project Euler problem 125
 * By Nayuki Minase
 */

import java.util.HashSet;
import java.util.Set;


public class p125 {
	
	public static void main(String[] args) {
		Set<Integer> nums = new HashSet<Integer>();;
		for (int i = 1; i <= 10000; i++) {
			int sum = i * i;
			for (int j = i + 1; ; j++) {
				sum += j * j;
				if (sum >= 100000000)
					break;
				if (Library.isPalindrome(sum))
					nums.add(sum);
			}
		}
		
		long sum = 0;
		for (int x : nums)
			sum += x;
		System.out.println(sum);
	}
	
}
