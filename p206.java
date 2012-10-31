/* 
 * Solution to Project Euler problem 206
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.regex.Pattern;


public final class p206 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p206().run());
	}
	
	
	public String run() {
		long i = 1000000000;
		while (!isConcealedSquare(i))
			i += 10;
		return Long.toString(i);
	}
	
	
	private final Pattern PATTERN = Pattern.compile("1.2.3.4.5.6.7.8.9.0");
	
	private boolean isConcealedSquare(long n) {
		return PATTERN.matcher(Long.toString(n * n)).matches();
	}
	
}
