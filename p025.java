/* 
 * Solution to Project Euler problem 25
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p025 {
	
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
