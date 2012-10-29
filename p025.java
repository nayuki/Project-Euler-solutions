/* 
 * Solution to Project Euler problem 25
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p025 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p025().run());
	}
	
	
	public String run() {
		BigInteger lowerthres = BigInteger.TEN.pow( 999);
		BigInteger upperthres = BigInteger.TEN.pow(1000);
		BigInteger prev = BigInteger.ONE;
		BigInteger cur = BigInteger.ZERO;
		int i = 0;
		while (true) {
			if (cur.compareTo(lowerthres) >= 0)
				return Integer.toString(i);
			else if (cur.compareTo(upperthres) >= 0)
				throw new RuntimeException("Not found");
			
			BigInteger temp = cur.add(prev);
			prev = cur;
			cur = temp;
			i++;
		}
	}
	
}
