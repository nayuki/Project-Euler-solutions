/* 
 * Solution to Project Euler problem 36
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p036 {
	
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i < 1000000; i++) {
			if (Library.isPalindrome(Integer.toString(i, 10)) && Library.isPalindrome(Integer.toString(i, 2)))
				sum += i;
		}
		System.out.println(sum);
	}
	
}
