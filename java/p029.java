/* 
 * Solution to Project Euler problem 29
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public final class p029 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p029().run());
	}
	
	
	public String run() {
		Set<BigInteger> generated = new HashSet<BigInteger>();
		for (int a = 2; a <= 100; a++) {
			for (int b = 2; b <= 100; b++)
				generated.add(BigInteger.valueOf(a).pow(b));
		}
		return Integer.toString(generated.size());
	}
	
}
