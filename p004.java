/* 
 * Solution to Project Euler problem 4
 * By Nayuki Minase
 */


public class p004 {
	
	public static void main(String[] args) {
		int maxPalin = -1;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int prod = i * j;
				if (isPalindrome(prod) && prod > maxPalin)
					maxPalin = prod;
			}
		}
		System.out.println(maxPalin);
	}
	
	
	private static boolean isPalindrome(int x) {
		String s = Integer.toString(x);
		String rev = new StringBuilder(s).reverse().toString();
		return s.equals(rev);
	}
	
}
