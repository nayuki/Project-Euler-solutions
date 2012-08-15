/* 
 * Solution to Project Euler problem 29
 * By Nayuki Minase
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public class p029 {
	
	public static void main(String[] args) {
		Set<BigInteger> generated = new HashSet<BigInteger>();
		for (int a = 2; a <= 100; a++) {
			for (int b = 2; b <= 100; b++)
				generated.add(BigInteger.valueOf(a).pow(b));
		}
		System.out.println(generated.size());
	}
	
}
