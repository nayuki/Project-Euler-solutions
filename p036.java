/* 
 * Solution to Project Euler problem 36
 * By Nayuki Minase
 */


public class p036 {
	
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i < 1000000; i++) {
			if (isPalindrome(Integer.toString(i, 10)) && isPalindrome(Integer.toString(i, 2)))
				sum += i;
		}
		System.out.println(sum);
	}
	
	
	private static boolean isPalindrome(String s) {
		String rev = new StringBuilder(s).reverse().toString();
		return s.equals(rev);
	}
	
}
