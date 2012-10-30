/* 
 * Solution to Project Euler problem 56
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p056 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p056().run());
	}
	
	
	public String run() {
		int max = 0;
		for (int a = 1; a < 100; a++) {
			for (int b = 1; b < 100; b++) {
				BigInteger pow = BigInteger.valueOf(a).pow(b);
				max = Math.max(digitSum(pow), max);
			}
		}
		return Integer.toString(max);
	}
	
	
	private static int digitSum(BigInteger n) {
		int sum = 0;
		String s = n.toString();
		for (int i = 0; i < s.length(); i++)
			sum += s.charAt(i) - '0';
		return sum;
	}
	
}
