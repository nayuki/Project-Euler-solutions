/* 
 * Solution to Project Euler problem 206
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.regex.Pattern;


public class p206 {
	
	public static void main(String[] args) {
		long i = 1000000000;
		while (!isConcealedSquare(i))
			i += 10;
		System.out.println(i);
	}
	
	
	private static final Pattern PATTERN = Pattern.compile("1.2.3.4.5.6.7.8.9.0");
	
	private static boolean isConcealedSquare(long n) {
		return PATTERN.matcher(Long.toString(n * n)).matches();
	}
	
}
