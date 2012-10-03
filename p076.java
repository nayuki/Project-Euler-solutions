/* 
 * Solution to Project Euler problem 76
 * By Nayuki Minase
 */

import java.math.BigInteger;


public class p076 {
	
	public static void main(String[] args) {
		System.out.println(partitions(100, 1).subtract(BigInteger.ONE));
	}
	
	
	private static BigInteger partitions(int n, int k) {
		BigInteger[][] table = new BigInteger[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = n; j >= 0; j--) {
				if (j == i)
					table[i][j] = BigInteger.ONE;
				else if (j > i)
					table[i][j] = BigInteger.ZERO;
				else if (j == 0)
					table[i][j] = table[i][j + 1];
				else
					table[i][j] = table[i][j + 1].add(table[i - j][j]);
			}
		}
		return table[n][k];
	}
	
}
