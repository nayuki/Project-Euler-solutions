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
		Pattern concealedSquarePattern = Pattern.compile("1.2.3.4.5.6.7.8.9.0");
		long i = 1000000000;
		while (!concealedSquarePattern.matcher(Long.toString(i * i)).matches())
			i += 10;
		return Long.toString(i);
	}
	
}
