/* 
 * Solution to Project Euler problem 79
 * By Nayuki Minase
 */


public class p079 {
	
	private static String[] subseqs = {"319", "680", "180", "690", "129", "620", "762", "689", "762", "318", "368", "710", "720", "710", "629", "168", "160", "689", "716", "731", "736", "729", "316", "729", "729", "710", "769", "290", "719", "680", "318", "389", "162", "289", "162", "718", "729", "319", "790", "680", "890", "362", "319", "760", "316", "729", "380", "319", "728", "716"};
	
	
	public static void main(String[] args) {
		for (int len = 3; ; len++) {
			int end = pow(10, len);
			for (int guess = 0; guess < end; guess++) {
				if (isConsistent(toString(guess, len))) {
					System.out.println(toString(guess, len));
					return;
				}
			}
		}
	}
	
	
	private static boolean isConsistent(String guess) {
		for (String subseq : subseqs) {
			if (!isSubsequence(guess, subseq))
				return false;
		}
		return true;
	}
	
	
	private static boolean isSubsequence(String parent, String child) {
		int j = 0;
		for (int i = 0; i < parent.length() && j < child.length(); i++) {
			if (parent.charAt(i) == child.charAt(j))
				j++;
		}
		return j == child.length();
	}
	
	
	private static String toString(int n, int len) {
		String s = Integer.toString(n);
		if (s.length() > len)
			throw new IllegalArgumentException();
		while (s.length() < len)
			s = "0" + s;
		return s;
	}
	
	
	private static int pow(int x, int y) {
		if (y < 0)
			throw new IllegalArgumentException();
		int z = 1;
		for (int i = 0; i < y; i++)
			z *= x;
		return z;
	}
	
}
