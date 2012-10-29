/* 
 * Solution to Project Euler problem 25
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p025 {
	
	public static void main(String[] args) {
		BigInteger prev = BigInteger.ONE;
		BigInteger cur = BigInteger.ZERO;
		int i = 0;
		while (true) {
			if (cur.toString().length() == 1000) {
				System.out.println(i);
				break;
			}
			BigInteger temp = cur.add(prev);
			prev = cur;
			cur = temp;
			i++;
		}
	}
	
}
